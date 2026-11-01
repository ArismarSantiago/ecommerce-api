package com.ecommerce.api.createDataFake;

import com.ecommerce.api.dto.request.ShippingRequest;
import com.ecommerce.api.dto.request.updateRequest.ShippingStatusUpdateRequest;
import com.ecommerce.api.entity.Shipping;
import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShippingFakeData {

    public static Shipping shippingFake(int i){
        Shipping shipping = new Shipping();
        ReflectionTestUtils.setField(shipping, "id", Long.valueOf(i));
        ReflectionTestUtils.setField(shipping, "shippedAt", LocalDateTime.of(2022, 10,10,10,10));
        shipping.setStatus(ShippingStatus.SHIPPED);
        shipping.setTrackingCode(i+"101010");
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

    public static ShippingRequest requestFake(){
        return new ShippingRequest(new Address("street", "number",
                "neighborhood", "city",
                "state", "zipCode"));
    }

    public static ShippingStatusUpdateRequest shippingStatusUpdateRequestFake(Long id, ShippingStatus status){
        return new ShippingStatusUpdateRequest(id, status);
    }
}
