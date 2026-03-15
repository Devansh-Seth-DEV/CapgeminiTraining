package com.notifysys.model;

import java.time.LocalTime;

public class EmailMessageFormatter
    implements MessageFormatter
{
    private String recipient;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String format(String text) {
        return "[EMAIL] " + LocalTime.now() +
                " (To=" + recipient + ") : " + text;
    }
}