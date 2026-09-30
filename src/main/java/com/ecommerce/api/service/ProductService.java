package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.ProductMapper;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductResponse> findAll(){
        return repository.findAll()
                .stream().map(ProductMapper::toResponse).toList();
    }

    public Product findByEntityId(Long id){
        return repository.findById(id).orElseThrow()
    }


}
