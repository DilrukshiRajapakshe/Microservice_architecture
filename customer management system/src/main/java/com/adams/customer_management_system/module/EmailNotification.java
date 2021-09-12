package com.adams.customer_management_system.module;

public class EmailNotification {
    private String f_name;
    private String l_name;
    private String email;
    private String type;

    public EmailNotification() {
    }

    public EmailNotification(String f_name, String l_name, String email, String type) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.email = email;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
