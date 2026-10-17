package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.CustomerMapper;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.updateRequest.CustomerUpdateRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper customerMapper;



    public List<CustomerResponse> findAll() {
        return repository.findAll().stream()
                .map(customerMapper::toResponse).toList();
    }

    public Customer findByEntityId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("Id não localizado", String.valueOf(id), "Customer"));
    }

    public CustomerResponse findById(Long id) {
        Customer entity = findByEntityId(id);
        return customerMapper.toResponse(entity);
    }

    public List<CustomerResponse> findByName(String name) {
        return repository.findByName(name).stream()
                .map(customerMapper::toResponse).toList();
    }

    public CustomerResponse findByEmail(String email) {
        Customer entity = repository.findByEmail(email);
        if (entity == null){
            throw new NotFoundExceptions("Email não localizado", email, "Customer");
        }
        return customerMapper.toResponse(entity);
    }

    public List<CustomerResponse> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .stream().map(customerMapper::toResponse).toList();
    }

    public CustomerResponse insert(CustomerRequest request){
       if (repository.existsByEmail(request.email())){
           throw new DuplicateExceptions("Esse email ja se encontra em uso", "email");
       }
        Customer entity = customerMapper.toEntity(request);
        Customer saveEntity = repository.save(entity);
        return customerMapper.toResponse(saveEntity);
    }

    public CustomerResponse update(CustomerUpdateRequest request, Long id){
        Customer entity = findByEntityId(id);

        if(repository.existsByEmail(request.email())){
            throw new DuplicateExceptions("Esse email se encontra em uso", "Customer");
        }
        Customer updated = customerMapper.updateCustomer(request, entity);

        Customer saveUpdated = repository.save(updated);

        return customerMapper.toResponse(saveUpdated);
    }

}