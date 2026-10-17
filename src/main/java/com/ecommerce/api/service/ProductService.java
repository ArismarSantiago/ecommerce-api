package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.ProductMapper;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.request.updateRequest.ProductUpdateRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;


    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> findAll(){
        return repository.findAll()
                .stream().map(productMapper::toResponse).toList();
    }

    public Product findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Id não encontrado", String.valueOf(id), "Product"));
    }

    public ProductResponse findById(Long id){
        Product product = findByEntityId(id);
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> findByName(String name){
        return repository.findByName(name).stream().map(productMapper::toResponse).toList();
    }
    public List<ProductResponse> findByPrice(BigDecimal initialPrice, BigDecimal finalePrice){
        return repository.findByPriceBetween(initialPrice, finalePrice).stream().map(productMapper::toResponse).toList();
    }


    public ProductResponse insertProduct(ProductRequest request){
        if (repository.existsByName(request.name().trim())){
            throw  new DuplicateExceptions("Esse produto já esta cadastrado", request.name());
        }
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(()-> new NotFoundExceptions("Categoria não encontrada", String.valueOf(request.categoryId()), "Category"));

        Product entity = productMapper.toEntity(request);
        entity.setCategory(category);
        Product saveEntity = repository.save(entity);
        return  productMapper.toResponse(saveEntity);
    }

    public ProductResponse update(ProductUpdateRequest request, Long id){
        Product product = findByEntityId(id);
        if (product.getStatus().equals(request.status())){
            throw new DuplicateExceptions("O produto já se encontra com esse status", product.getName());
        }
        if (repository.existsByName(request.name()) && !request.name().trim().equals(product.getName().trim())){
            throw new DuplicateExceptions("Esse nome ja esta cadastrado", product.getName());
        }
        Product productUpdated = productMapper.updateProduct(request, product);
        Product saveProduct = repository.save(productUpdated);

        return productMapper.toResponse(saveProduct);
    }


}
