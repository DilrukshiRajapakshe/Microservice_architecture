package com.adams.notification_management_system.service.Impl;

import com.adams.notification_management_system.module.Mail;
import com.adams.notification_management_system.service.SendMailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender javaMailSender;

    public SendMailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(Mail mail) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getRecipient(), mail.getRecipient());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());
        javaMailSender.send(msg);
    }


}
