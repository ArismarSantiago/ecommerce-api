package com.ecommerce.api.model;

import com.ecommerce.api.dto.response.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
@AllArgsConstructor
@Getter
public class CustomerModel extends RepresentationModel<CustomerModel> {
    private final CustomerResponse response;
}
