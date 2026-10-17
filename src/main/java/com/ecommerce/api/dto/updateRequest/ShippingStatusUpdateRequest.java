package com.ecommerce.api.dto.request.updateRequest;

import com.ecommerce.api.enums.ShippingStatus;

public record ShippingStatusUpdateRequest(
        Long shippingId,
        ShippingStatus status
) {
}
