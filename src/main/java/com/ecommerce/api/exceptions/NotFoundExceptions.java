package com.ecommerce.api.exceptions;

public class NotFoundExceptions extends RuntimeException {
    private final String id;
    private final String name;
    public NotFoundExceptions(String message, String id, String name) {
        super(message);
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
