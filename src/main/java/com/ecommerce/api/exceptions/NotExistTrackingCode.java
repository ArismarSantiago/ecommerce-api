package com.ecommerce.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotExistTrackingCode extends RuntimeException {
    private final String trackingCode;
    public NotExistTrackingCode(String message, String trackingCode) {
        super(message);
        this.trackingCode = trackingCode;
    }
}
