package com.ecommerce.api.dto.request.updateRequest;

import com.ecommerce.api.enums.ProductStatus;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        ProductStatus status,
        Integer stock,
        Long categoryId
) {
}
