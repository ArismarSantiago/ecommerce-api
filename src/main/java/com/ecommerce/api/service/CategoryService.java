package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.CategoryMapper;
import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryResponse> findAll(){
        return repository.findAll().stream()
                .map(CategoryMapper::toResponse).toList();
    }

    public Category findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Id não encontrado", String.valueOf(id), "Category"));
    }

    public CategoryResponse findById(Long id){
        Category category = findByEntityId(id);

        return CategoryMapper.toResponse(category);
    }

    public CategoryResponse findByName(String name){
        Category category = repository.findByName(name);
        if (category == null){
            throw new NotFoundExceptions("Nome não localizado",name, "Category");
        }
        return CategoryMapper.toResponse(category);
    }

    public CategoryResponse update(CategoryRequest request, Long id){
        Category entity = findByEntityId(id);
        if (repository.existsByName(request.name()) && !request.name().trim().equals(entity.getName().trim())){
            throw new DuplicateExceptions("Essa categoria já se encontra cadastrada", request.name());
        }
        Category updated = CategoryMapper.update(request, id);
        Category saveUpdate = repository.save(updated);
        return CategoryMapper.toResponse(saveUpdate);
    }

    public CategoryResponse insert(CategoryRequest request){
        if (repository.existsByName(request.name())){
            throw new DuplicateExceptions("Essa categoria ja existe", request.name());
        }
        Category entity = CategoryMapper.toEntity(request);
        Category saveEntity = repository.save(entity);
        return CategoryMapper.toResponse(saveEntity);
    }
}
