package com.ecommerce.api.events;

import com.ecommerce.api.entity.Shipping;

public record OnChangeStatusShipping(
        Shipping shipping
) {
}
