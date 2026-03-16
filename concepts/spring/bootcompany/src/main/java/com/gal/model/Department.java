package com.gal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department {
	@Id
	@Column(name="department_id")
	int departmentId;
	
	@Column(name="department_name")
	String departmentName;
	
	@Column(name="managerId")
	Integer managerId;

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

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return String.format("Department [departmentId=%s, departmentName=%s, managerId=%s]", departmentId,
				departmentName, managerId);
	}
}
