package com.adams.wishlistmanagement_system.controller;

import com.adams.wishlistmanagement_system.module.Wishlist;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface WishlistController {
    ResponseEntity<List<Wishlist>> FindAll();
    ResponseEntity<Object> FindByID(String id);
    ResponseEntity<List<Wishlist>> FindNonApprovedPayment(String status);
    ResponseEntity<List<Wishlist>> FindPaymentHistoryByDate(Date sd , Date ed);
    ResponseEntity<List<Wishlist>> FindPaymentHistoryByEmail(String email);
    ResponseEntity<Object> Creat(Wishlist wishlist);
    ResponseEntity<Object> Update(String id, Wishlist wishlist);
    ResponseEntity<Object> Delete(String id);
    ResponseEntity<Object> UpdateStatusOny(String id, Wishlist wishlist);

}
