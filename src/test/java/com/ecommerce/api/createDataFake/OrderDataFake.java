package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.entity.*;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDataFake {


    public static Order orderFake(int i, Payment payment, Customer customer, Shipping shipping){
        Order order = new Order();
        order.setId(Long.valueOf(i));
        order.setCustomer(customer);
        order.setPayment(payment);
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setShipping(shipping);
        order.setTotal(BigDecimal.valueOf(200));
        return order;
    }


    public static List<Order> orderList(int number, Payment payment, Customer customer, Shipping shipping){
        List<Order> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(orderFake(i, payment, customer, shipping));
        }
        return list;
    }

    public static OrderRequest request(Order order, List<OrderItemRequest> orderItems){
        return new OrderRequest(orderItems, order.getCustomer().getId(), ShippingFakeData.requestFake(order.getShipping()));
    }

    public static OrderUpdateStatusRequest orderUpdateStatusRequest(Long id, OrderStatus status){
      return  new OrderUpdateStatusRequest(id, status);
    }
}
