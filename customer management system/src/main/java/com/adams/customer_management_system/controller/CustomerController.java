package com.adams.customer_management_system.controller;

import com.adams.customer_management_system.module.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerController {

    ResponseEntity<List<Customer>> FindAllCustomer();

    ResponseEntity<Object> FindOneCustomer(String id);

    ResponseEntity<List<Customer>> FindNonApprovedCustomer(String status);

    ResponseEntity<Object> CreatCustomer(Customer customer);

    ResponseEntity<Object> UpdateCustomer(String id, Customer customer);

    ResponseEntity<Object> UpdateNonApprovedCustomer(String id, Customer customer);

    ResponseEntity<Object> DeleteCustomer(String id);

    ResponseEntity<List<Customer>> FindCustomerByF_name(String f_name);

    ResponseEntity<List<Customer>> FindCustomerByL_name(String l_name);

}
