package com.ecommerce.api.dto.response;

import com.ecommerce.api.enums.ShippingStatus;
import com.ecommerce.api.valueObject.Address;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ShippingResponse extends RepresentationModel<ShippingResponse> {
       private Long id;
       private ShippingStatus status;
       private String trackingCode;
       private LocalDateTime shippedAt;
       private Address address;
}
