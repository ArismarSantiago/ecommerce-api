package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.ProductController;
import com.ecommerce.api.dto.response.ProductResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<ProductResponse, EntityModel<ProductResponse>> {
    @Override
    public EntityModel<ProductResponse> toModel(ProductResponse response) {
        return EntityModel.of(
                response,
                linkTo(methodOn(ProductController.class).findAll()).withSelfRel(),
                linkTo(methodOn(ProductController.class).findById(response.id())).withSelfRel(),
                linkTo(methodOn(ProductController.class).findByName(response.name())).withSelfRel(),
                linkTo(ProductController.class).slash("findByPrice").withRel("/{initialPrice}/{finalPrice}"),
                linkTo(methodOn(ProductController.class).insertProduct(null)).withSelfRel(),
                linkTo(methodOn(ProductController.class).update(null, response.id())).withSelfRel()
        );
    }
}
