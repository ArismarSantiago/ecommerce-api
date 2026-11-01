package com.ecommerce.api.service;

import com.ecommerce.api.dto.mapper.CustomerMapper;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.updateRequest.CustomerUpdateRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.exceptions.DuplicateExceptions;
import com.ecommerce.api.exceptions.NotFoundExceptions;
import com.ecommerce.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerMapper mapper;



    public List<CustomerResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse).toList();
    }

    public Customer findByEntityId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundExceptions("Id não localizado", String.valueOf(id), "Customer"));
    }

    public CustomerResponse findById(Long id) {
        Customer entity = findByEntityId(id);
        return mapper.toResponse(entity);
    }

    public List<CustomerResponse> findByName(String name) {
        return repository.findByName(name).stream()
                .map(mapper::toResponse).toList();
    }

    public CustomerResponse findByEmail(String email) {
        Customer entity = repository.findByEmail(email);
        if (entity == null){
            throw new NotFoundExceptions("Email não localizado", email, "Customer");
        }
        return mapper.toResponse(entity);
    }

    public List<CustomerResponse> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber)
                .stream().map(mapper::toResponse).toList();
    }

    public CustomerResponse insert(CustomerRequest request){
       if (repository.existsByEmail(request.email())){
           throw new DuplicateExceptions("Esse email ja se encontra em uso", "email");
       }
        Customer entity = mapper.toEntity(request);
        Customer saveEntity = repository.save(entity);
        return mapper.toResponse(saveEntity);
    }

    public CustomerResponse update(CustomerUpdateRequest request, Long id){
        Customer entity = findByEntityId(id);

        if(repository.existsByEmail(request.email())){
            throw new DuplicateExceptions("Esse email se encontra em uso", "Customer");
        }
        Customer updated = mapper.updateCustomer(request, entity);

        Customer saveUpdated = repository.save(updated);

        return mapper.toResponse(saveUpdated);
    }

}