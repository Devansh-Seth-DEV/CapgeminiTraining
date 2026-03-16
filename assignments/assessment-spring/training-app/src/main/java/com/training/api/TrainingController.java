package com.training.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.service.TrainingService;

import ch.qos.logback.core.joran.spi.HttpUtil.RequestMethod;

@Controller
public class TrainingController {
	@Autowired
	private TrainingService service;
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	// Requirement: URL:"/trainings"
	@RequestMapping(value = "/trainings")
	@ResponseBody
	public String getTActiveTrainings(
		@RequestParam(required = false) String name
	) {
		return service.getActiveTrainings(name);
	}
	
	// Requirement: URL: "/trainings/upcoming?name=spring"
	@RequestMapping(value = "/trainings/upcoming")
	@ResponseBody
	public String getUpcomingTrainings(@RequestParam String name) {
		return service.getUpcomingTrainings(name);
	}
	
	@RequestMapping("/exit")
	@ResponseBody
	public String shutdownApp() {
		context.close();
		return "Application is shutting down...";
	}
}
