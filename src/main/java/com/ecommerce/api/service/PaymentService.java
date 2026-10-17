package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.PaymentMapper;
import com.ecommerce.api.dto.request.PaymentRequest;
import com.ecommerce.api.dto.response.PaymentResponse;
import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.events.OnPaymentEvent;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private PaymentMapper mapper;

    @Autowired
    private ApplicationEventPublisher publisher;




    public Payment findByEntityId(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new NotFoundExceptions("Payment não localizado", String.valueOf(id), "Payment"));
    }

    public PaymentResponse findById(Long id) {
        Payment entity = findByEntityId(id);
        return mapper.toResponse(entity);
    }

    public List<PaymentResponse> findByCreatedAtBetween(LocalDate date) {
        LocalDateTime initialDate = date.atStartOfDay();
        LocalDateTime finalDate = date.atTime(LocalTime.MAX);
        return repository.findByPaymentDateBetween(initialDate, finalDate)
                .stream().map(mapper::toResponse).toList();
    }

    public PaymentResponse updateStatusPayment(PaymentRequest request, Long id) {
        Payment entity = findByEntityId(id);

        if (entity.getStatus().equals(request.status())) {
            throw new DuplicateExceptions("O pagameno já se encontra nesse status", String.valueOf(id));
        }
        Payment updated = mapper.updatePaymentStatus(request);
        repository.save(updated);
        OnPaymentEvent event = new OnPaymentEvent(entity);
        publisher.publishEvent(event);
        return mapper.toResponse(updated);
    }

    public List<PaymentResponse> findAll() {
        return  repository.findAll().stream()
                .map(mapper::toResponse).toList();
    }
}
