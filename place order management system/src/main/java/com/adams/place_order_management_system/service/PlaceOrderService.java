package com.adams.place_order_management_system.service;

import com.adams.place_order_management_system.module.Customer;
import com.adams.place_order_management_system.module.Order;
import com.adams.place_order_management_system.module.PlaceOrder;
import com.adams.place_order_management_system.module.Wishlist;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PlaceOrderService {

    List<PlaceOrder> FindAllPlaceOrder();
    PlaceOrder FindPlaceOrderByID(String id);
    List<PlaceOrder> FindNonApprovedPlaceOrder(String status);
    List<PlaceOrder> FindPlaceOrderHistoryByDate(Date sd , Date ed);
    List<PlaceOrder> FindPlaceOrderHistoryByEmail(String email);
    PlaceOrder CreatPlaceOrder(PlaceOrder placeOrder);
    void UpdatePlaceOrder(String id, PlaceOrder placeOrder);
    void DeletePlaceOrder(String id);
    void UpdatePlaceOrderStatusOny(String id, PlaceOrder placeOrder);
    List<Order> getOrderList(Map params);
    ArrayList<PlaceOrder> getPlaceOrderList(ResponseEntity<Wishlist[]> responseEntityWishList);
    void massage(String messageService, String email);
    Customer getUserInformation(String email);

}
