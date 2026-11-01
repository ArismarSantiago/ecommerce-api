package com.ecommerce.api.service;

import com.ecommerce.api.createDataFake.*;
import com.ecommerce.api.dto.mapper.CustomerMapper;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.dto.response.CustomerResponse;
import com.ecommerce.api.dto.updateRequest.CustomerUpdateRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.repository.CustomerRepository;
import org.hibernate.boot.model.source.spi.AssociationSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void up(){
        ReflectionTestUtils.setField(service,"mapper", Mappers.getMapper(CustomerMapper.class));
    }

    @Test
    void findById(){
        Customer customer = CustomerFakeData.customerFake(1);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        CustomerResponse response = service.findById(1L);

       assertNotNull(response);
       assertEquals(1L, response.getId());

       verify(repository, times(1)).findById(any());
    }

    @Test
    void findAll(){
        List<Customer> list = CustomerFakeData.listCustomersFake(3);

        when(repository.findAll()).thenReturn(list);

        List<CustomerResponse> responses = service.findAll();

        assertNotNull(responses);
        verify(repository, times(1)).findAll();
    }

    @Test
    void insertCustomer(){
        Customer customer = CustomerFakeData.customerFake(1);
        CustomerRequest request = CustomerFakeData.customerRequestFake(customer);
        when(repository.save(any())).thenReturn(customer);

        CustomerResponse response = service.insert(request);

        assertNotNull(response);
        verify(repository, times(1)).save(any());
    }

    @Test
    void updateCustomer(){
        Customer customer = CustomerFakeData.customerFake(1);
        Customer updated = CustomerFakeData.customerFake(2);
        CustomerUpdateRequest requestUpdate = CustomerFakeData.requestUpdateFake(updated);
        ReflectionTestUtils.setField(updated, "id", 1L);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        when(repository.save(any())).thenReturn(updated);

        CustomerResponse response = service.update(requestUpdate, 1L);

        assertNotNull(response);
        assertNotEquals(customer.getName(), response.getName());
        assertEquals(updated.getName(), response.getName());
        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(anyLong());


    }
}
