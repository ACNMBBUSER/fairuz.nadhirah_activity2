package com.mbbtraining.AccountMs.service.impl;

import com.mbbtraining.AccountMs.service.NotificationService;

public class SomeService {

    private final NotificationService notificationService;

    public SomeService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void doSomethingAndNotify(String recipient){
        notificationService.sendNotification("Some notification message");
    }
}
