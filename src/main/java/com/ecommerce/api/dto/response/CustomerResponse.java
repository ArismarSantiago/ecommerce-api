package com.ecommerce.api.dto.response;

import com.ecommerce.api.valueObject.Address;
import lombok.Builder;

@Builder
public record CustomerResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        Address address
) {
}
