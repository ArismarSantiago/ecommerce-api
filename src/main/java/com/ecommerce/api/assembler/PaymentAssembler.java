package com.ecommerce.api.assembler;

import com.ecommerce.api.controller.PaymentController;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.dto.response.PaymentResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaymentAssembler {


    public void hateoasLinkPayment(PaymentResponse response) {
        var link = methodOn(PaymentController.class);
        response.add(linkTo(link.findAll()).withRel("findAll").withType("GET"));
        response.add(linkTo(link.findById(response.getId())).withRel("findById").withType("GET"));
        response.add(linkTo(link.updateStatusPayment(null, response.getId())).withRel("updateStatusPayment").withType("PUT"));
        response.add(linkTo(link.findByPaymentDateBetween(response.getPaymentDate().toLocalDate())).withRel("findByPaymentDateBetween").withType("GET"));
           }
}
