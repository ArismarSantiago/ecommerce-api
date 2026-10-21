package com.ecommerce.api.methodsUse;

import com.ecommerce.api.dto.mapper.OrderItemsMapper;
import com.ecommerce.api.dto.request.OrderItemRequest;
import com.ecommerce.api.dto.request.OrderRequest;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.OrderItem;
import com.ecommerce.api.entity.Product;
import com.ecommerce.api.enums.OrderItemStatus;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.OrderItemRepository;
import com.ecommerce.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateOrderItems {


    @Autowired
    private OrderItemRepository repository;
    @Autowired
    private ProductRepository productrepository;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    private final ChanceProductStockByOrder changeStock;

    public CreateOrderItems(ChanceProductStockByOrder changeStock) {
        this.changeStock = changeStock;
    }


    public List<OrderItem> createOrderItems(OrderRequest request){
        List<OrderItem> items = new ArrayList<>();
        for(OrderItemRequest c: request.items()){
            Product product = findProduct(c.productId());
            changeStock.ChangeStockProductsOrder(product, c.quantity());
            OrderItem item = orderItemsMapper.toEntity(c, product);
            item.setStatus(OrderItemStatus.WAITING_PAYMENT);
            item.setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(c.quantity())));
            items.add(item);
        }
        return items;
    }

    public void saveOrderItems(Order order){
        for (OrderItem c: order.getOrderItems()){
            c.setOrder(order);
            repository.save(c);
        }

    }
    public Product findProduct(Long id){
        return productrepository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Produto não encontrado", String.valueOf(id), "Product"));
    }
}
