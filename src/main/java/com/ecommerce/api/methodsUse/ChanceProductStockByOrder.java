package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.enums.ProductStatus;
import com.ecommerce.api.exceptions.ProductNotAvailable;
import com.ecommerce.api.repository.OrderItemRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChanceProductStockByOrder {

    @Autowired
    private ProductRepository repository;





    public void ChangeStockProductsOrder(Product product, int stock){
        if (product.getStock() < stock){
            throw new ProductNotAvailable("Produto sem estoque ou sem quantidade solicitada",
                    product, "Diminua a quantidade do pedido se ainda tiver estoque disponivel");
        }
        if (product.getStatus().equals(ProductStatus.DISCONTINUED)){
            throw new ProductNotAvailable("Esse produto se encontra descntinuado até o momento", product,
                    "Esse produto foi descontinuado a venda por tempo indeterminado");
        }

        product.setStock(product.getStock() - stock);
        repository.save(product);
    }

    public void returnProductQuantityStock(List<OrderItem> orderItems){
        for (OrderItem c: orderItems){
            Product product = c.getProduct();
            product.setStock(c.getProduct().getStock() + c.getQuantity());
            repository.save(product);
        }
    }
}
