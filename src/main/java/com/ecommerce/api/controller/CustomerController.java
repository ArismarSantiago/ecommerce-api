package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.CustomerAssembler;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController implements com.ecommerce.api.controller.docs.CustomerControllerDocs {

    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerAssembler assembler;

    @GetMapping
    @Override
    public ResponseEntity<List<CustomerResponse>> findAll(){
        List<CustomerResponse> list = service.findAll();
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id){
        CustomerResponse response = service.findById(id);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/name")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByName(@RequestParam(name = "name") String name){
        List<CustomerResponse> list = service.findByName(name);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }


    @GetMapping("/email")
    @Override
    public ResponseEntity<CustomerResponse> findByEmail(@RequestParam(name = "email") String email){
        CustomerResponse response = service.findByEmail(email);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/phoneNumber")
    @Override
    public ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber){
        List<CustomerResponse> list = service.findByPhoneNumber(phoneNumber);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }



    @PostMapping
    @Override
    public ResponseEntity<CustomerResponse> insert(CustomerRequest request){
        CustomerResponse response = service.insert(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        assembler.toModel(response);
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CustomerResponse> update(@Valid @RequestBody CustomerRequest request, @PathVariable Long id){
        CustomerResponse response = service.update(request, id);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }

}
