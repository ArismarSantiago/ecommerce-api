package com.ecommerce.api.entity;

import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Shipping {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;
    @Column(nullable = false, length = 20)
    private String trackingCode;
    @CreatedDate
    private LocalDateTime shippedAt;

    @Embedded
    private Address address;

}
