package com.ecommerce.api.model;

import com.ecommerce.api.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
public class ProductModel extends RepresentationModel<ProductModel> {
    private final ProductResponse response;
}
