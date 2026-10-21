package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.OrderStatus;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MethodStatusOrderAndShipping {

    @Autowired
    private OrderRepository orderRepository;






    public void changeStatusForPayment(Payment payment){
       Order order = payment.getOrder();

        switch (payment.getStatus()){
            case PAID -> {
                order.setStatus(OrderStatus.PROCESSING);
                order.getShipping().setStatus(ShippingStatus.PENDING);
                order.getShipping().setTrackingCode(createTrackingCode(order.getShipping().getId()));
                orderRepository.save(order);

            }
            case CANCELED -> {
                order.setStatus(OrderStatus.CANCELED);
                order.getShipping().setStatus(ShippingStatus.CANCELED);
                order.getShipping().setTrackingCode(createTrackingCode(order.getShipping().getId()));
                orderRepository.save(order);
            }
            case PENDING -> {
                order.setStatus(OrderStatus.WAITING_PAYMENT);
                order.getShipping().setStatus(ShippingStatus.WAITING_PAYMENT);
                order.getShipping().setTrackingCode(createTrackingCode(order.getShipping().getId()));
                orderRepository.save(order);
            }
            case COMPLETED -> {
                order.setStatus(OrderStatus.COMPLETED);
                order.getShipping().setStatus(ShippingStatus.DELIVERED);
                order.getShipping().setTrackingCode(createTrackingCode(order.getShipping().getId()));
                orderRepository.save(order);
            }
            case null, default -> {
                throw new NotFoundExceptions("Status precisar conter algum valor", String.valueOf(payment.getStatus()), "PaymentStatus");
            }
        }
    }


    public String createTrackingCode(Long id){
        Random random = new Random();
        return  String.valueOf(id) + random.nextInt(10);
    }
}
