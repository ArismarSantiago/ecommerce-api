package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import com.ecommerce.api.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {



    public static Category toEntity(CategoryRequest request){
        Category entity = new Category();
        entity.setName(request.name());
        return entity;
    }


    public static CategoryResponse toResponse(Category entity){
        return CategoryResponse.builder()
                .id(entity.getId()).name(entity.getName()).build();

    }


    public static Category update(CategoryRequest request, Long id){
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
}
