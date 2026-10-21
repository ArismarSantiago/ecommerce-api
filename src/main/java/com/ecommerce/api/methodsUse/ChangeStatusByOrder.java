package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.OrderItemStatus;
import com.ecommerce.api.enums.PaymentStatus;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.events.OnChangeStatusOrder;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.repository.PaymentRepository;
import com.ecommerce.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusByOrder {


    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private OrderRepository repository;

    private final ChanceProductStockByOrder ChanceProductStockByOrder;

    public ChangeStatusByOrder(ChanceProductStockByOrder chanceProductStockByOrder) {
        ChanceProductStockByOrder = chanceProductStockByOrder;
    }


    @EventListener
    public void changeStatusShippingAndPayment(OnChangeStatusOrder changeStatusOrder) {
        Order order = changeStatusOrder.order();
        Payment payment = order.getPayment();
        Shipping shipping = order.getShipping();
        switch (changeStatusOrder.order().getStatus()) {
            case CANCELED -> {
                shipping.setStatus(ShippingStatus.CANCELED);
                if (payment.getStatus() == PaymentStatus.PAID) {
                    payment.setStatus(PaymentStatus.REFUND_PROCESSING);
                } else {
                    payment.setStatus(PaymentStatus.CANCELED);
                }
                order.getOrderItems().forEach(c -> c.setStatus(OrderItemStatus.CANCELED));
                ChanceProductStockByOrder.returnProductQuantityStock(changeStatusOrder.order().getOrderItems());
            }
            case PREPARING -> {
                shipping.setStatus(ShippingStatus.PREPARING);
            }

            case IN_TRANSIT -> {
                shipping.setStatus(ShippingStatus.IN_TRANSIT);
            }
        }
        repository.save(order);
    }
}