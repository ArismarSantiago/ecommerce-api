package com.ecommerce.api.service;


import com.ecommerce.api.dto.mapper.OrderItemsMapper;
import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.dto.response.OrderResponse;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.OrderItemRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Service
public class OrderItemService {


    @Autowired
    private OrderItemRepository repository;
    @Autowired
    private OrderItemsMapper mapper;



    public List<OrderItemResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse).toList();
    }

    public OrderItemResponse findById(Long id) {
        OrderItem orderItem = repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions(
                        "OrderItem não localizada",
                        String.valueOf(id), "OrderItem"));
        return mapper.toResponse(orderItem);
    }
}
