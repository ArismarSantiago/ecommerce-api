package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.ProductControllerDocs;
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
                linkTo(methodOn(ProductControllerDocs.class).findAll()).withSelfRel(),
                linkTo(methodOn(ProductControllerDocs.class).findById(response.id())).withSelfRel(),
                linkTo(methodOn(ProductControllerDocs.class).findByName(response.name())).withSelfRel(),
                linkTo(ProductControllerDocs.class).slash("findByPrice").withRel("/{initialPrice}/{finalPrice}"),
                linkTo(methodOn(ProductControllerDocs.class).insertProduct(null)).withSelfRel()
        );
    }
}
