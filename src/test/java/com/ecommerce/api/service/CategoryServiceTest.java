package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.CategoryDataFake;
import com.ecommerce.api.dto.mapper.CategoryMapper;
import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.dto.response.CategoryResponse;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryService service;

    @BeforeEach
            void setUp() {
        ReflectionTestUtils.setField(
                service,
                "mapper",
                Mappers.getMapper(CategoryMapper.class)
        );
    }

    @Test
    void findAll() {
        List<Category> list = CategoryDataFake.list(3);
        when(repository.findAll()).thenReturn(list);

        List<CategoryResponse> responses = service.findAll();

        assertNotNull(responses);

        verify(repository, times(1)).findAll();
    }

    @Test
    void findByEntityId() {
        Category category = CategoryDataFake.createFackCategory(1);

        when(repository.findById(1L)).thenReturn(Optional.of(category));
        CategoryResponse response = service.findById(1L);

        verify(repository, times(1)).findById(1L);
    }

    @Test
    void update() {
        Category category = CategoryDataFake.createFackCategory(1);
        Category categoryUpdated = CategoryDataFake.createFackCategory(2);
        CategoryRequest request = CategoryDataFake.requestFake(categoryUpdated);
        when(repository.findById(1L)).thenReturn(Optional.of(category));
        when(repository.save(any(Category.class))).thenReturn(categoryUpdated);
        CategoryResponse response = service.update(request, 1L);

        assertEquals("CategoryName2", response.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void insert() {
        Category category = CategoryDataFake.createFackCategory(1);
        CategoryRequest request = CategoryDataFake.requestFake(category);
        when(repository.save(any(Category.class))).thenReturn(category);
        CategoryResponse response = service.insert(request);

        verify(repository, times(1)).save(any());

        assertEquals(1L, response.getId());
        assertEquals("CategoryName1", response.getName());

    }
}