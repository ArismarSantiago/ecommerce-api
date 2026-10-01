package com.ecommerce.api.model;

import com.ecommerce.api.dto.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class CategoryModel extends RepresentationModel<CategoryModel> {
    private final CategoryResponse response;
}
