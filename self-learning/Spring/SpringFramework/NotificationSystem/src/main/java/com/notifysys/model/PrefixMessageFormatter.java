package com.notifysys.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PrefixMessageFormatter
    implements MessageFormatter
{
    @Value("${notification.prefix:[DEFAULT]}")
    private String prefix;

    @Override
    public String format(String text) {
        return prefix + " " + java.time.LocalTime.now() + " : " + text;
    }
}