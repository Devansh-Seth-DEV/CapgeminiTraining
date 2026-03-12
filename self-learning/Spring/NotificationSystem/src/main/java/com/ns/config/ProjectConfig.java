package com.ns.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.ns")	// Spring scans this package for @Component
@PropertySource("classpath:application.properties")	// Points to our file
public class ProjectConfig {
	
}
