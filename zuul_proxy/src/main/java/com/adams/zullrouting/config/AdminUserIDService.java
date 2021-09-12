package com.adams.zullrouting.config;

import com.adams.zullrouting.module.MoreUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class AdminUserIDService implements UserDetailsService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Value( "${adams.payment_management_system.EMAIL}" )
    private String EMAIL;
    @Value( "${adams.payment_management_system.PASSWORD}" )
    private String PASSWORD;
    @Autowired
    RestTemplate restTemplate;
    @Value("${routes.service.url}")
    private String serverUrl;
    @Value("${routes.service.version.url}")
    private String versionUrl;
    @Value("${customerService.service.name}")
    private String customerService;
    @Value("${findByEmail.url.param}")
    private String findByEmail;
    public AdminUserIDService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (EMAIL.equals(userName)){
            // Admin login
            return new User(EMAIL, PASSWORD, new ArrayList<>());
        }
        else{
            // Student login
            try {
                ResponseEntity <MoreUsers> responseEntity = restTemplate.getForEntity(serverUrl+customerService+
                        versionUrl+findByEmail,MoreUsers.class, userName);
                return new User(responseEntity.getBody().getEmail(), responseEntity.getBody().getPassword(), new ArrayList<>());
            } catch (Exception e) {
                e.printStackTrace();
                throw new UsernameNotFoundException("User " + userName + " was not found in the database");
            }
        }
    }


}