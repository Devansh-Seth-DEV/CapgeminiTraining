package com.issuetracking.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee_issues")
public class Issue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private int issueId;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "is_resolved")
	private boolean isResolved; 
	
	@Column(name = "raise_date")
	private LocalDate raiseDate;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "employee_id")
	private Employee employee;
	
	public Issue() {}

	public Issue(int issueId, String category, boolean isResolved, LocalDate raiseDate, Employee employee) {
		this.issueId = issueId;
		this.category = category;
		this.isResolved = isResolved;
		this.raiseDate = raiseDate;
		this.employee = employee;
	}

	public Issue(int issueId, String category, LocalDate raiseDate, Employee employee) {
		this.issueId = issueId;
		this.category = category;
		this.isResolved = false;
		this.raiseDate = raiseDate;
		this.employee = employee;
	}
	
	public Issue(String category, boolean isResolved, LocalDate raiseDate, Employee employee) {
		this(0, category, isResolved, raiseDate, employee);
	}

	public Issue(String category, boolean isResolved, LocalDate raiseDate) {
		this(0, category, isResolved, raiseDate, null);
	}

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
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

	public void isResolved(boolean isResolved) {
		this.isResolved = isResolved;
	}

	public LocalDate getRaiseDate() {
		return raiseDate;
	}

	public void setRaiseDate(LocalDate raiseDate) {
		this.raiseDate = raiseDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return String.format("Issue [issueId=%s, category=%s, isResolved=%s, raiseDate=%s, employeeId=%s]",
				issueId, category, isResolved, raiseDate,
				(employee == null) ? "null" : employee.getEmployeeId());
	}
}
