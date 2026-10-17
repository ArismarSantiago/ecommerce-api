package com.ecommerce.api.events;

import com.ecommerce.api.entity.Order;

public record OnChangeStatusOrder(
        Order order
) {
}
