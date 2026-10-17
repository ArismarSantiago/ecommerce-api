package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.PaymentAssembler;
import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.dto.response.PaymentResponse;
import com.ecommerce.api.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController implements com.ecommerce.api.controller.docs.PaymentControllerDocs {

    @Autowired
    private PaymentService service;

    @Autowired
    private PaymentAssembler assembler;




    @GetMapping
    @Override
    public ResponseEntity<List<PaymentResponse>> findAll(){
        List<PaymentResponse> list = service.findAll();
        list.forEach(assembler::hateoasLinkPayment);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<PaymentResponse> findById(@PathVariable Long id){
        PaymentResponse response = service.findById(id);
        assembler.hateoasLinkPayment(response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{date}")
    @Override
    public ResponseEntity<List<PaymentResponse>> findByPaymentDateBetween(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
        List<PaymentResponse> list = service.findByCreatedAtBetween(date);
        list.forEach(assembler::hateoasLinkPayment);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<PaymentResponse>updateStatusPayment(@RequestBody @Valid PaymentRequest request, @PathVariable Long id){
        PaymentResponse response = service.updateStatusPayment(request, id);
        assembler.hateoasLinkPayment(response);
        return ResponseEntity.ok(response);
    }
}
