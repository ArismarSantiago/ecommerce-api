package com.ecommerce.api.events;

import com.ecommerce.api.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;


public record OnPaymentEvent(
        Payment payment
) {
}
