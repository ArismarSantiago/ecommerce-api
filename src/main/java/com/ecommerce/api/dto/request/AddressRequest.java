package com.ecommerce.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AddressRequest(
        @NotBlank
        @Length(max = 200)
        String street,
        @NotBlank
        @Length(max = 10)
        String number,
        @NotBlank
        @Length(max = 50)
        String neighborhood,
        @NotBlank
        @Length(max = 30)
        String city,
        @NotBlank
        @Length(max = 30)
        String state,
        @NotBlank
        @Length(max = 10)
        String zipCode
) {
}
