package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.events.OnChangeStatusShipping;
import com.ecommerce.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusByShipping {

    @Autowired
    private OrderRepository orderRepository;


    @EventListener
    public void changeStatusOrderByShipping(OnChangeStatusShipping statusShipping){
        Shipping shipping = statusShipping.shipping();
        Order order = shipping.getOrder();
        switch (shipping.getStatus()) {
            case IN_TRANSIT -> {
                order.setStatus(OrderStatus.IN_TRANSIT);
            }
            case SHIPPED -> {
                order.setStatus(OrderStatus.SHIPPED);
            }
            case DELIVERED -> {
                order.setStatus(OrderStatus.COMPLETED);
             }
            case RETURNING -> {
                order.setStatus(OrderStatus.RETURNING);
            }
            case CANCELED -> {
                order.setStatus(OrderStatus.CANCELED);
            }
        }
        orderRepository.save(order);
    }
}
