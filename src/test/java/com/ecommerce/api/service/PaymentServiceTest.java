package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.PaymentDataFake;
import com.ecommerce.api.dto.mapper.PaymentMapper;
import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.dto.response.PaymentResponse;
import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository repository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private PaymentService service;

    @BeforeEach
    void up(){
        ReflectionTestUtils.setField(service, "mapper", Mappers.getMapper(PaymentMapper.class));
        ReflectionTestUtils.setField(service, "publisher", publisher);
        ReflectionTestUtils.setField(service, "repository", repository);

    }

    @Test
    void findById() {

        Payment payment = PaymentDataFake.paymentFake(1);
        when(repository.findById(1L)).thenReturn(Optional.of(payment));

        PaymentResponse response = service.findById(payment.getId());

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(BigDecimal.valueOf(200), response.getAmount());
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void updateStatusPayment() {
        Payment payment = PaymentDataFake.paymentFake(1);
        Payment updated = PaymentDataFake.paymentFake(2);
        ReflectionTestUtils.setField(updated, "id", 1L);
        PaymentRequest request = PaymentDataFake.requestFake(payment);
        when(repository.findById(1L)).thenReturn(Optional.of(payment));
        when(repository.save(any())).thenReturn(updated);

        PaymentResponse response = service.updateStatusPayment(request, 1L);

        assertNotEquals(payment.getStatus(), response.getStatus());
        assertEquals(request.status(), response.getStatus());

        verify(repository, times(1)).findById(anyLong());
        verify(repository, times(1)).save(any());

    }

    @Test
    void findAll() {
        List<Payment> list = PaymentDataFake.listPayment(3);
        when(repository.findAll()).thenReturn(list);

        List<PaymentResponse> responses = service.findAll();
        PaymentResponse response = responses.getFirst();
        assertEquals(0L, response.getId());
        verify(repository, times(1)).findAll();
    }
}