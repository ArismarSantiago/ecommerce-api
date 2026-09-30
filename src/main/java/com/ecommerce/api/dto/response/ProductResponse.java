package com.ecommerce.api.dto.response;

import jakarta.persistence.Column;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer stock
) {
}
