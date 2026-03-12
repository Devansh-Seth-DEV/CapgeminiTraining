package com.ns.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UserAlert {
	private String message;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void show() {
		System.out.println("Alert: " + message + " [Object ID: " + System.identityHashCode(this) + "]");
	}
}
