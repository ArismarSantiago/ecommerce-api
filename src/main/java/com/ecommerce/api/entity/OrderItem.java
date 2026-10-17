package com.ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Entity(name = "order_items_tb")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 3)
    @Setter
    private Integer quantity;
    @Column(nullable = false)
    @Setter
    private BigDecimal unitPrice;
    @Column(nullable = false)
    @Setter
    private BigDecimal subTotal;


    public OrderItem(Long id, Integer quantity, BigDecimal unitPrice, BigDecimal subTotal, Order order, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
        this.order = order;
        this.product = product;
    }

    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;



}
