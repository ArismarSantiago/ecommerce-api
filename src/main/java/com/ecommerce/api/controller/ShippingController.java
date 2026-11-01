package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.ShippingAssembler;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController implements com.ecommerce.api.controller.docs.ShippingControllerDocs {

    @Autowired
    private ShippingService service;
    @Autowired
    private ShippingAssembler assembler;

    @GetMapping
    @Override
    public ResponseEntity<List<ShippingResponse>> findAll(){
        List<ShippingResponse> list = service.findAll();
        list.forEach(assembler::hateoasLinksShipping);
        return ResponseEntity.ok(list);
    }

   @GetMapping("/trackingCode")
    @Override
    public ResponseEntity<ShippingResponse> findByTrackingCode(@RequestParam(name = "trackingCode") String trackingCode){
        ShippingResponse response = service.findByTrackingCode(trackingCode);
        assembler.hateoasLinksShipping(response);
        return ResponseEntity.ok(response);
    }

   @GetMapping("/{id}")
    @Override
    public ResponseEntity<ShippingResponse> findById(@PathVariable Long id){
        ShippingResponse response = service.findById(id);
       assembler.hateoasLinksShipping(response);
        return ResponseEntity.ok(response);
    }





}
