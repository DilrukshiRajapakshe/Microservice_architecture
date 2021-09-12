package com.adams.place_order_management_system.controller;

import com.adams.place_order_management_system.module.PlaceOrder;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface PlaceOrderController {

    ResponseEntity<List<PlaceOrder>> FindAllPlaceOrder();
    ResponseEntity<Object> FindPlaceOrderByID(String id);
    ResponseEntity<List<PlaceOrder>> FindNonApprovedPlaceOrder(String status);
    ResponseEntity<List<PlaceOrder>> FindPlaceOrderHistoryByDate(Date sd , Date ed);
    ResponseEntity<List<PlaceOrder>> FindPlaceOrderHistoryByEmail(String email);
    ResponseEntity<Object> CreatPlaceOrder(PlaceOrder placeOrder);
    ResponseEntity<Object> UpdatePlaceOrder(String id, PlaceOrder placeOrder);
    ResponseEntity<Object> DeletePlaceOrder(String id);
    ResponseEntity<Object> UpdatePlaceOrderStatusOny(String id, PlaceOrder placeOrder);

}
