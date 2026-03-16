package com.training.repository;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.training.model.Training;

import jakarta.annotation.PreDestroy;

@Component
public class DataInitializer
	implements CommandLineRunner
{
	@Autowired
	private TrainingRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		LocalDate today = LocalDate.now();
		
		// 2 Finished
        repository.save(new Training(
        		"Java Basics",
        		today.minusMonths(2), 
        		today.minusMonths(1), 
        		"Core Java", 
        		"Room 101"
        ));
        
        repository.save(new Training(
        		"SQL 101", 
        		today.minusMonths(3), 
        		today.minusMonths(2), 
        		"Database", 
        		"Online"
        ));

        // 2 Ongoing 
        repository.save(new Training(
        		"Spring Framework", 
        		today.minusDays(5), 
        		today.plusDays(5), 
        		"Spring Framework Basics", 
        		"Room 202"
        	));
        
        repository.save(new Training(
        		"Web Dev", 
        		today.minusDays(2), 
        		today.plusDays(10), 
        		"HTML/CSS", 
        		"Online"
        	));

        // 2 Upcoming
        repository.save(new Training(
        		"Microservices", 
        		today.plusDays(10), 
        		today.plusDays(20), 
        		"Spring Cloud", 
        		"Room 303"
        	));
        
        repository.save(new Training(
        		"Spring Framework", 
        		today.plusMonths(1), 
        		today.plusMonths(2), 
        		"Spring Boot", 
        		"Online"
        	));
        
        System.out.println("6 Test Trainings Inserted!");
	}
	
	@PreDestroy
	public void cleanup() {
		repository.deleteAll();
		System.out.println("Database Cleared on Shutdown");
	}
}
