package com.adams.customer_management_system.module;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Customer")
public class Customer {
    @Id
    private String email;
    private String f_name;
    private String l_name;
    private String password;
    private String address;
    private String status;
    private int p_number;

    public Customer() {
    }

    public Customer(String email, String f_name, String l_name, String password, String address, String status, int p_number) {
        this.email = email;
        this.f_name = f_name;
        this.l_name = l_name;
        this.password = password;
        this.address = address;
        this.p_number = p_number;
        this.status = status;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getP_number() {
        return p_number;
    }

    public void setP_number(int p_number) {
        this.p_number = p_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", email='" + email + '\'' +
                "f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", p_number=" + p_number +
                '}';
    }
}
