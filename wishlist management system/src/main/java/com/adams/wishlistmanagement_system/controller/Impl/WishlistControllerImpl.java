package com.adams.wishlistmanagement_system.controller.Impl;

import com.adams.wishlistmanagement_system.controller.WishlistController;
import com.adams.wishlistmanagement_system.module.Wishlist;
import com.adams.wishlistmanagement_system.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@CrossOrigin()
@RestController
@RequestMapping("/adams/api/v1")
public class WishlistControllerImpl implements WishlistController {

    @Autowired
    private final WishlistService wishlistService;

    public WishlistControllerImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/find/all")

    @Override
    public ResponseEntity<List<Wishlist>> FindAll() {
        try {
            if (wishlistService.getAllPayment().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wishlistService.getAllPayment(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/id/{id}")
    public ResponseEntity<Object> FindByID(@PathVariable("id") String id) {
        try {
            if (wishlistService.getPaymentByID(id) == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wishlistService.getPaymentByID(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/status/{status}")
    @Override
    public ResponseEntity<List<Wishlist>> FindNonApprovedPayment(@PathVariable("status") String status) {
        try {
            if (wishlistService.getNonApprovedPayment(status).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wishlistService.getNonApprovedPayment(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{sd}/between/{ed}")
    @Override
    public ResponseEntity<List<Wishlist>> FindPaymentHistoryByDate(@PathVariable("sd") Date sd, @PathVariable("ed") Date ed) {
        try {
            if (wishlistService.getPaymentHistoryByDate(sd, ed).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wishlistService.getPaymentHistoryByDate(sd, ed), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/email/{email}")
    @Override
    public ResponseEntity<List<Wishlist>> FindPaymentHistoryByEmail(@PathVariable("email") String email) {
        try {
            if (wishlistService.getPaymentHistoryByEmail(email).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(wishlistService.getPaymentHistoryByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> Creat(@RequestBody Wishlist wishlist){
        if(wishlistService.addNewPayment(wishlist) == true){
            wishlist.setId(UUID.randomUUID().toString());
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            wishlist.setDate(date);
            return new ResponseEntity<>(wishlistService.addNewPayment(wishlist), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> Update(@PathVariable("id") String id, @RequestBody Wishlist wishlist){
        if(wishlistService.updatePayment(id, wishlist) != null) {
            return new ResponseEntity<>(wishlistService.updatePayment(id, wishlist), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Object> Delete(@PathVariable("id") String id){
        try {
            wishlistService.deletePayment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/find/email/{email}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> UpdateStatusOny(@PathVariable String id, @RequestBody Wishlist wishlist) {
        if(wishlistService.updatePayment(id, wishlist) != null) {
            return new ResponseEntity<>(wishlistService.updatePaymentStatus(id, wishlist.getStatus()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/correct")
    public String check(){
        return "wishListService";
    }

}
