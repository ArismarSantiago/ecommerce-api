package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.*;
import com.ecommerce.api.dto.mapper.OrderMapper;
import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.dto.response.OrderResponse;
import com.ecommerce.api.entity.*;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.methodsUse.CreateOrderItems;
import com.ecommerce.api.methodsUse.CreateShipping;
import com.ecommerce.api.methodsUse.ReturnTotalValueOrderAndCustomerOrder;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private ReturnTotalValueOrderAndCustomerOrder returnTotalValueOrderAndCustomerOrder;

    @Mock
    private ApplicationEventPublisher eventPublisher;
    @Mock
    private CreateShipping createShipping;
    @Mock
    private CreateOrderItems orderItems;


    @InjectMocks
    private OrderService service;

    @BeforeEach
    void up() {
        OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

        ReflectionTestUtils.setField(service, "repository", repository);
        ReflectionTestUtils.setField(service, "mapper", mapper);
        ReflectionTestUtils.setField(service, "eventPublisher", eventPublisher);
    }

    @Test
    void findAll() {
        List<Order> orders = OrderDataFake.orderList(3, PaymentDataFake.paymentFake(1),
                CustomerFakeData.customerFake(1),
                ShippingFakeData.shippingFake(1));
        when(repository.findAll()).thenReturn(orders);

        List<OrderResponse> responses = service.findAll();
    }

    @Test
    void findById() {
        Order order = OrderDataFake.orderFake(1,
                PaymentDataFake.paymentFake(1),
                CustomerFakeData.customerFake(1),
                ShippingFakeData.shippingFake(1));
        when(repository.findById(anyLong())).thenReturn(Optional.of(order));

        OrderResponse response = service.findById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    void createOrder() {
        Order order = OrderDataFake.orderFake(1,
                PaymentDataFake.paymentFake(1),
                CustomerFakeData.customerFake(1),
                ShippingFakeData.shippingFake(1));
        List<OrderItemRequest> orderItemsRequest = OrderItemsFakeData.orderItemRequestList(3);
       OrderRequest request =  OrderDataFake.request(order, orderItemsRequest);
       List<OrderItem> items = OrderItemsFakeData.orderItems(3);
        when(repository.save(any())).thenReturn(order);
        when(orderItems.createOrderItems(request)).thenReturn(items);
        when(returnTotalValueOrderAndCustomerOrder.returnTotalValueOrder(request)).thenReturn(BigDecimal.valueOf(200));

        OrderResponse response = service.createOrder(request);

        verify(repository, times(1)).save(any());
    }

    @Test
    void updateStatusOrder() {
        Order order = OrderDataFake.orderFake(1,
                PaymentDataFake.paymentFake(1),
                CustomerFakeData.customerFake(1),
                ShippingFakeData.shippingFake(1));
        order.setStatus(OrderStatus.IN_TRANSIT);

        OrderUpdateStatusRequest request = OrderDataFake.orderUpdateStatusRequest(1L, OrderStatus.CANCELED);
        when(repository.findById(1L)).thenReturn(Optional.of(order));
        when(repository.save(any())).thenReturn(order);

        OrderResponse response = service.updateStatusOrder(request, request.orderId());

        assertEquals(order.getStatus(), response.getStatus());
        verify(repository, times(1)).save(any());
    }
}