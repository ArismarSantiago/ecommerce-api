package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.CategoryController;
import com.ecommerce.api.dto.response.CategoryResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryAssembler {


    public void hateoasLinkCategory(CategoryResponse response) {
        var link = methodOn(CategoryController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.update(null, response.getId())).withRel("update").withType("PUT"));
        response.add(linkTo(link.insert(null)).withRel("insert").withType("POST"));
        response.add(linkTo(link.findByName(response.getName())).withRel("findByName").withType("GET"));
           }
}
