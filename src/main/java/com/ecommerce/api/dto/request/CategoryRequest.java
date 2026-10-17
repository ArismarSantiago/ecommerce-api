package com.ecommerce.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
    @NotBlank(message = "Nome da categoria")
    String name
) {
}
