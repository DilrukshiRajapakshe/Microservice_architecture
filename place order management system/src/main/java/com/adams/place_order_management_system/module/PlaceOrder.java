package com.adams.place_order_management_system.module;

import java.util.List;

public class PlaceOrder {
    private Wishlist wishlist;
    private List<Order> orders;

    public PlaceOrder() {
    }

    public PlaceOrder(Wishlist wishlist, List<Order> orders) {
        this.wishlist = wishlist;
        this.orders = orders;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "PlaceOrder{" +
                "wishlist=" + wishlist +
                ", orders=" + orders +
                '}';
    }
}
