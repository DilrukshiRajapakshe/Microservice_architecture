package com.adams.order_list_management_system.module;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Order")
public class Order{
    private String id;
    private String pid;
    private String itemCode;
    private double OneItemPrice;
    private int amount;
    private double totalPrice;

    public Order() {
    }

    public Order(String id, String pid, String itemCode, double oneItemPrice, int amount, double totalPrice) {
        this.id = id;
        this.pid = pid;
        this.itemCode = itemCode;
        OneItemPrice = oneItemPrice;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getOneItemPrice() {
        return OneItemPrice;
    }

    public void setOneItemPrice(double oneItemPrice) {
        OneItemPrice = oneItemPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                "pid=" + pid +
                ", itemCode='" + itemCode + '\'' +
                ", OneItemPrice=" + OneItemPrice +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
