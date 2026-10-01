package com.ecommerce.api.controller.docs;

import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerControllerDocs {
    @Operation(summary = "Find customer by id", description = "Find a specific customer using id",
            tags = "find customer by id",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> findById(@PathVariable Long id);

    @Operation(summary = "Find all customers", description = "Find all customers",
            tags = "find all customers",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping
    ResponseEntity<List<CustomerResponse>> findAll();

    @Operation(summary = "Find customers by name", description = "Find all customers using name",
            tags = "find customer by name",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/name")
    ResponseEntity<List<CustomerResponse>> findByName(@RequestParam(name = "name") String name);

    @Operation(summary = "Find customer by email", description = "Find a specific customer using email",
            tags = "find customer by email",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/email")
    ResponseEntity<CustomerResponse> findByEmail(@RequestParam(name = "email") String email);

    @Operation(summary = "Find customer by PhoneNumber", description = "Find customers using PhoneNumber",
            tags = "find customer by phoneNumber",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/phoneNumber")
    ResponseEntity<List<CustomerResponse>> findByPhoneNumber(@RequestParam(name = "phoneNumber") String phoneNumber);

    @Operation(summary = "Insert customer", description = "Insert a new Customer in database",
            tags = "insert customers",
            responses = {@ApiResponse(
                    description = "Created",
                    responseCode = "201",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PostMapping
    ResponseEntity<CustomerResponse> insert(CustomerRequest request);





    @Operation(summary = "Update customer", description = "Update data for customers",
            tags = "update customers",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CustomerResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PutMapping("/{id}")
    ResponseEntity<CustomerResponse> update(@Valid @RequestBody CustomerRequest request, @PathVariable Long id);
}
