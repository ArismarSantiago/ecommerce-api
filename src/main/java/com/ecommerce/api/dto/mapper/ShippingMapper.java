package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.ShippingRequest;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.entity.Shipping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ShippingMapper {

    Shipping toEntity(ShippingRequest request);

    ShippingResponse toResponse(Shipping shipping);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Shipping updateShippingAddress(ShippingRequest request);

}
