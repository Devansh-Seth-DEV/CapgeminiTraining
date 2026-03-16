package com.training.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "training")
public class Training {
	@Id
	@SequenceGenerator(name = "train_seq",
					   sequenceName = "training_seq",
					   allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "train_seq")
	private int trainingid;
	
	String topic;

	LocalDate startDate;

	LocalDate endDate;

	String trainingname;

	String venue;
	
	public Training() {
		super();
	}

	public Training(
		String topic,
		LocalDate startDate,
		LocalDate endDate,
		String trainingname,
		String venue
	) {
		this.topic = topic;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainingname = trainingname;
		this.venue = venue;
	}

	public int getTrainingid() {
		return trainingid;
	}

	public void setTrainingid(int trainingid) {
		this.trainingid = trainingid;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getTrainingname() {
		return trainingname;
	}

	public void setTrainingname(String trainingname) {
		this.trainingname = trainingname;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	@Override
	public String toString() {
		return String.format("Training [trainingid=%s, topic=%s, startDate=%s, endDate=%s, trainingname=%s, venue=%s]",
				trainingid, topic, startDate, endDate, trainingname, venue);
	}
}