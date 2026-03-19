package com.gal.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cab_assignments")
public class CabAssignment {
	@Id
	@Column(name = "cab_assign_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cabAssignId;
	
	@ManyToOne
	@JoinColumn(name = "cab_id")
	private Cab cab;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	@Column(name = "assigned_date")
	private LocalDate assignedDate;

	public int getCabAssignId() {
		return cabAssignId;
	}

	public void setCabAssignId(int cabAssignId) {
		this.cabAssignId = cabAssignId;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDate getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(LocalDate assignedDate) {
		this.assignedDate = assignedDate;
	}
}
