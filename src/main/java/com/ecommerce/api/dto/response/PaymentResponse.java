package com.ecommerce.api.dto.response;

import com.ecommerce.api.enums.PaymentStatus;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse extends RepresentationModel<PaymentResponse> {
      private  Long id;
      private  BigDecimal amount;
      private  PaymentStatus status;
      private  LocalDateTime paymentDate;
}
