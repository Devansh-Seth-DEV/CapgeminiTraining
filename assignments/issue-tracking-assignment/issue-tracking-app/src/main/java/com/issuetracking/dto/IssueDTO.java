package com.issuetracking.dto;

import java.time.LocalDate;

import com.issuetracking.model.Employee;
import com.issuetracking.model.Issue;

public class IssueDTO {
	private String category;
	private boolean isResolved;
	private LocalDate raiseDate;
	private String raisedByEmployee;
	private String employeeEmail;
	
	public IssueDTO() {}
	
	public IssueDTO(Issue issue) {
		this.category = issue.getCategory();
		this.isResolved = issue.isResolved();
		this.raiseDate = issue.getRaiseDate();
		
		Employee emp = issue.getEmployee();
		this.raisedByEmployee = emp.getFirstName() + " " + emp.getLastName();
		this.employeeEmail = emp.getEmail();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isResolved() {
		return isResolved;
	}

	public void setResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public LocalDate getRaiseDate() {
		return raiseDate;
	}

	public void setRaiseDate(LocalDate raiseDate) {
		this.raiseDate = raiseDate;
	}

	public String getRaisedByEmployee() {
		return raisedByEmployee;
	}

	public void setRaisedByEmployee(String raisedByEmployee) {
		this.raisedByEmployee = raisedByEmployee;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
}
