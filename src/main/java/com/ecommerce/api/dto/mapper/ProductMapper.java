package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {


    public static Product toEntity(ProductRequest request){
        Product entity = new Product();
        entity.setName(request.name());
        entity.setPrice(request.price());
        entity.setStock(request.stock());
        entity.setDescription(request.description());

        return entity;
    }


    public static ProductResponse toResponse(Product entity){
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .description(entity.getDescription())
                .build();
    }

    public static Product update(ProductRequest request, Long id){
        Product entity = new Product();
        entity.setName(request.name());
        entity.setPrice(request.price());
        entity.setStock(request.stock());
        entity.setDescription(request.description());

        return entity;
    }
}
