package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.request.updateRequest.ProductUpdateRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);

    ProductResponse toResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProduct(ProductUpdateRequest request, @MappingTarget Product product);

}