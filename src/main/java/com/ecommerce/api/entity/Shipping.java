package com.ecommerce.api.entity;

import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Enumerated(EnumType.STRING)
    private ShippingStatus status;
    @Setter
    @Column(nullable = false, length = 20)
    private String trackingCode;
    @CreatedDate
    private LocalDateTime shippedAt;

    @Setter
    @Embedded
    private Address address;

    @Setter
    @OneToOne
    private Order order;

}
