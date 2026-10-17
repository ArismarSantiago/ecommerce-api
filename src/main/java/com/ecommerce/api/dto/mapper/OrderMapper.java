package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;
import com.ecommerce.api.dto.response.OrderResponse;
import com.ecommerce.api.entity.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customerName", source = "customer.name")
    OrderResponse toResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order orderUpdateStatus(OrderUpdateStatusRequest updateStatusRequest,@MappingTarget Order order);

}
