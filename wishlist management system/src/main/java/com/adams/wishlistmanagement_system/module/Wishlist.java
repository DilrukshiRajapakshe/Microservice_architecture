package com.adams.wishlistmanagement_system.module;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Payment")
public class Wishlist {
    private String id;
    private String email;
    private Date date;
    private double TotalPrice;
    private String status;

    public Wishlist() {
    }

    public Wishlist(String id, String email, Date date, double totalPrice, String status) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.TotalPrice = totalPrice;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                ", TotalPrice=" + TotalPrice +
                ", status=" + status +
                '}';
    }
}
