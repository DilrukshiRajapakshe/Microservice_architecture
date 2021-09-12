package com.adams.customer_management_system.controller.Impl;

import com.adams.customer_management_system.controller.CustomerController;
import com.adams.customer_management_system.module.Customer;
import com.adams.customer_management_system.module.EmailNotification;
import com.adams.customer_management_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping("/adams/api/v1")
public class CustomerControllerImpl implements CustomerController {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private CustomerService customerService;
    @Value("${routes.service.url}")
    private String serverUrl;
    @Value("${getNotification.url.param}")
    private String getNotification;
    @Value("${notificationService.service.name}")
    private String notificationService;
    @Value("${routes.service.version.url}")
    private String versionUrl;

    @GetMapping("/find/all")
    @Override
    public ResponseEntity<List<Customer>> FindAllCustomer() {
        try {
            if (customerService.getAllUsers().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/find/email/{email}")
    public ResponseEntity<Object> FindOneCustomer(@PathVariable("email") String email) {

        try {
            if (customerService.getCustomerByEmail(email) == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/status/{status}")
    @Override
    public ResponseEntity<List<Customer>> FindNonApprovedCustomer(@PathVariable("status") String status) {
        try {
            if (customerService.FindNonApprovedCustomer(status).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerService.FindNonApprovedCustomer(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseEntity<Object> CreatCustomer(@RequestBody Customer customer) {
        try {
            if (customerService.addNewCustomer(customer) == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            EmailNotification notification = new EmailNotification(customer.getF_name(), customer.getL_name(),
                    customer.getEmail(), "new_customer");
            ResponseEntity<EmailNotification> responseEntityNotification = restTemplate.postForEntity(serverUrl + notificationService
                    + versionUrl + getNotification, notification, EmailNotification.class);
            return new ResponseEntity<>(customerService.addNewCustomer(customer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/all/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> UpdateCustomer(@PathVariable("email") String email, @RequestBody Customer customer) {
        if (customerService.updateCustomer(email, customer) != null) {
            return new ResponseEntity<>(customerService.updateCustomer(email, customer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/status/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Override
    public ResponseEntity<Object> UpdateNonApprovedCustomer(@PathVariable("email") String email, @RequestBody Customer customer) {
        if (customerService.updateCustomer(email, customer) != null) {
            EmailNotification notification = new EmailNotification(customer.getF_name(), customer.getL_name(),
                    customer.getEmail(), "Customer_registration_verification");
            ResponseEntity<EmailNotification> responseEntityNotification = restTemplate.postForEntity(serverUrl +
                    notificationService + versionUrl + getNotification, notification, EmailNotification.class);
            return new ResponseEntity<>(customerService.updateCustomer(email, customer), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public ResponseEntity<Object> DeleteCustomer(@PathVariable("email") String email) {
        try {
            customerService.DeleteCustomer(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/f_name/{f_name}")
    @Override
    public ResponseEntity<List<Customer>> FindCustomerByF_name(@PathVariable("f_name") String f_name) {
        try {
            if (customerService.getFirstName(f_name).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerService.getFirstName(f_name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/l_name/{l_name}")
    @Override
    public ResponseEntity<List<Customer>> FindCustomerByL_name(@PathVariable("l_name") String l_name) {
        try {
            if (customerService.getLastName(l_name).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(customerService.getLastName(l_name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/correct")
    public String check() {
        return "customerService";
    }
}
