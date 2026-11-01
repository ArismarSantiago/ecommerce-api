package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.request.updateRequest.ProductUpdateRequest;
import com.ecommerce.api.dto.response.ProductResponse;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductsFakeData {



    public static Product productFake(int i){
        Product product = new Product();

        product.setId(Long.valueOf(i));
        product.setName(String.valueOf(i + "name"));
        product.setPrice(BigDecimal.valueOf(25));
        product.setDescription("description");
        product.setCategory(new Category(1L, "category", new ArrayList<>()));
        product.setStock(10);
        product.setStatus(ProductStatus.IN_STOCK);

        return product;
    }
    public static ProductRequest productFakeRequest(Product product){
    return new ProductRequest(product.getName(),product.getDescription(), product.getPrice(),
                product.getStock(), product.getCategory().getId(), product.getStatus());
    }

    public static ProductUpdateRequest productUpdateFake(Product product){
        return new ProductUpdateRequest(product.getName(), product.getDescription(), product.getPrice(), product.getStatus(), product.getStock(), product.getCategory().getId());
    }


    public static List<Product> listFakeProducts(int number){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i<number ; i++){
            products.add(productFake(i));
        }
        return products;
    }

    public static ProductResponse fakeProductResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setName(product.getName());
        response.setPrice(product.getPrice());
        response.setId(product.getId());
        response.setStatus(product.getStatus());
        response.setDescription(product.getDescription());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }
}
