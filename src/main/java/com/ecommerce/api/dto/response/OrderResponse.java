package com.ecommerce.api.dto.response;

import com.ecommerce.api.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class OrderResponse extends RepresentationModel<OrderResponse> {
        private Long id;
        private LocalDate createdAt;
        private OrderStatus status;
        private BigDecimal total;
        private String customerName;
}
