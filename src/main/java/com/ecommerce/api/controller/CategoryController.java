package com.ecommerce.api.controller;

import com.ecommerce.api.assembler.CategoryAssembler;
import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import com.ecommerce.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController implements com.ecommerce.api.controller.docs.CategoryControllerDocs {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryAssembler assembler;


    @GetMapping
    @Override
    public ResponseEntity<List<CategoryResponse>> findAll(){
        List<CategoryResponse> list = service.findAll();
      list.forEach(assembler::hateoasLinkCategory);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/name")
    @Override
    public ResponseEntity<CategoryResponse> findByName(@RequestParam(name = "name") String name){
        CategoryResponse response = service.findByName(name);
        assembler.hateoasLinkCategory(response);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        CategoryResponse response = service.findById(id);
        assembler.hateoasLinkCategory(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Override
    public ResponseEntity<CategoryResponse> insert(@Valid @RequestBody CategoryRequest request){
        CategoryResponse response = service.insert(request);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        assembler.hateoasLinkCategory(response);
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id){
        CategoryResponse response = service.update(request, id);
        assembler.hateoasLinkCategory(response);
        return ResponseEntity.ok(response);
    }
}
