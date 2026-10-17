package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.OrderMapper;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;
import com.ecommerce.api.dto.response.OrderResponse;
import com.ecommerce.api.entity.*;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.events.OnChangeStatusOrder;
import com.ecommerce.api.events.OnCreateOrder;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.methodsUse.CreateOrderItems;
import com.ecommerce.api.methodsUse.CreateShipping;
import com.ecommerce.api.methodsUse.ReturnTotalValueOrderAndCustomerOrder;
import com.ecommerce.api.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;


    private final ReturnTotalValueOrderAndCustomerOrder returnTotalValueOrderAndCustomerOrder;
    private final CreateShipping createShipping;
    private final CreateOrderItems orderItems;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private OrderMapper orderMapper;

    public OrderService(ReturnTotalValueOrderAndCustomerOrder mappingAndReturnOrderItems, CreateShipping createShipping, CreateOrderItems orderItems) {
        this.returnTotalValueOrderAndCustomerOrder = mappingAndReturnOrderItems;
        this.createShipping = createShipping;
        this.orderItems = orderItems;
    }


    public List<OrderResponse> findAll(){
        return repository.findAll().stream()
                .map(orderMapper::toResponse).toList();
    }
    public Order findByEntityId(Long id){
        return  repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Order não localizada", String.valueOf(id), "Order"));
    }


    public OrderResponse findById(Long id){
        Order order = findByEntityId(id);
        return orderMapper.toResponse(order);
    }

    public List<OrderResponse> findByCreatedDateBetween(LocalDate createdAt){
        LocalDateTime initialDate = createdAt.atStartOfDay();
        LocalDateTime finalDate = createdAt.atTime(LocalTime.MAX);
        return repository.findByCreatedAtBetween(initialDate, finalDate)
                .stream().map(orderMapper::toResponse)
                .toList();
    }




    @Transactional
    public OrderResponse createOrder(OrderRequest request){
        Order order = new Order();
        order.getOrderItems().addAll(orderItems.createOrderItems(request));
        order.setTotal(returnTotalValueOrderAndCustomerOrder.returnTotalValueOrder(request));
        order.setCustomer(returnTotalValueOrderAndCustomerOrder.returnCustomer(request));
        order.setStatus(OrderStatus.valueOf("WAITING_PAYMENT"));
        Order saveOrder = repository.save(order);
        orderItems.saveOrderItems(order);
        OnCreateOrder event = new OnCreateOrder(saveOrder.getId());
        eventPublisher.publishEvent(event);

        createShipping.createShipping(request.shippingRequest(), order);
        return orderMapper.toResponse(saveOrder);

    }

    public OrderResponse updateStatusOrder(OrderUpdateStatusRequest request, Long id){
        Order order = findByEntityId(id);
        if (order.getStatus().equals(request.status())){
            throw new DuplicateExceptions("O status ja esta nessa fase: "+ request.status(), "OrderStatus");
        }
        Order toEntity = orderMapper.orderUpdateStatus(request, order);
        repository.save(toEntity);
        OnChangeStatusOrder changeStatus = new OnChangeStatusOrder(order);
        eventPublisher.publishEvent(changeStatus);
        return orderMapper.toResponse(toEntity);
    }


}