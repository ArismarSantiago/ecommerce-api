package com.ecommerce.api.exceptions;

import lombok.Getter;

@Getter
public class DuplicateNameExceptions extends RuntimeException {
    private final String name;
    public DuplicateNameExceptions(String message, String name) {
        super(message);
        this.name = name;
    }
}
