package com.gal.algo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class SixDigitOtpGenerator implements OtpGenerator {
	@Override
	public String getOtp() {
		int randValue = new Random().nextInt(1000000);
		return String.format("%06d", randValue);
	}

}
