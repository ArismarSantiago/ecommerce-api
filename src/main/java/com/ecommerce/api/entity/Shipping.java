package com.ecommerce.api.entity;

import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Shipping {
    @Id
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

    @OneToOne
    private Order order;

}
