package com.ecommerce.api.repository;

import com.ecommerce.api.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    Shipping findByTrackingCode(String trackingCode);
   List<Shipping> findByShippedAtBetween(LocalDateTime initialDate, LocalDateTime finaleDate);
}
