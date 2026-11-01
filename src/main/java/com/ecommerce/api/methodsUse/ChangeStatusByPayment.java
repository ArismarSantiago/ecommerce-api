package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.OrderItemStatus;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.events.OnPaymentEvent;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ChangeStatusByPayment {

    @Autowired
    private OrderRepository repository;
    private final ChanceProductStockByOrder chanceProductStockByOrder;

    public ChangeStatusByPayment(ChanceProductStockByOrder chanceProductStockByOrder) {
        this.chanceProductStockByOrder = chanceProductStockByOrder;
    }

    public String createTrackingCode(Long id){
        Random random = new Random();
        return  String.valueOf(id) + random.nextInt(10);
    }


    @EventListener
    public void ChangeStatusOrderAndShipping(OnPaymentEvent event) {
        Order order = event.payment().getOrder();

        switch (event.payment().getStatus()) {
            case CANCELED -> {
                order.getShipping().setStatus(ShippingStatus.CANCELED);
                order.setStatus(OrderStatus.CANCELED);
                order.getOrderItems().forEach(c -> c.setStatus(OrderItemStatus.CANCELED));
                chanceProductStockByOrder.returnProductQuantityStock(order.getOrderItems());
            }
            case PAID -> {
                order.getShipping().setStatus(ShippingStatus.PENDING);
                order.setStatus(OrderStatus.PROCESSING);
                order.getShipping().setTrackingCode(createTrackingCode(order.getShipping().getId()));
            }
            case REFUND_PROCESSING -> {
                order.getShipping().setStatus(ShippingStatus.RETURNING);
                order.setStatus(OrderStatus.RETURNING);
            }
            case null, default -> {
                throw new NotFoundExceptions("Status precisar conter algum valor", String.valueOf(event.payment().getStatus()), "PaymentStatus");
            }
        }
        repository.save(order);
    }
}
