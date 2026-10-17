package com.ecommerce.api.repository;

import com.ecommerce.api.entity.Payment;
import com.ecommerce.api.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentDateBetween(LocalDateTime initialDate, LocalDateTime finalDate);

}
