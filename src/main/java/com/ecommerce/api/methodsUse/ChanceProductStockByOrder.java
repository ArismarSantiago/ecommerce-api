package com.ecommerce.api.methodsUse;

import com.ecommerce.api.entity.Product;
import com.ecommerce.api.enums.ProductStatus;
import com.ecommerce.api.exceptions.ProductNotAvailable;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChanceProductStockByOrder {

    @Autowired
    private ProductRepository repository;




    public void ChangeStockProducts(Product product, int stock){
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
}
