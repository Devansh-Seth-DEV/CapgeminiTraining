package com.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Training;

@Repository
public interface TrainingRepository 
	extends JpaRepository<Training, Integer>
{
	
}
