package com.ecommerce.api.entity;

import com.ecommerce.api.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLSelect;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "products_tb")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(nullable = false, unique = true, length = 250)
    private String name;
    @Setter
    @Column(nullable = false, length = 400)
    private String description;
    @Setter
    @Column(nullable = false, length = 8)
    private BigDecimal price;
    @Setter
    @Column(nullable = false, length = 3)
    private Integer stock;
    @Setter
    @Enumerated(EnumType.STRING)
    private ProductStatus status;


    @Setter
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Category category;

}
