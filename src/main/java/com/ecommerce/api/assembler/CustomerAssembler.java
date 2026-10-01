package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.CustomerController;
import com.ecommerce.api.dto.response.CustomerResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CustomerAssembler implements RepresentationModelAssembler<CustomerResponse, EntityModel<CustomerResponse>> {
    @Override
    public EntityModel<CustomerResponse> toModel(CustomerResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(CustomerController.class).findAll()).withSelfRel(),
                linkTo(methodOn(CustomerController.class).findById(response.id())).withRel("findById").withSelfRel(),
                linkTo(methodOn(CustomerController.class).findByName(response.name())).withRel("findByName").withSelfRel(),
                linkTo(methodOn(CustomerController.class).findByEmail(response.email())).withRel("findByEmail").withSelfRel(),
                linkTo(methodOn(CustomerController.class).findByPhoneNumber(response.phoneNumber())).withRel("findByPhoneNumber").withSelfRel(),
                linkTo(methodOn(CustomerController.class).insert(null)).withRel("Insert").withSelfRel(),
                linkTo(methodOn(CustomerController.class).update(null, response.id())).withRel("Update").withSelfRel()
        );
    }
}
