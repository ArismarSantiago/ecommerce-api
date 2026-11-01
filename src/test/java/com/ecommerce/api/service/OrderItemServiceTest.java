package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.OrderItemsFakeData;
import com.ecommerce.api.dto.mapper.OrderItemsMapper;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTest {

    @Mock
    private OrderItemRepository repository;

    @InjectMocks
    private OrderItemService service;

    @Spy
    private OrderItemsMapper mapper = Mappers.getMapper(OrderItemsMapper.class);

    @Test
    void findAll() {
        List<OrderItem> items = OrderItemsFakeData.orderItems(3);
        when(repository.findAll()).thenReturn(items);

        List<OrderItemResponse> responses = service.findAll();
        assertNotNull(responses);
        OrderItemResponse response = responses.getFirst();
        assertEquals(0L, response.getId());
        verify(repository, times(1)).findAll();

    }

    @Test
    void findById() {
        OrderItem orderItem = OrderItemsFakeData.orderItemFake(1);

        when(repository.findById(1L)).thenReturn(Optional.of(orderItem));

        OrderItemResponse response = service.findById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(repository,times(1)).findById(1L);
    }
}