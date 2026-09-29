package com.ecommerce.api.entity;

import com.ecommerce.api.valueObject.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "customer_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 200, unique = true)
    private String email;
    @Column(nullable = false, length = 14)
    private String phoneNumber;

    @Embedded
    private Address address;


    @OneToMany(mappedBy = "customer")
    private List<Order> order;
}
