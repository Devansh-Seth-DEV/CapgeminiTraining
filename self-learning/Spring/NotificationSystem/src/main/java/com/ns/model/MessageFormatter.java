package com.ns.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class MessageFormatter implements Formatter {
	@Value("${notification.prefix:[DEFAULT]}")
	// in case notificatin.prefix is missing from your .properties file,
	// spring will silently use [DEFAULT] instead of crashing
	private String prefix;

//	@Override
//	public String format(String text) {
//		return "[LOG] " + java.time.LocalDate.now() + " : " + text;
//	}
	
	@Override
	public String format(String text) {
		// Now using the prefix from the file!
		return prefix + " " + java.time.LocalDate.now() + " : " + text;
	}
}
