package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.ProductMapper;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.exceptions.DuplicateNameExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Id não encontrado", id, "Product"));
    }

    public ProductResponse findById(Long id){
        Product product = findByEntityId(id);
        return ProductMapper.toResponse(product);
    }

    public List<ProductResponse> findByName(String name){
        return repository.findByName(name).stream().map(ProductMapper::toResponse).toList();
    }
    public List<ProductResponse> findByPrice(BigDecimal initialPrice, BigDecimal finalePrice){
        return repository.findByPriceBetween(initialPrice, finalePrice).stream().map(ProductMapper::toResponse).toList();
    }


    public ProductResponse insertProduct(ProductRequest request){
        if (repository.existsByName(request.name().trim())){
            throw  new DuplicateNameExceptions("Esse produto já esta cadastrado", request.name());
        }
        Product entity = ProductMapper.toEntity(request);
        Product saveEntity = repository.save(entity);

        return ProductMapper.toResponse(saveEntity);
    }

}
