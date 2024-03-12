package com.mbbtraining.AccountMs.service.impl;

import com.mbbtraining.AccountMs.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class MockNotificationService implements NotificationService {

    @Override
    public void sendNotification(String message){
//        System.out.println("Mock notification sent to " + recipient + ": " + message);
    }
}
