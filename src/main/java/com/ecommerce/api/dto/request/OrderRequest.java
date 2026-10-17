package com.ecommerce.api.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OrderRequest(
        @NotEmpty(message = "O pedido deve conter pelo menos um item")
        List<OrderItemRequest> items,
        Long customerId,
        @Valid
        ShippingRequest shippingRequest
) {}

