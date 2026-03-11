package com.refapi.secret;

public class SecretMessage {
	private String content = "The eagle lands at midnight.";

    private void printSecret() {
        System.out.println("Secret Method says: " + content);
    }
    
    private void printSecret(String recuiterName) {
    	System.out.println("Hello " + recuiterName + "! The secret is: " + content);
    }
}
