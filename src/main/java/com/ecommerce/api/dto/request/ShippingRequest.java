package com.ecommerce.api.dto.request;

import com.ecommerce.api.valueObject.Address;
import jakarta.validation.constraints.NotBlank;

public record ShippingRequest(
        @NotBlank
        Address address
) {
}
