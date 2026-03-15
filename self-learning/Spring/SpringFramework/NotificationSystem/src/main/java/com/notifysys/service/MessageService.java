package com.notifysys.service;

import com.notifysys.model.MessageFormatter;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private MessageFormatter formatter;

    public MessageService(MessageFormatter formatter) {
        this.formatter = formatter;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("MessageService initialized!");

        if (formatter != null) {
            System.out.println("Formatter has been successfully injected.");
        } else {
            System.out.println("WARNING: Formatter is missing!");
        }
    }

    // This runs right before the Spring container shuts down,
    @PreDestroy
    public void preDestroy() {
        System.out.println("MessageService shutting down...");
    }

    public void send(String message) {
        String formatted = formatter.format(message);
        System.out.println(formatted);
    }
}