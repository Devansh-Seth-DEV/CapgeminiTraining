package com.gal.model;

import java.time.LocalDate;

public class EmployeeTransportDTO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phoneNumber;
	
	private Cab cab;
	private LocalDate cabAssignedDate;
	
	
	public EmployeeTransportDTO(Employee e) {
		this.employeeId = e.getEmployeeId();
		this.firstName = e.getFirstName();
		this.lastName = e.getLastName();
		this.email = e.getEmail();
		this.address = e.getAddress();
		this.phoneNumber = e.getPhoneNumber();
	}
	
	public EmployeeTransportDTO(Employee e, CabAssignment assignment) {
		this.employeeId = e.getEmployeeId();
		this.firstName = e.getFirstName();
		this.lastName = e.getLastName();
		this.email = e.getEmail();
		this.address = e.getAddress();
		this.phoneNumber = e.getPhoneNumber();
		
		this.cab = (assignment != null)
					? assignment.getCab() : null;
		
		this.setCabAssignedDate((assignment != null)
					? assignment.getAssignedDate() : null);
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public LocalDate getCabAssignedDate() {
		return cabAssignedDate;
	}

	public void setCabAssignedDate(LocalDate assignedDate) {
		this.cabAssignedDate = assignedDate;
	}

}
