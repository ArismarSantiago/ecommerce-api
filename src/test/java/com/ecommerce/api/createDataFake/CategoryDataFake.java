package com.ecommerce.api.createDataFake;


import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataFake {



    public static Category createFackCategory(int i){
        Category category = new Category();
        category.setId(Long.valueOf(i));
        category.setName(String.valueOf("CategoryName" + i));

        category.setProducts(ProductsFakeData.listFakeProducts(i));

        return category;
    }

    public static List<Category> list(int number){
        List<Category> listCategory = new ArrayList<>();
        for (int i = 0; i<number; i++){
            listCategory.add(createFackCategory(i));
        }
        return listCategory;
    }

    public static CategoryRequest requestFake(Category category){
        return new CategoryRequest(category.getName());
    }
}
