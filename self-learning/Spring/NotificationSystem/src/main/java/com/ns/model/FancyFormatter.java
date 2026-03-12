package com.ns.model;

import org.springframework.stereotype.Component;

//@Component
public class FancyFormatter implements Formatter {
	@Override
	public String format(String text) {
		return "[FANCY] : " + text.toUpperCase();
	}

}
