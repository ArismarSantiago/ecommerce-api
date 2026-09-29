package com.ecommerce.api.dto.request;

import com.ecommerce.api.valueObject.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "nome é obrigatório")
        @Min(3) @Max(200)
        String name,
        @NotBlank(message = "email é obrigatório")
        @Min(5) @Max(200)
        @Email
        String email,
        @NotBlank(message = "esse campo é obrigatório")
        @Min(11) @Max(20)
        String phoneNumber,
        Address address

) {
}
