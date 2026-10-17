package com.ecommerce.api.dto.mapper;

import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.dto.response.PaymentResponse;
import com.ecommerce.api.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentResponse toResponse(Payment payment);

    Payment updatePaymentStatus(PaymentRequest request);



}