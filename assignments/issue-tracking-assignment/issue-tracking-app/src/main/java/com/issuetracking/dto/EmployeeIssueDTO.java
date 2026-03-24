package com.issuetracking.dto;

import com.issuetracking.model.Employee;
import com.issuetracking.model.Issue;

public class EmployeeIssueDTO {
	private String firstName;
	private String lastName;
	
	public EmployeeIssueDTO() {}
	
	public EmployeeIssueDTO(Issue issue) {
		Employee emp = issue.getEmployee();
		this.firstName = emp.getFirstName();
		this.lastName = emp.getLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
