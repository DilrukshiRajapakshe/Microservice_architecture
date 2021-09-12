package com.adams.order_list_management_system.service;

import com.adams.order_list_management_system.module.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrdersByID(String id);

    Order addNewOrders(Order order);

    void deleteOrders(String id);

    Order updateOrders(String id, Order order);

    List<Order> findAllPid(String pid);

    void demo();
}
