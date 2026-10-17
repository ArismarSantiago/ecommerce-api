package com.ecommerce.api.repository;

import com.ecommerce.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    List<Customer> findByPhoneNumber(String phoneNumber);

    Customer findByEmail(String email);
    Boolean existsByEmail(String email);
}
