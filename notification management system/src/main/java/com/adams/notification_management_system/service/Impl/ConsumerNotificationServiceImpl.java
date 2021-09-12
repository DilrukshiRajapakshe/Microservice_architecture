package com.adams.notification_management_system.service.Impl;

import com.adams.notification_management_system.module.Mail;
import com.adams.notification_management_system.service.ConsumerNotificationService;
import com.adams.notification_management_system.service.SendMailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerNotificationServiceImpl implements ConsumerNotificationService {

    SendMailService service;

    public ConsumerNotificationServiceImpl(SendMailService service) {
        this.service = service;
    }

    @Override
    @RabbitListener(queues = "${adams.payment_management_system.QUEUE}")
    public void consumeMessageFromQueue(Mail m) {
        service.sendMail(m);
    }

}
