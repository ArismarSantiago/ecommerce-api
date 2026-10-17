package com.ecommerce.api.dto.response;

import com.ecommerce.api.valueObject.Address;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CustomerResponse extends RepresentationModel<CustomerResponse> {
    private  Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Address address;

}
