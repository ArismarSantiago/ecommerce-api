package com.ecommerce.api.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Address {
    @Column(length = 200, nullable = false)
    private String street;
    @Column(length = 10, nullable = false)
    private String number;
    @Column(length = 50, nullable = false)
    private String neighborhood;
    @Column(length = 30, nullable = false)
    private String city;
    @Column(length = 30, nullable = false)
    private String state;
    @Column(length = 10, nullable = false)
    private String zipCode;

}
