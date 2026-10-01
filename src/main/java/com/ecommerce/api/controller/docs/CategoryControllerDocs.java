package com.ecommerce.api.controller.docs;

import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CategoryControllerDocs {
    @Operation(summary = "find all category", description = "find all category"
            , tags = "findAll category",
            responses = {@ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CategoryResponse.class))
            ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping
    ResponseEntity<List<CategoryResponse>> findAll();

    @Operation(summary = "find category by name", description = "find a specific category using name",
            tags = "category"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/name")
    ResponseEntity<CategoryResponse> findByName(@RequestParam(name = "name") String name);

    @Operation(summary = "find category by id", description = "find a specific category using id",
            tags = "category"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @GetMapping("/{id}")
    ResponseEntity<CategoryResponse> findById(@PathVariable Long id);

    @Operation(summary = "Insert Category", description = "Insert a new category",
            tags = "insert category"
            , responses = {@ApiResponse(
            description = "Created",
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PostMapping
    ResponseEntity<CategoryResponse> insert(CategoryRequest request);

    @Operation(summary = "Update category", description = "update category",
            tags = "update category"
            , responses = {@ApiResponse(
            description = "success",
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))),

            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)})
    @PutMapping("/{id}")
    ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id);
}
