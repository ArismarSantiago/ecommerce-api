package com.ecommerce.api.controller.docs;

import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.dto.request.updateRequest.OrderUpdateStatusRequest;
import com.ecommerce.api.dto.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

public interface OrderControllerDocs {
    @Operation(summary = "Find all orders", description = "find all orders", tags = "order"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = OrderResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping()
    ResponseEntity<List<OrderResponse>> findAll();

    @Operation(summary = "Find by id", description = "find a order using id", tags = "order",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<OrderResponse> findById(@PathVariable Long id);

    @Operation(summary = "Find order date", description = "find orders using date", tags = "order",
            responses = {@ApiResponse(
                    description = "success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/date")
    ResponseEntity<List<OrderResponse>> findByDateBetween(@PathVariable(name = "date") LocalDate date);

    @Operation(
            summary = "insert Order Items", description = "Insert list order Items"
            , tags = "order"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = OrderResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PostMapping
    ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request);

    @Operation(summary = "Update order", description = "Update order status", tags = "order"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = OrderResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PutMapping("/{}")
    ResponseEntity<OrderResponse> updateStatusOrder(@RequestBody @Valid OrderUpdateStatusRequest requestStatus, @PathVariable Long id);
}
