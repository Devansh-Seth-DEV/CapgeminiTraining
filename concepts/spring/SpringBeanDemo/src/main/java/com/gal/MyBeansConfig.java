package com.gal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.gal.algo.Calc;
import com.gal.algo.MyCalc;

@Configuration
public class MyBeansConfig {
	// Will create beans and provide to spring context, then onwards 
	// context will manage the
	
	@Bean
	@Scope("prototype") // means every time create a new object
	/*
	 * OTHER SCOPES:
	 * 1. request: every time a request comes create a new object
	 * 2. session: every time a session created create a new object
	 * 3. application: every time new application create a new object
	 * 4. singleton(default): create a single instance only
	 */
	StringBuilder getStringBuilder() {
		System.out.println("returning new getStringBuilder");
		return new StringBuilder("bean from configuration");
	}
	
	@Bean
	@Scope("singleton") // by default its singleton
	@Primary
	Calc getCalc() {
		return new MyCalc();
	}

	@Bean (name = "calcNamedBean")
	Calc getCalc2() {
		return new MyCalc();
	}
}
