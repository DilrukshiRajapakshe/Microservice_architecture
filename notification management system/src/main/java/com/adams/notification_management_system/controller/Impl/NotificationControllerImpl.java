package com.adams.notification_management_system.controller.Impl;

import com.adams.notification_management_system.controller.NotificationController;
import com.adams.notification_management_system.module.Mail;
import com.adams.notification_management_system.module.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/adams/api/v1")
@CrossOrigin()
@RestController
public class NotificationControllerImpl implements NotificationController {

    @Autowired
    private RabbitTemplate template;
    @Value( "${adams.payment_management_system.EXCHANGE}" )
    private String EXCHANGE;
    @Value( "${adams.payment_management_system.ROUTING_KEY}" )
    private String ROUTING_KEY;
    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    @PostMapping(value ="/get_notification", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void getNotification(@RequestBody Notification notification){
        String f_name = notification.getF_name();
        String l_name = notification.getL_name();
        String type = notification.getType();
        String email = notification.getEmail();
        switch (type){
            case  "new_customer":
                Mail m_new_customer = new Mail(adminEmail, "New Customer", f_name+ " "+ l_name +
                        " Customer's registration is pending & Customer email address is"+ email+" !!!!.....");
                template.convertAndSend(EXCHANGE, ROUTING_KEY, m_new_customer);
                break;
            case  "Customer_registration_verification":
                Mail m_Customer_registration_verification = new Mail(email, "Registration verification",
                        f_name+ " "+ l_name +" registration approved by Admin !!!!.....");
                template.convertAndSend(EXCHANGE, ROUTING_KEY, m_Customer_registration_verification);
                break;
            case  "new_order":
                Mail m_new_order = new Mail(adminEmail, "New order", f_name+ " "+ l_name +
                        " Customer order is pending & Customer email address is"+ email+" !!!!.....");
                template.convertAndSend(EXCHANGE, ROUTING_KEY, m_new_order);
                break;
            case  "Order_verification":
                Mail m_Order_verification = new Mail(email, "New order", f_name+ " "+ l_name +
                        "Order is verified by Admin !!!!.....");
                template.convertAndSend(EXCHANGE, ROUTING_KEY, m_Order_verification);
                break;
        }
    }

    @GetMapping("/correct")
    public String check(){
        return "notificationService";
    }

}
