package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.ProductsFakeData;
import com.ecommerce.api.dto.mapper.ProductMapper;
import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.request.updateRequest.ProductUpdateRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.repository.CategoryRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService service;



    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                service,
                "mapper",
                Mappers.getMapper(ProductMapper.class)
        );
        assertNotNull(
                ReflectionTestUtils.getField(service, "mapper"),
                "O mapper não foi injetado no service"
        );
    }

    @Test
    void findAll() {
        List<Product> list = ProductsFakeData.listFakeProducts(5);

        when(repository.findAll()).thenReturn(list);

        List<ProductResponse> responses = service.findAll();

        assertNotNull(responses);

        ProductResponse response = responses.getFirst();

        assertEquals(0L, response.getId());
        assertEquals(BigDecimal.valueOf(25), response.getPrice());
    }

    @Test
    void findById() {
        Product product = ProductsFakeData.productFake(1);

        when(repository.findById(1L))
                .thenReturn(Optional.of(product));

        ProductResponse resp = service.findById(1L);

        assertNotNull(resp);
        assertEquals("1name", resp.getName());
        assertEquals(1L, resp.getId());
    }


    @Test
    void   insertProduct() {
        Product product = ProductsFakeData.productFake(1);
        ProductRequest request = ProductsFakeData.productFakeRequest(product);
        Category category = product.getCategory();
        when(repository.save(any(Product.class))).thenReturn(product);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        ProductResponse response = service.insertProduct(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("1name", request.name());
    }

    @Test
    void update() {
        Product product = ProductsFakeData.productFake(1);
        Product productUpdated = ProductsFakeData.productFake(2);
        ReflectionTestUtils.setField(productUpdated, "id", 1L);
        ProductUpdateRequest request = ProductsFakeData.productUpdateFake(productUpdated);

        when(repository.findById(1L)).thenReturn(Optional.of(product));
        when(repository.save(any(Product.class))).thenReturn(productUpdated);


        var response = service.update(request, product.getId());

        assertEquals(1L, response.getId());
        assertEquals("2name", response.getName());
    }
}