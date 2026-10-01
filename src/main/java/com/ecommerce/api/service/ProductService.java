package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.ProductMapper;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.exceptions.DuplicateExceptions;
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
                .orElseThrow(()-> new NotFoundExceptions("Id não encontrado", String.valueOf(id), "Product"));
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
            throw  new DuplicateExceptions("Esse produto já esta cadastrado", request.name());
        }
        Product entity = ProductMapper.toEntity(request);
        Product saveEntity = repository.save(entity);

        return ProductMapper.toResponse(saveEntity);
    }

    public ProductResponse update(ProductRequest request, Long id){
        Product product = findByEntityId(id);
        if (repository.existsByName(request.name()) && !request.name().trim().equals(product.getName().trim())){
            throw new DuplicateExceptions("Esse nome ja esta cadastrado", product.getName());
        }
        Product productUpdated = ProductMapper.update(request, id);
        Product saveProduct = repository.save(productUpdated);

        return ProductMapper.toResponse(saveProduct);
    }

}
