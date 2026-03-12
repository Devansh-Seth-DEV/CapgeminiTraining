package com.ns.model;

import org.springframework.stereotype.Component;

//@Component
public class PlainFormatter implements Formatter {
    public String format(String text) {
    	return "[PLAIN] " + text;
    }
}