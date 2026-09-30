package com.ecommerce.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
    @NotBlank(message = "Nome da categoria")
    @Min(3) @Max(200)
    String name
) {
}
