package com.ecommerce.api.controller;


import com.ecommerce.api.assembler.OrderAssembler;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;
import com.ecommerce.api.dto.response.OrderResponse;
import com.ecommerce.api.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController implements com.ecommerce.api.controller.docs.OrderControllerDocs {

    @Autowired
    private OrderService service;
    @Autowired
    private OrderAssembler assembler;




    @GetMapping
    @Override
    public ResponseEntity<List<OrderResponse>> findAll(){
        List<OrderResponse> list = service.findAll();
        list.forEach(assembler::hateoasLinkOrder);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id){
        OrderResponse response = service.findById(id);
        assembler.hateoasLinkOrder(response);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{date}")
    @Override
    public ResponseEntity<List<OrderResponse>> findByDateBetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<OrderResponse> list = service.findByCreatedDateBetween(date);
        list.forEach(assembler::hateoasLinkOrder);
        return ResponseEntity.ok(list);
    }


    @PostMapping
    @Override
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request){
        OrderResponse response = service.createOrder(request);
        assembler.hateoasLinkOrder(response);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<OrderResponse> updateStatusOrder(@RequestBody @Valid OrderUpdateStatusRequest requestStatus, @PathVariable Long id){
        OrderResponse response = service.updateStatusOrder(requestStatus, id);
        assembler.hateoasLinkOrder(response);
        return ResponseEntity.ok(response);

    }
}
