package com.ecommerce.api.exceptions;

import lombok.Getter;

@Getter
public class DuplicateExceptions extends RuntimeException {
    private final String name;
    public DuplicateExceptions(String message, String name) {
        super(message);
        this.name = name;
    }
}
