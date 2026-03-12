package com.ns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ns.model.Formatter;
import com.ns.model.MessageFormatter;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class MessageService {
	
//	@Autowired
//	private MessageFormatter formatter;	// Spring injects this!
//	private final MessageFormatter formatter;
	
//	@Autowired
	// in modern Spring (since v4.3) if a class only has one constructor
	// you don't even need to write the @Autowired tag
	// Spring will assume you want to use it
//	public MessageService(MessageFormatter formatter) {
//		this.formatter = formatter;
//	}
//	
//	@Autowired
//	@Qualifier("fancy")		// We tell Spring exactly which "Nickname" to look for
//	private Formatter formatter;
	
	private final Formatter formatter;
	
	// We ask for "Formatter" (the interface)
//	public MessageService(
//		@Qualifier("fancy") Formatter formatter
//	) {
//		this.formatter = formatter;
//	}
	
	// No @Autowired needed! the XML handles the wiring
	public MessageService(Formatter formatter) {
		this.formatter = formatter;
	}
	
	@PostConstruct
	public void init() {
		System.out.println("Message service is ALIVE!");
		if (formatter != null) {
			System.out.println("Formatter has been successfully injected!");
		} else {
			System.out.println("Warning: Formatter is missing!");
		}
	}
	
	@PreDestroy
	public void cleanup() {
		System.out.println("Message Service is shutting down");
		System.out.println("Cleaning up resources... Goodbye!");
	}
	
	public void send(String message) {
		String formatted = formatter.format(message);
		System.out.println(formatted);
	}
}
