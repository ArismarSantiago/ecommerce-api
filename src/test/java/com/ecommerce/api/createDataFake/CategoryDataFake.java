package com.ecommerce.api.createDataFake;


import com.ecommerce.api.dto.request.CategoryRequest;
import com.ecommerce.api.entity.Category;
import com.ecommerce.api.entity.Product;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryDataFake {



    public static Category createFackCategory(int i){
        Category category = new Category();
        ReflectionTestUtils.setField(category, "id", Long.valueOf(i));
        category.setName(String.valueOf("CategoryName" + i));

        category.getProducts().forEach(ProductsFakeData.listFakeProducts(3)::add);

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
