package com.ecommerce.api.dto.request;

import com.ecommerce.api.enums.PaymentStatus;

public record PaymentRequest(
        PaymentStatus status
) {
}
