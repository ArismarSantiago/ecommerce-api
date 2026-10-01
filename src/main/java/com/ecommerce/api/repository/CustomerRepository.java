package com.ecommerce.api.repository;

import com.ecommerce.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    List<Customer> findByPhoneNumber(String phoneNumber);

    Customer findByEmail(String email);
    Boolean existsByEmail(String email);
}
