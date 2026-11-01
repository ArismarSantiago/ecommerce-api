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

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper mapper;


    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductResponse> findAll(){
        return repository.findAll()
                .stream().map(mapper::toResponse).toList();
    }

    public Product findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Id não encontrado", String.valueOf(id), "Product"));
    }

    public ProductResponse findById(Long id){
        Product product = findByEntityId(id);
        return mapper.toResponse(product);
    }

    public List<ProductResponse> findByName(String name){
        return repository.findByName(name).stream().map(mapper::toResponse).toList();
    }
    public List<ProductResponse> findByPrice(BigDecimal initialPrice, BigDecimal finalePrice){
        return repository.findByPriceBetween(initialPrice, finalePrice).stream().map(mapper::toResponse).toList();
    }


    public ProductResponse insertProduct(ProductRequest request){
        if (repository.existsByName(request.name().trim())){
            throw  new DuplicateExceptions("Esse produto já esta cadastrado", request.name());
        }
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(()-> new NotFoundExceptions("Categoria não encontrada", String.valueOf(request.categoryId()), "Category"));

        Product entity = mapper.toEntity(request);
        entity.setCategory(category);
        Product saveEntity = repository.save(entity);
        return  mapper.toResponse(saveEntity);
    }

    public ProductResponse update(ProductUpdateRequest request, Long id){
        Product product = findByEntityId(id);
        if (repository.existsByName(request.name()) && !request.name().trim().equals(product.getName().trim())){
            throw new DuplicateExceptions("Esse nome ja esta cadastrado", product.getName());
        }
        Product productUpdated = mapper.updateProduct(request, product);
        Product saveProduct = repository.save(productUpdated);

        return mapper.toResponse(saveProduct);
    }


}
