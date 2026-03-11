package com.gal.algo;

import org.springframework.stereotype.Component;

@Component
public class MyCalc implements Calc {
	@Override
	public int add(int a, int b) {
		return a+b;
	}

}
