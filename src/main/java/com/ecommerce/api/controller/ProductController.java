package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.ProductAssembler;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController implements com.ecommerce.api.controller.docs.ProductControllerDocs {

    @Autowired
    private ProductService service;

    @Autowired
    private final ProductAssembler assembler;

    public ProductController(ProductAssembler assembler) {
        this.assembler = assembler;
    }


    @GetMapping
    @Override
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> list = service.findAll();
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        ProductResponse response = service.findById(id);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }

   @GetMapping("/name")
    @Override
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name){
        List<ProductResponse> list = service.findByName(name);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{price}/{priceFinal}")
    @Override
    public ResponseEntity<List<ProductResponse>> findByPrice(
            @RequestParam(name = "price") BigDecimal price, @RequestParam(name = "priceFinal") BigDecimal priceFinal){
        List<ProductResponse> list = service.findByPrice(price, priceFinal);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }
    @PostMapping
    @Override
    public ResponseEntity<ProductResponse> insertProduct(@Valid @RequestBody ProductRequest request){
        ProductResponse response = service.insertProduct(request);

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
    public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest request, @PathVariable Long id){
        ProductResponse response = service.update(request, id);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }

}
