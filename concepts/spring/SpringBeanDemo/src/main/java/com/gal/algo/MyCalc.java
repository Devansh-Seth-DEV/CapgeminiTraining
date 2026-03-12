package com.gal.algo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;


@Component
public class MyCalc implements Calc {
	@Override
	public int add(int a, int b) {
		return a+b;
	}
	
	@PostConstruct
	public void onInit() {
		System.out.println("Initialization method for DBConnect, WebSockets open ..");
	}
	
	@PreDestroy
	public void onDestroy() {
		System.out.println("Destroy method for closing connection etc...");
	}
}
