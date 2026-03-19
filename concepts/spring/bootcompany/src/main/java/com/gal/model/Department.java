package com.gal.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
	@Id
	@Column(name="department_id")
	int departmentId;
	
	@Column(name="department_name")
	String departmentName;
	
	@OneToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@Column(name="established_date")
	LocalDate establishedDate;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public LocalDate getEstablishedDate() {
		return establishedDate;
	}

	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}

	@Override
	public String toString() {
		return String.format("Department [departmentId=%s, departmentName=%s, managerId=%s, establishedDate=%s]", departmentId,
				departmentName,
				manager==null ? "null" : manager.getEmployeeId(),
				establishedDate);
	}
}
