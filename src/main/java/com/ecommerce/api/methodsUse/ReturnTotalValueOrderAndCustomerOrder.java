package com.ecommerce.api.methodsUse;

import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.CustomerRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReturnTotalValueOrderAndCustomerOrder {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;



public BigDecimal returnTotalValueOrder(OrderRequest request){
        BigDecimal totalValue = BigDecimal.valueOf(0);
        for (OrderItemRequest c: request.items()){
           Product product = findAProduct(c.productId());

          totalValue = totalValue.add(product.getPrice().multiply(BigDecimal.valueOf(c.quantity())));
        }

        return totalValue;
}

public Product findAProduct(Long productId){
        return productRepository.findById(productId)
            .orElseThrow(()-> new NotFoundExceptions("Produto não localizado", String.valueOf(productId), "Product"));

}
    public Customer returnCustomer(OrderRequest request){
        return customerRepository.findById(request.customerId())
                .orElseThrow(()-> new NotFoundExceptions("Usuario não localizado", String.valueOf(request.customerId()), "Customer"));
    }

}
