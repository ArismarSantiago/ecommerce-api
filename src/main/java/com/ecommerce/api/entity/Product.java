package com.ecommerce.api.entity;

import com.ecommerce.api.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "products_tb")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 250)
    private String name;
    @Column(nullable = false, length = 400)
    private String description;
    @Column(nullable = false, length = 8)
    private BigDecimal price;
    @Column(nullable = false, length = 3)
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;


    @ManyToOne
    @JoinColumn(name = "products_id")
    private Category category;

}
