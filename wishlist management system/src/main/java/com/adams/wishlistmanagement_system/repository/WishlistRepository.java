package com.adams.wishlistmanagement_system.repository;

import com.adams.wishlistmanagement_system.module.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface WishlistRepository extends MongoRepository<Wishlist, String> {
    @Query("{'status' : ?0}")
    public List<Wishlist> getNonApproved(String status);
    @Query("{'email' : ?0}")
    public List<Wishlist> getHistoryByEmail(String email);
    @Query("{'date': {$gte: ?0, $lte:?1 }}")
    public List<Wishlist> getHistoryByDate(Date gte, Date lte);

}
