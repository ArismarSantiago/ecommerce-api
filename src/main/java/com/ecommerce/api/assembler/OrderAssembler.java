package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.OrderController;
import com.ecommerce.api.dto.response.OrderResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderAssembler {



    public void hateoasLinkOrder(OrderResponse response){

        var link = methodOn(OrderController.class);

        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.updateStatusOrder(null, response.getId())).withRel("UpdateStatusOrder").withType("PUT"));
        response.add(linkTo(link.findByDateBetween(response.getCreatedAt())).withRel("findByCreateAt").withType("GET"));
        response.add(linkTo(link.createOrder(null)).withRel("create").withType("POST"));
    }
}
