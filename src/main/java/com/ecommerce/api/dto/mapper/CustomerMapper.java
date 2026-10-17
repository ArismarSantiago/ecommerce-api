package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.AddressRequest;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.updateRequest.CustomerUpdateRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.valueObject.Address;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequest request);

    CustomerResponse toResponse(Customer customer);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateCustomer (CustomerUpdateRequest request, @MappingTarget Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address map(AddressRequest value);

}
