package com.adams.notification_management_system.service;

import com.adams.notification_management_system.module.Mail;

public interface ConsumerNotificationService {
    void consumeMessageFromQueue(Mail m);
}
