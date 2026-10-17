package com.ecommerce.api.controller.docs;

import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.dto.response.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface PaymentControllerDocs {
    @Operation(summary = "Find all payments", description = "find all payments"
            , tags = "payment",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})

    @GetMapping
    ResponseEntity<List<PaymentResponse>> findAll();

    @Operation(summary = "find payment by id", description = "find aa specific payment using id"
            , tags = "payment",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})

    @GetMapping("/{id}")
    ResponseEntity<PaymentResponse> findById(@PathVariable Long id);

    @Operation(summary = "find payment by date", description = "find payment using date"
            , tags = "payment",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})

    @GetMapping("/date")
    ResponseEntity<List<PaymentResponse>> findByPaymentDateBetween(LocalDate date);

    @Operation(summary = "Update status payment", description = "update payment"
            , tags = "payment",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = PaymentResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})

    @PutMapping("/{id}")
    ResponseEntity<PaymentResponse> updateStatusPayment(@RequestBody @Valid PaymentRequest request, @PathVariable Long id);
}
