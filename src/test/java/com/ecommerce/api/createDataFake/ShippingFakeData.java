package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.ShippingRequest;
import com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShippingFakeData {

    public static Shipping shippingFake(int i){
        Shipping shipping = new Shipping();
        shipping.setStatus(ShippingStatus.WAITING_PAYMENT);
        shipping.setShippedAt(LocalDateTime.now());
        shipping.setId(Long.valueOf(i));
        shipping.setTrackingCode(i+"101010"+i);
        shipping.setAddress(new Address("street"+i, "number"+i,
                "neighborhood"+i, "city"+i,
                "state"+i, "zipCode"+i));
        return shipping;
    }


    public static List<Shipping> shippingListFake(int number){
        List<Shipping> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(shippingFake(i));
        }
        return list;
    }

    public static ShippingRequest requestFake(Shipping shipping){
        return new ShippingRequest(shipping.getAddress());
    }

    public static ShippingStatusUpdateRequest shippingStatusUpdateRequestFake(Shipping shipping, ShippingStatus status){
        return new ShippingStatusUpdateRequest(shipping.getId(), status);
    }
}
