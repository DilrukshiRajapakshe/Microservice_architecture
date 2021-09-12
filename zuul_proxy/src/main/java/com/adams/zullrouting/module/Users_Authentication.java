package com.adams.zullrouting.module;
import java.io.Serializable;

public class Users_Authentication implements Serializable {

    private final String jwt;

    public Users_Authentication(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
