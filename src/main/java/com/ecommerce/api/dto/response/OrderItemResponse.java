package com.ecommerce.api.dto.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class OrderItemResponse extends RepresentationModel<OrderItemResponse> {
        private Long id;
     private Integer quantity;
     private BigDecimal unitPrice;
     private BigDecimal subTotal;

}
