package com.notifysys.service;

import com.notifysys.model.MessageFormatter;

import jakarta.annotation.PostConstruct;

public class OnlineMessageService {
    private MessageFormatter formatter;
    private String serviceName;        // For showcasing constructor injection

    public OnlineMessageService(MessageFormatter formatter) {
        this.formatter = formatter;
        this.serviceName = "Local Notification Service";
    }

    public OnlineMessageService(MessageFormatter formatter, String serviceName) {
        this.formatter = formatter;
        this.serviceName = serviceName;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(serviceName + " initialized successfully!");
    }

    public void send(String message) {
        String formatted = formatter.format(message);
        System.out.println("[" + System.identityHashCode(this) + "] : " + formatted);
    }
}