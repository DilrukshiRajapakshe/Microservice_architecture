package com.adams.order_list_management_system.repository;

import com.adams.order_list_management_system.module.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{'pid' : ?0}")
    List<Order> findAllPid(String pid);
}
