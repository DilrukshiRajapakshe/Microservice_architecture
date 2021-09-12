package com.adams.wishlistmanagement_system.service.Impl;

import com.adams.wishlistmanagement_system.module.Wishlist;
import com.adams.wishlistmanagement_system.repository.WishlistRepository;
import com.adams.wishlistmanagement_system.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    WishlistRepository paymentRepository;
    @Override
    public Wishlist find(String email) {
        Optional<Wishlist> w = paymentRepository.findById(email);
        return new Wishlist(w.get().getId(),w.get().getEmail(),w.get().getDate(),w.get().getTotalPrice(), w.get().getStatus());
    }

    @Override
    public List<Wishlist> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Wishlist getPaymentByID(String id) {
        Optional<Wishlist> w = paymentRepository.findById(id);
        return new Wishlist(w.get().getId(),w.get().getEmail(),w.get().getDate(),w.get().getTotalPrice(), w.get().getStatus());
    }

    @Override
    public Boolean addNewPayment(Wishlist wishlist) {
        ArrayList<Wishlist> l = (ArrayList<Wishlist>) getPaymentHistoryByEmail(wishlist.getEmail());
        List<Wishlist> Non_Approved_list = null;
        l.forEach(w ->{
            if(w.getStatus() == "Non-Approved")
                Non_Approved_list.add(w);
        } );
        if(Non_Approved_list.isEmpty())
            return false;
        paymentRepository.save(wishlist);
        return true;
    }

    @Override
    public List getNonApprovedPayment(String status) {
        return paymentRepository.getNonApproved(status);
    }

    @Override
    public List getPaymentHistoryByEmail(String email) {
        return paymentRepository.getHistoryByEmail(email);
    }

    @Override
    public List getPaymentHistoryByDate(Date gte, Date lte) {
        return paymentRepository.getHistoryByDate(gte, lte);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Wishlist updatePayment(String id, Wishlist wishlist)
    {
        Optional<Wishlist> w = paymentRepository.findById(id);
        w.get().setId(wishlist.getId());
        w.get().setEmail(wishlist.getEmail());
        w.get().setDate(wishlist.getDate());
        w.get().setTotalPrice(wishlist.getTotalPrice());
        w.get().setStatus(wishlist.getStatus());
        return paymentRepository.save(w.get());
    }

    @Override
    public Wishlist updatePaymentStatus(String id, String status) {
        Optional<Wishlist> w = paymentRepository.findById(id);
        w.get().setStatus(status);
        return paymentRepository.save(w.get());
    }

    @Override
    public void demo() {

    }
}
