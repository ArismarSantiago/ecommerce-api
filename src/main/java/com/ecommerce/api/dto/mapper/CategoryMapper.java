package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import com.ecommerce.api.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface CategoryMapper {

    Category toEntity(CategoryRequest request);

    CategoryResponse toResponse(Category category);

    Category updateCategory(CategoryRequest request);
}
