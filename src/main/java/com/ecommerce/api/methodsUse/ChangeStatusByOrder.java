package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.PaymentStatus;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.events.OnChangeStatusOrder;
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


    @EventListener
    public void changeStatusShipping(OnChangeStatusOrder changeStatusOrder) {
        Payment payment = changeStatusOrder.order().getPayment();
        Shipping shipping = changeStatusOrder.order().getShipping();
        switch (changeStatusOrder.order().getStatus()) {
            case CANCELED -> {
                shipping.setStatus(ShippingStatus.CANCELED);
                if (payment.getStatus() == PaymentStatus.PAID) {
                    payment.setStatus(PaymentStatus.REFUND_PROCESSING);
                } else {
                    payment.setStatus(PaymentStatus.CANCELED);
                }
                paymentRepository.save(payment);
            }
            case PREPARING -> {
                shipping.setStatus(ShippingStatus.PREPARING);
            }

            case IN_TRANSIT -> {
                shipping.setStatus(ShippingStatus.IN_TRANSIT);
            }
        }
        shippingRepository.save(shipping);
    }
}