package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.ShippingController;
import com.ecommerce.api.dto.response.ShippingResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShippingAssembler {


    public void hateoasLinksShipping(ShippingResponse response){
        var link = methodOn(ShippingController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.findByTrackingCode(response.getTrackingCode())).withRel("GET").withType("findByTrackingCode"));

    }
}
