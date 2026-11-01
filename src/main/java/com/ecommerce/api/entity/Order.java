package com.ecommerce.api.entity;

import com.ecommerce.api.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "order_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @Setter
    private OrderStatus status;
    @Setter
    private BigDecimal total;

    public Order(Long id, LocalDateTime createdAt, OrderStatus status, BigDecimal total, Customer customer, Shipping shipping, Payment payment) {
        this.id = id;
        this.createdAt = createdAt;
        this.status = status;
        this.total = total;
        this.customer = customer;
        this.shipping = shipping;
        this.payment = payment;
    }

    @Setter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Setter
    @OneToOne(mappedBy = "order")
    private Shipping shipping;

    @Setter
    @OneToOne(mappedBy = "order")
    private Payment payment;

}



