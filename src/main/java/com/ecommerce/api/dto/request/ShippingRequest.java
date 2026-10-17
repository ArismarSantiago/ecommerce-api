package com.ecommerce.api.dto.request;

import com.ecommerce.api.valueObject.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShippingRequest(
        @NotNull
        @Valid
        Address address
) {
}
