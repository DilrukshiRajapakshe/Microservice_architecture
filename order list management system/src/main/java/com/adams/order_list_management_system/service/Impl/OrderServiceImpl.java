package com.adams.order_list_management_system.service.Impl;

import com.adams.order_list_management_system.module.Order;
import com.adams.order_list_management_system.repository.OrderRepository;
import com.adams.order_list_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrdersByID(String id) {
        Optional<Order> o = orderRepository.findById(id);
        return new Order(o.get().getId(),o.get().getPid(),o.get().getItemCode(),o.get().getOneItemPrice(),o.get().getAmount(), o.get().getTotalPrice());
    }

    @Override
    public Order addNewOrders(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrders(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order updateOrders(String id, Order order)
    {
        Optional<Order> o = orderRepository.findById(id);
        o.get().setId(order.getId());
        o.get().setPid(order.getPid());
        o.get().setItemCode(order.getItemCode());
        o.get().setOneItemPrice(order.getOneItemPrice());
        o.get().setAmount(order.getAmount());
        o.get().setTotalPrice(order.getTotalPrice());
        return orderRepository.save(o.get());
    }

    @Override
    public List<Order> findAllPid(String pid) {
        return orderRepository.findAllPid(pid);
    }

    @Override
    public void demo() {

    }
}

