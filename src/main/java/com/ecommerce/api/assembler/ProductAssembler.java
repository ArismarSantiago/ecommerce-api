package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.ProductController;
import com.ecommerce.api.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler {


    public ProductResponse hateoasLinkProduct(ProductResponse response) {
        var link = methodOn(ProductController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.update(null, response.getId())).withRel("update").withType("PUT"));
        response.add(linkTo(link.insertProduct(null)).withRel("insertProducts").withType("POST"));
        response.add(linkTo(link.findByName(response.getName())).withRel("findByName").withType("GET"));
        response.add(linkTo(link.findByPrice(BigDecimal.ONE, response.getPrice())).withRel("findByPrice").withType("GET"));

        return response;
           }
}
