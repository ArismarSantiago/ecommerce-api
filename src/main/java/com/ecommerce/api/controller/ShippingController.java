package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.ShippingAssembler;
import com.ecommerce.api.dto.response.ShippingResponse;
import com.ecommerce.api.service.ShippingService;
import com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/{date}")
    @Override
    public ResponseEntity<List<ShippingResponse>> shippedAtBetween(@RequestParam(name = "date")LocalDate date){
        List<ShippingResponse> responses = service.findByShippedAtBetween(date);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("update/{id}")
    @Override
    public ResponseEntity<ShippingResponse> updateStatusShipping(@PathVariable Long id, @RequestBody @Valid ShippingStatusUpdateRequest request){
        ShippingResponse response = service.updateStatusShipping(request, id);

        return ResponseEntity.ok(response);
    }




}
