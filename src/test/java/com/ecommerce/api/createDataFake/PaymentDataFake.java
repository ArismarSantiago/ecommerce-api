package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.enums.PaymentStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentDataFake {


    public static Payment paymentFake(int i){
        Payment payment = new Payment();
        ReflectionTestUtils.setField(payment, "id", Long.valueOf(i));
        payment.setStatus(PaymentStatus.PENDING);
        payment.setAmount(BigDecimal.valueOf(200));

        return payment;
    }

    public static List<Payment> listPayment(int number){
        List<Payment> list = new ArrayList<>();
        for (int i = 0; i<number; i++){
            list.add(paymentFake(i));
        }
        return list;
    }

    public static PaymentRequest requestFake(Payment payment){
        return new PaymentRequest(PaymentStatus.COMPLETED);
    }
}
