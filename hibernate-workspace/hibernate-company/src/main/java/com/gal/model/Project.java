package com.gal.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="project_id")
	private int projectId;
	
	private String title;
	private String client;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(nullable=false)
	private double budget;
	
	@OneToOne
	@JoinColumn(name="project_manager_id")
	private Employee projectManager;
	
	public Project() {
		super();
	}

	public Project(
		int projectId,
		String title,
		String client,
		Date startDate,
		Date endDate,
		double budget,
		Employee projectManager
	) {
		this.projectId = projectId;
		this.title = title;
		this.client = client;
		this.startDate = startDate;
		this.endDate = endDate;
		this.budget = budget;
		this.projectManager = projectManager;
	}
	
	public Project(
		String title,
		String client,
		Date startDate,
		Date endDate,
		double budget,
		Employee projectManager
	) {
		this(0,
			 title,
			 client,
			 startDate,
			 endDate,
			 budget,
			 projectManager);
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}

	@Override
	public String toString() {
		return "Project[projectId: %s, title: '%s', client: '%s', startDate: '%s', endDate: '%s', budget: %s, projectManager: %s"
				.formatted(
					projectId,
					title,
					client,
					startDate,
					endDate,
					budget,
					projectManager
				);
	}
}
