package com.adams.customer_management_system.repository;

import com.adams.customer_management_system.module.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query(value = "{'email' : $0}", delete = true)
    void deleteById(String id);

    @Query("{'f_name' : ?0}")
    List<Customer> firstName(String f_name);

    @Query("{ 'l_name': ?0 })")
    List<Customer> lastName(String l_name);

    @Query("{'status' : ?0}")
    List<Customer> getNonApproved(String status);

}
