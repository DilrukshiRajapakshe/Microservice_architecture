package com.adams.order_list_management_system.controller;

import com.adams.order_list_management_system.module.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderController {
    ResponseEntity<List<Order>> FindAll();
    ResponseEntity<Object> FindByID(String id);
    ResponseEntity<Object> Creat(Order order);
    ResponseEntity<Object> Update(String id, Order order);
    ResponseEntity<Object> Delete(String id);
    ResponseEntity<List<Order>> FindByWishlistID(String pid);

}
