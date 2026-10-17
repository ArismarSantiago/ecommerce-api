package com.ecommerce.api.entity;

import com.ecommerce.api.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Payment_tb")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Setter
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Setter
    private PaymentStatus status;
    @CreatedDate
    private LocalDateTime paymentDate;

    public Payment(Long id, BigDecimal amount, PaymentStatus status, LocalDateTime paymentDate, Order order) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
        this.order = order;
    }

    @Setter
    @OneToOne
    private Order order;

}
