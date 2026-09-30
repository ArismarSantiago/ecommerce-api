package com.ecommerce.api.exceptions;

public class NotFoundExceptions extends RuntimeException {
    private final Long id;
    private final String name;
    public NotFoundExceptions(String message, Long id, String name) {
        super(message);
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
