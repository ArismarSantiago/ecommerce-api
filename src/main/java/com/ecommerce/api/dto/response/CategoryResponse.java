package com.ecommerce.api.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long id,
        String name
) {
}
