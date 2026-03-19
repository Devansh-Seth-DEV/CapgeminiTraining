package com.gal.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="hire_date")
	private LocalDate hireDate;
	
	@Column(name="job_title")
	private String jobTitle;
	
	private Double salary;
	
	private String address;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private Employee manager;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	@JsonIgnore
	private Department department;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return String.format(
				"Employee [employeeId=%s, firstName=%s, lastName=%s, email=%s, phoneNumber=%s, hireDate=%s, jobTitle=%s, salary=%s, address=%s, managerId=%s, departmentId=%s]",
				employeeId, firstName, lastName, email, phoneNumber, hireDate, jobTitle, salary, address,
				(manager==null) ? "null" : manager.getEmployeeId(), 
				(department==null) ? "null" : department.getDepartmentId());
	}
}
