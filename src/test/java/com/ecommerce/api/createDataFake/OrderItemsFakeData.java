package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.enums.OrderItemStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderItemsFakeData {

    public static OrderItem orderItemFake(int i) {
        OrderItem orderItem = new OrderItem();
        orderItem.setStatus(OrderItemStatus.WAITING_PAYMENT);
        orderItem.setProduct(ProductsFakeData.productFake(i));
        orderItem.setQuantity(10);
        orderItem.setSubTotal(BigDecimal.valueOf(200));
        orderItem.setUnitPrice(BigDecimal.valueOf(20));

        return orderItem;
    }

    public static List<OrderItem> orderItems(int number) {
        List<OrderItem> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(orderItemFake(i));
        }
        return list;
    }

    public static OrderItemRequest orderItemRequestFake(int i) {
        return new OrderItemRequest(ProductsFakeData.productFake(i).getId(), i);
    }

    public static List<OrderItemRequest> orderItemRequestList(int number) {
        List<OrderItemRequest> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(orderItemRequestFake(i));
        }
        return list;
    }
}
