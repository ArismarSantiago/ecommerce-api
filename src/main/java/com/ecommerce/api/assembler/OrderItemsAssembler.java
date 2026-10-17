package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.OrderItemController;
import com.ecommerce.api.dto.response.OrderItemResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemsAssembler {


    public void hateoasLinkOrderItems(OrderItemResponse response) {
        var link = methodOn(OrderItemController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
           }
}
