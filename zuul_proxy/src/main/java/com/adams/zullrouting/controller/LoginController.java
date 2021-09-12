package com.adams.zullrouting.controller;

import com.adams.zullrouting.module.MoreUsers;
import org.springframework.http.ResponseEntity;

public interface LoginController {
    ResponseEntity<?> Login(MoreUsers authenticationRequest)throws Exception;
}
