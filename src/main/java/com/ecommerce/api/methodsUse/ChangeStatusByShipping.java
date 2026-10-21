package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.OrderItemStatus;
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
    private final ChanceProductStockByOrder chanceProductStockByOrder;

    public ChangeStatusByShipping(ChanceProductStockByOrder chanceProductStockByOrder) {
        this.chanceProductStockByOrder = chanceProductStockByOrder;
    }


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
                order.getOrderItems().forEach(c -> c.setStatus(OrderItemStatus.CANCELED));
                chanceProductStockByOrder.returnProductQuantityStock(order.getOrderItems());
            }
        }
        orderRepository.save(order);
    }
}
