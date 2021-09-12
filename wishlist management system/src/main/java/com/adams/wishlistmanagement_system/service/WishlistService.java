package com.adams.wishlistmanagement_system.service;

import com.adams.wishlistmanagement_system.module.Wishlist;

import java.util.Date;
import java.util.List;

public interface WishlistService {
    Wishlist find(String email);

    List<Wishlist> getAllPayment();

    Wishlist getPaymentByID(String id);

    Boolean addNewPayment(Wishlist wishlist);

    List<Wishlist> getNonApprovedPayment(String status);

    List<Wishlist> getPaymentHistoryByEmail(String email);

    List<Wishlist> getPaymentHistoryByDate(Date gte, Date lte);

    void deletePayment(String id);

    Wishlist updatePayment(String id, Wishlist wishlist);

    Wishlist updatePaymentStatus(String id, String status);

    void demo();
}
