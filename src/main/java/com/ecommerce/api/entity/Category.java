package com.ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "category_tb")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 200, unique = true)
    private String name;



    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
