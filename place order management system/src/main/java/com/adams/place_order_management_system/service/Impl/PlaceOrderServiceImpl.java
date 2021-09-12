package com.adams.place_order_management_system.service.Impl;

import com.adams.place_order_management_system.module.*;
import com.adams.place_order_management_system.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    RestTemplate restTemplate;
    @Value("${routes.service.url}")
    private String serverUrl;
    @Value("${routes.service.version.url}")
    private String versionUrl;
    @Value("${customerService.service.name}")
    private String customerService;
    @Value("${findByEmail.url.param}")
    private String findByEmail;
    @Value("${orderListService.service.name}")
    private String orderService;
    @Value("${wishListService.service.name}")
    private String wishListService;
    @Value("${notificationService.service.name}")
    private String notificationService;
    @Value("${findByPid.url.param}")
    private String findByPidParam;
    @Value("${findAll.url.param}")
    private String findAllParam;
    @Value("${findByID.url.param}")
    private String findByID;
    @Value("${findByStatus.url.param}")
    private String findByStatus;
    @Value("${findBetween.url.param}")
    private String findBetween;
    @Value("${create.url.param}")
    private String create;
    @Value("${updateAllFiledByID.url.param}")
    private String updateAllFiledByID;
    @Value("${updateStatusOnlyByID.url.param}")
    private String updateStatusOnlyByID;
    @Value("${deleteByID.url.param}")
    private String deleteByID;
    @Value("${getNotification.url.param}")
    private String getNotification;

    @Override
    public List<PlaceOrder> FindAllPlaceOrder() {
        ResponseEntity<Wishlist[]> responseEntityWishList = restTemplate.getForEntity(serverUrl+wishListService+versionUrl+
                findAllParam, Wishlist[].class);
        return getPlaceOrderList(responseEntityWishList);
    }

    @Override
    public PlaceOrder FindPlaceOrderByID(String id) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        ResponseEntity<Wishlist> responseEntityWishList = restTemplate.getForEntity(serverUrl+wishListService+versionUrl+
                findByID,Wishlist.class, params );
        Wishlist wishlist = responseEntityWishList.getBody();
        return new PlaceOrder(wishlist,getOrderList(params));
    }

    @Override
    public List<PlaceOrder> FindNonApprovedPlaceOrder(String status) {
        ArrayList<PlaceOrder> po = new ArrayList<PlaceOrder>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("status", status);
        ResponseEntity<Wishlist> responseEntityWishList = restTemplate.getForEntity(serverUrl+wishListService+versionUrl+
                findByStatus,Wishlist.class, params );
        Wishlist wishlist = responseEntityWishList.getBody();
        po.add(new PlaceOrder(wishlist,getOrderList(params)));
        return po;
    }

    @Override
    public List<PlaceOrder> FindPlaceOrderHistoryByDate(Date sd, Date ed) {
        Map<String, Date> params_w = new HashMap<String, Date>();
        params_w.put("sd", sd);
        params_w.put("ed", ed);
        ResponseEntity<Wishlist[]> responseEntityWishList = restTemplate.getForEntity(serverUrl+wishListService+versionUrl+
                findBetween, Wishlist[].class, params_w);
        return getPlaceOrderList(responseEntityWishList);
    }

    @Override
    public List<PlaceOrder> FindPlaceOrderHistoryByEmail(String email) {
        ArrayList<PlaceOrder> po = new ArrayList<PlaceOrder>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        ResponseEntity<Wishlist> responseEntityWishList = restTemplate.getForEntity(serverUrl+wishListService+versionUrl+
                findByEmail,Wishlist.class, params );
        Wishlist wishlist = responseEntityWishList.getBody();
        po.add(new PlaceOrder(wishlist,getOrderList(params)));
        return po;
    }

    @Override
    public PlaceOrder CreatPlaceOrder(PlaceOrder placeOrder) {
        ResponseEntity<Wishlist> responseEntityWishList = restTemplate.postForEntity(serverUrl+wishListService+versionUrl+
                create, placeOrder.getWishlist(),Wishlist.class );
        List<Order> orderList = placeOrder.getOrders();
        ArrayList<Order> ordersList = new ArrayList<>();
        for(Order order: orderList) {
            ResponseEntity<Order> responseEntityOrder = restTemplate.postForEntity(serverUrl + orderService +versionUrl+
                    create, order, Order.class);
            ordersList.add(order);
        }
        massage("new_order", placeOrder.getWishlist().getEmail());
        return new PlaceOrder(responseEntityWishList.getBody(),ordersList );
    }

    @Override
    public void UpdatePlaceOrder(String id, PlaceOrder placeOrder) {
        Map<String, String> params_w = new HashMap<String, String>();
        params_w.put("id", id);
        restTemplate.put(serverUrl + wishListService + versionUrl+ updateAllFiledByID, placeOrder.getWishlist() , params_w);
        List<Order> orderList = placeOrder.getOrders();
        for(Order order: orderList) {
            Map<String, String> params_o = new HashMap<String, String>();
            params_o.put("id", order.getId());
            restTemplate.put(serverUrl + orderService + versionUrl+
                    updateAllFiledByID, order, params_o);
        }
    }

    @Override
    public void DeletePlaceOrder(String id) {
        Map<String, String> params_w = new HashMap<String, String>();
        params_w.put("id", id);
        restTemplate.delete(serverUrl + wishListService +versionUrl+ deleteByID, params_w);
    }

    @Override
    public void UpdatePlaceOrderStatusOny(String id, PlaceOrder placeOrder) {
        Map<String, String> params_w = new HashMap<String, String>();
        params_w.put("id", id);
        restTemplate.put(serverUrl + wishListService +versionUrl+ updateStatusOnlyByID, placeOrder.getWishlist() , params_w);
        massage("Order_verification", placeOrder.getWishlist().getEmail());
    }
    @Override
    public List<Order> getOrderList(Map params){
        ArrayList<Order> ol = new ArrayList<Order>();
        ResponseEntity<Order[]> responseEntityOrder = restTemplate.getForEntity(serverUrl+orderService+versionUrl+
                findByPidParam,Order[].class, params);
        Order orderList[] = responseEntityOrder.getBody();
        for(Order o: orderList) ol.add(o);
        return ol;
    }
    @Override
    public ArrayList<PlaceOrder> getPlaceOrderList(ResponseEntity<Wishlist[]> responseEntityWishList){
        Wishlist wishList_find_all[] = responseEntityWishList.getBody();
        ArrayList<PlaceOrder> po = new ArrayList<PlaceOrder>();

        for(Wishlist w: wishList_find_all){
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", w.getId());
            po.add(new PlaceOrder(w,getOrderList(params)));
        }
        return po;
    }
    @Override
    public void massage(String messageService, String email){
        Customer customer = getUserInformation(email);
        Notification notification = new Notification(customer.getF_name(),customer.getL_name(),
                customer.getEmail(), messageService);
        ResponseEntity<Notification> responseEntityNotification = restTemplate.postForEntity(serverUrl+notificationService+versionUrl+
                getNotification, notification,Notification.class );
    }
    @Override
    public Customer getUserInformation(String email){
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        ResponseEntity<Customer> responseEntityCustomer = restTemplate.getForEntity(serverUrl+customerService+versionUrl+
                findByEmail,Customer.class, params );
        Customer customer = responseEntityCustomer.getBody();
        return customer;
    }

}
