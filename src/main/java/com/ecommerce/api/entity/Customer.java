package com.ecommerce.api.entity;

import com.ecommerce.api.valueObject.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity(name = "customer_tb")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, length = 200)
    private String name;
    @Setter
    @Column(nullable = false, length = 200, unique = true)
    private String email;
    @Setter
    @Column(nullable = false, length = 14)
    private String phoneNumber;

    @Setter
    @Embedded
    private Address address;


    @OneToMany(mappedBy = "customer")
    private List<Order> order;
}
