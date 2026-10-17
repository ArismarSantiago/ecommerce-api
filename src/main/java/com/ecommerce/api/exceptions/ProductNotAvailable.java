package com.ecommerce.api.exceptions;

import com.ecommerce.api.entity.Product;
import lombok.Getter;

@Getter
public class ProductNotAvailable extends RuntimeException {
    private final Product product;
    private final String description;
    public ProductNotAvailable(String message, Product product, String description) {
        super(message);
        this.product = product;
        this.description = description;
    }
}
