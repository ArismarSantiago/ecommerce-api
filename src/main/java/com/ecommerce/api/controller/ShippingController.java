package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.ShippingAssembler;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    @Autowired
    private ShippingService service;
    @Autowired
    private ShippingAssembler assembler;

    @GetMapping
    public ResponseEntity<List<ShippingResponse>> findAll(){
        List<ShippingResponse> list = service.findAll();
        list.forEach(assembler::hateoasLinksShipping);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/trackingCode")
    public ResponseEntity<ShippingResponse> findByTrackingCode(@RequestParam(name = "trackingCode") String trackingCode){
        ShippingResponse response = service.findByTrackingCode(trackingCode);
        assembler.hateoasLinksShipping(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShippingResponse> findById(@PathVariable Long id){
        ShippingResponse response = service.findById(id);
       assembler.hateoasLinksShipping(response);
        return ResponseEntity.ok(response);
    }





}
