package com.gal.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserVerifier {
//    @Autowired
    //OtpGenerator otpGenerator;
    
    @Autowired
    OtpGenerator fourDigitOtpGenerator;

    @Autowired
    OtpGenerator sixDigitOtpGenerator;
    
    // @Autowired can also be done on constructor so first
    // we'll be commenting @Autowire on field otpGenerator
//    @Autowired
//    public UserVerifier(OtpGenerator otpGenerator) {
//    	super();
//    	System.out.println("Constructor settting the otpGenerator " + otpGenerator);
//    	this.fourDigitOtpGenerator = otpGenerator;
//    }

    @Autowired
    public UserVerifier(FourDigitOtpGenerator otpGenerator) {
    	super();
    	System.out.println("Constructor settting the otpGenerator " + otpGenerator);
    	this.fourDigitOtpGenerator = otpGenerator;
    }

    public void verfiyUser(){
//    	System.out.println(otpGenerator);
    	System.out.println(fourDigitOtpGenerator);
    	System.out.println(sixDigitOtpGenerator);

        String otp = fourDigitOtpGenerator.getOtp();

        if(otp!=null){
            System.out.println("user verified: OTP(" + otp + ")");
        }else{
            System.out.println("user verification fails");
        }
    }
}