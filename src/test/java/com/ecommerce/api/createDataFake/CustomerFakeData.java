package com.ecommerce.api.createDataFake;

import com.ecommerce.api.controller.CustomerController;
import com.ecommerce.api.dto.request.AddressRequest;
import com.ecommerce.api.dto.request.CustomerRequest;
import com.ecommerce.api.entity.Customer;
import com.ecommerce.api.entity.Order;
import com.ecommerce.api.valueObject.Address;
import org.aspectj.weaver.ast.Or;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.List;

public class CustomerFakeData {


    public static Customer customerFake(int i){
        Customer customer = new Customer();
        customer.setName("name" + i);
        customer.setId(Long.valueOf(i));
        customer.setPhoneNumber("000000000");
        customer.setEmail("email"+ i + "@gmail.com");
        customer.setId(Long.valueOf(i));
        customer.setAddress(new Address("street"+i, "number"+i, "neighborhood"+i,"city"+i, "state"+i, "zipCode"+i));
        return customer;
    }


    public static CustomerRequest customerRequestFake(Customer customer){
        return new CustomerRequest(customer.getName(), customer.getEmail(), customer.getPhoneNumber(), new AddressRequest("street", "number", "neighborhood","city", "state", "zipCode"));
    }

    public static List<Customer> listCustomersFake(int number){
        List<Customer> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(customerFake(i));
        }
        return list;
    }
}
