package com.ecommerce.api.dto.request.updateRequest;

import com.ecommerce.api.enums.OrderStatus;

public record OrderUpdateStatusRequest(
        Long orderId,
        OrderStatus status
) {
}
