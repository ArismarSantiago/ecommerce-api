package com.ecommerce.api.methodsUse;

import com.ecommerce.api.dto.mapper.ShippingMapper;
import com.ecommerce.api.dto.request.ShippingRequest;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CreateShipping {

    @Autowired
    private ShippingRepository repository;

    @Autowired
    private ShippingMapper shippingMapper;




    public void createShipping(ShippingRequest request, Order order){
        Shipping shipping = shippingMapper.toEntity(request);
        shipping.setOrder(order);
        shipping.setTrackingCode("WAITING_PAYMENT");

        repository.save(shipping);
    }

}
