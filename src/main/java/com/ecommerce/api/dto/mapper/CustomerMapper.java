package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public static Customer toEntity(CustomerRequest request){
        Customer entity = new Customer();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhoneNumber(request.phoneNumber());
        entity.setAddress(request.address());


        return entity;
    }
    public static CustomerResponse toResponse(Customer entity){
      return CustomerResponse.builder()
              .id(entity.getId())
              .name(entity.getName())
              .email(entity.getEmail())
              .phoneNumber(entity.getPhoneNumber())
              .address(entity.getAddress()).build();
    }

    public static Customer update(CustomerRequest request, Long id){
        Customer entity = new Customer();
        entity.setName(request.name());
        entity.setEmail(request.email());
        entity.setPhoneNumber(request.phoneNumber());
        entity.setAddress(request.address());
        return entity;
    }
}
