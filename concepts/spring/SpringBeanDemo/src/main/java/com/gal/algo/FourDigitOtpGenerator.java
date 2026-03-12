package com.gal.algo;

import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class FourDigitOtpGenerator implements OtpGenerator {
	@Override
	public String getOtp() {
		int randValue = new Random().nextInt(10000);
		return String.format("%04d", randValue);
	}
}
