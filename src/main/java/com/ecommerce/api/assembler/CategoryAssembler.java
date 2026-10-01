package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.CategoryController;
import com.ecommerce.api.dto.response.CategoryResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CategoryAssembler implements RepresentationModelAssembler<CategoryResponse, EntityModel<CategoryResponse>> {
    @Override
    public EntityModel<CategoryResponse> toModel(CategoryResponse response) {
        return EntityModel.of(response,
        linkTo(methodOn(CategoryController.class).findAll()).withRel("findAll").withSelfRel(),
        linkTo(methodOn(CategoryController.class).findById(response.id())).withRel("findById").withSelfRel(),
        linkTo(methodOn(CategoryController.class).findByName(response.name())).withRel("findByName").withSelfRel(),
        linkTo(methodOn(CategoryController.class).update(null, response.id())).withRel("update").withSelfRel(),
        linkTo(methodOn(CategoryController.class).insert(null)).withRel("insert").withSelfRel()
        );
    }
}
