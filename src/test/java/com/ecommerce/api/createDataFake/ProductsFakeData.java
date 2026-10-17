package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.ProductRequest;
import com.ecommerce.api.dto.request.updateRequest.ProductUpdateRequest;
import com.ecommerce.api.entity.Product;

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
        product.setCategory(CategoryDataFake.createFackCategory());
        product.setStock(10);

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
}
