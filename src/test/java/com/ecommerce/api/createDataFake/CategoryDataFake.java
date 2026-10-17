package com.ecommerce.api.createDataFake;


import com.ecommerce.api.entity.Category;

import java.util.ArrayList;

public class CategoryDataFake {



    public static Category createFackCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName(String.valueOf("CategoryName"));

        return category;
    }
}
