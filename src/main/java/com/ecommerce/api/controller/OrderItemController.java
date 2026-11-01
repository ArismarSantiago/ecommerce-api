package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.OrderItemsAssembler;
import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController implements com.ecommerce.api.controller.docs.OrderItemControllerDocs {

    @Autowired
    private OrderItemService service;

    @Autowired
    private OrderItemsAssembler assembler;



    @GetMapping
    @Override
    public ResponseEntity<List<OrderItemResponse>> findAll(){
        List<OrderItemResponse> list = service.findAll();
        list.forEach(assembler::hateoasLinkOrderItems);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrderItemResponse> findById(@PathVariable Long id){
        OrderItemResponse response = service.findById(id);
        assembler.hateoasLinkOrderItems(response);
        return ResponseEntity.ok(response);
    }
}
