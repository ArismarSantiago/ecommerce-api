package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.CustomerController;
import com.ecommerce.api.dto.response.CustomerResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerAssembler {


    public void hateoasLinkCustomer(CustomerResponse response) {
        var link = methodOn(CustomerController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.update(null, response.getId())).withRel("update").withType("PUT"));
        response.add(linkTo(link.insert(null)).withRel("insert").withType("POST"));
        response.add(linkTo(link.findByEmail(response.getEmail())).withRel("findByEmail").withType("GET"));
        response.add(linkTo(link.findByPhoneNumber(response.getPhoneNumber())).withRel("findByPhoneNumber").withType("GET"));
        response.add(linkTo(link.findByName(response.getName())).withRel("findByName").withType("GET"));
           }
}
