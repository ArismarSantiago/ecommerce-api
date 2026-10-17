package com.ecommerce.api.dto.updateRequest;

import com.ecommerce.api.dto.request.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public record CustomerUpdateRequest (
        String name,
        @Email
        String email,
        String phoneNumber,
        @Valid
        AddressRequest address
)
{ }
