package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.ProductAssembler;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
public class ProductControllerDocs implements com.ecommerce.api.controller.docs.ProductControllerDocs {

    @Autowired
    private ProductService service;

    @Autowired
    private final ProductAssembler assembler;

    public ProductControllerDocs(ProductAssembler assembler) {
        this.assembler = assembler;
    }


    @Operation(
            summary = "Find All products",
            description = "Find all products added in database",
            tags = "Find All Products",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping
    @Override
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> list = service.findAll();
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Find By Id product",
            description = "Find a specific product by id",
            tags = "findById product",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id){
        ProductResponse response = service.findById(id);
        assembler.toModel(response);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Find By Name Product", description = "Find Product Using Name",
            tags = "find by name product"
            , responses = {@ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ProductResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/name")
    @Override
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name){
        List<ProductResponse> list = service.findByName(name);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Find product by price", description = "Find products using the starting price and the ending price.",
            tags = "find by price product"
            , responses = {@ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ProductResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/{price}/{priceFinal}")
    @Override
    public ResponseEntity<List<ProductResponse>> findByPrice(
            @RequestParam(name = "price") BigDecimal price, @RequestParam(name = "priceFinal") BigDecimal priceFinal){
        List<ProductResponse> list = service.findByPrice(price, priceFinal);
        list.forEach(assembler::toModel);
        return ResponseEntity.ok(list);
    }
    @Operation(summary = "Insert Product", description = "Insert products",
            tags = "Insert product"
            , responses = {@ApiResponse(
            description = "Success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ProductResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
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


}
