package com.training.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.model.Training;
import com.training.repository.TrainingRepository;

@Service
public class TrainingService {
	@Autowired
	private TrainingRepository repository;
	
	public String getActiveTrainings(String name) {
	    LocalDate today = LocalDate.now();
	    List<Training> allTrainings = repository.findAll();
	    StringBuilder result = new StringBuilder();

	    allTrainings.forEach(t -> {
	        if (!t.getEndDate().isBefore(today)) {
	            if (name == null ||
	            		t.getTopic().toLowerCase()
	            		.contains(name.toLowerCase())) {
	                result.append(t.toString())
	                		.append(" <br>");
	            }
	        }
	    });

	    return result.length() > 0 
	    		   ? result.toString()
	    		   : "No trainings found.";
	}
	
	public String getUpcomingTrainings(String searchName) {
		LocalDate today = LocalDate.now();
		List<Training> allTrainings = repository.findAll();
		
		StringBuilder result = new StringBuilder();
		
		allTrainings.forEach(t -> {
			if (t.getStartDate().isAfter(today) &&
				t.getTopic().toLowerCase()
				.contains(searchName.toLowerCase())) {
				result.append(t.toString())
					.append(" <br>");
			}
		});
		
		return result.length() > 0
				? result.toString()
				: "No trainings found.";
	}
}
