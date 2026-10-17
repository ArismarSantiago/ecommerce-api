package com.ecommerce.api.repository;

import com.ecommerce.api.entity.Order;
import com.ecommerce.api.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCreatedAtBetween(LocalDateTime initialDate, LocalDateTime finalDate);
}
