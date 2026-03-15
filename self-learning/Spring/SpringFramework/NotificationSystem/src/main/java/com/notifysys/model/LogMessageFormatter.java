package com.notifysys.model;

import org.springframework.stereotype.Component;

@Component
public class LogMessageFormatter
    implements MessageFormatter
{
    @Override
    public String format(String text) {
        return "[LOG] " + java.time.LocalTime.now() + " : " + text;
    }
}