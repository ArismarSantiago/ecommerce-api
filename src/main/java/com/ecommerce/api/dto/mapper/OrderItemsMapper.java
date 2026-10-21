package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderItemsMapper {



    OrderItem toEntity(OrderItemRequest request);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "unitPrice", source = "product.price")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "status", ignore = true)
    OrderItem toEntity(OrderItemRequest request, Product product);

    OrderItemResponse toResponse(OrderItem orderItem);

}
