package com.ecommerce.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "nome é obrigatório")
        String name,
        @NotBlank(message = "email é obrigatório")
       @Email
        String email,
        @NotBlank(message = "esse campo é obrigatório")
        String phoneNumber,
     @Valid
     AddressRequest address

) {
}
