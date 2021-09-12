package com.adams.customer_management_system.service;

import com.adams.customer_management_system.module.Customer;

import com.adams.customer_management_system.module.MoreUsers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    MoreUsers find(String email);

    List<Customer> getAllUsers();

    Customer getCustomerByEmail(String email);

    Customer addNewCustomer(Customer customer);

    List getFirstName(String f_name);

    List getLastName(String L_name);

    void DeleteCustomer(String email);

    Customer updateCustomer(String email, Customer customer);

    List FindNonApprovedCustomer(String status);

    Customer UpdateNonApprovedCustomer(String id, Customer customer);

    void demo();
}
