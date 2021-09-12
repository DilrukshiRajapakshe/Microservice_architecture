package com.adams.order_list_management_system.controller.Impl;

import com.adams.order_list_management_system.controller.OrderController;
import com.adams.order_list_management_system.module.Order;
import com.adams.order_list_management_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/adams/api/v1")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/find/all")
    @Override
    public ResponseEntity<List<Order>> FindAll() {
        try {
            if (orderService.getAllOrders().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Object> FindByID(@PathVariable("id") String id) {
        try {
            if (orderService.getOrdersByID(id) == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderService.getOrdersByID(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> Creat(@RequestBody Order order){
        try {
            if (orderService.addNewOrders(order)== null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderService.addNewOrders(order), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/all/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> Update(@PathVariable("id") String pid, @RequestBody Order order){
        if(orderService.updateOrders(pid, order) != null) {
            return new ResponseEntity<>(orderService.updateOrders(pid, order), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Object> Delete(@PathVariable("id") String id){
        try {
            orderService.deleteOrders(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/pid/{pid}")
    @Override
    public ResponseEntity<List<Order>> FindByWishlistID(@PathVariable("pid") String pid) {
        try {
            if (orderService.findAllPid(pid).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orderService.findAllPid(pid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/correct")
    public String check(){
        return "orderListService";
    }

}
