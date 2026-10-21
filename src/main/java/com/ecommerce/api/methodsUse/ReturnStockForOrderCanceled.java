package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.repository.OrderRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReturnStockForOrderCanceled {


    @Autowired
    private ProductRepository repository;


    public void productReturnStock(List<OrderItem> orderItems){
        for (OrderItem c: orderItems){
            Product product = c.getProduct();
            product.setStock(c.getProduct().getStock() + c.getQuantity());
            repository.save(product);
        }
    }

}
