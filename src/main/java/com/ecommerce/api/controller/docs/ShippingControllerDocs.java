package com.ecommerce.api.controller.docs;

import com.ecommerce.api.dto.response.OrderItemResponse;
import com.ecommerce.api.dto.response.ShippingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

public interface ShippingControllerDocs {
    @Operation(summary = "find all", description = "find all Shipping", tags = "shipping",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))
            ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})

    @GetMapping
    ResponseEntity<List<ShippingResponse>> findAll();

    @Operation(summary = "find tracking code", description = "find a specific shipping using tracking code", tags = "shipping",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))
            ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})


    @GetMapping("/trackingCode")
    ResponseEntity<ShippingResponse> findByTrackingCode(@RequestParam(name = "trackingCode") String trackingCode);

    @Operation(summary = "find by id", description = "find shipping using id", tags = "shipping",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))
            ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})


    @GetMapping("/{id}")
    ResponseEntity<ShippingResponse> findById(@PathVariable Long id);

    @Operation(summary = "Update status shipping", description = "Update Status", tags = "shipping",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))
            ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PutMapping("update/{id}")
    ResponseEntity<ShippingResponse> updateStatusShipping(@PathVariable Long id, @RequestBody @Valid com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest request);

    @Operation(summary = "find shipped date", description = "find all shipping in specific date", tags = "shipping",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = OrderItemResponse.class))
            ), @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    ResponseEntity<List<ShippingResponse>> shippedAtBetween(@RequestParam(name = "date") LocalDate date);
}



