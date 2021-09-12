package com.adams.customer_management_system.service.Impl;

import com.adams.customer_management_system.module.Customer;
import com.adams.customer_management_system.module.MoreUsers;
import com.adams.customer_management_system.repository.CustomerRepository;
import com.adams.customer_management_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public MoreUsers find(String id) {
        Optional<Customer> user = customerRepository.findById(id);
        return new MoreUsers(user.get().getEmail(), user.get().getPassword());
    }

    @Override
    public List<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Optional<Customer> u = customerRepository.findById(email);
        return new Customer(u.get().getEmail(), u.get().getF_name(), u.get().getL_name(), u.get().getPassword(), u.get().getAddress(), u.get().getStatus(), u.get().getP_number());
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List getFirstName(String f_name) {
        return customerRepository.firstName(f_name);
    }

    @Override
    public List getLastName(String L_name) {
        return customerRepository.lastName(L_name);
    }

    @Override
    public void DeleteCustomer(String email) {
        customerRepository.deleteById(email);
    }

    @Override
    public Customer updateCustomer(String email, Customer customer) {
        Optional<Customer> cu = customerRepository.findById(email);
        cu.get().setEmail(customer.getEmail());
        cu.get().setF_name(customer.getF_name());
        cu.get().setL_name(customer.getL_name());
        cu.get().setPassword(customer.getPassword());
        cu.get().setAddress(customer.getAddress());
        cu.get().setP_number(customer.getP_number());
        return customerRepository.save(cu.get());
    }

    @Override
    public List FindNonApprovedCustomer(String status) {
        return customerRepository.getNonApproved(status);
    }

    @Override
    public Customer UpdateNonApprovedCustomer(String id, Customer customer) {
        Optional<Customer> p = customerRepository.findById(id);
        p.get().setStatus(customer.getStatus());
        return customerRepository.save(p.get());
    }

    @Override
    public void demo() {
        System.out.println("start");
        customerRepository.save(new Customer("a", "b", "c", "d", "f", "s", 1));
        System.out.println("end");
    }

}
