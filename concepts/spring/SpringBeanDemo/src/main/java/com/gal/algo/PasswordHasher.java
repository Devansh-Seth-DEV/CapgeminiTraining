package com.gal.algo;

import org.springframework.stereotype.Component;

// Asking IoC container (ApplicationContext: we've used AnnotationConfigApplicationContext in main) to
// create an object for this class
@Component
public class PasswordHasher {
	public PasswordHasher() {
		System.out.println("PasswordHasher created.");
	}

	public String hash(String password) {
		return new StringBuilder(password).reverse()
				.toString();
	}
}
