package com.capg.training.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	private int empId;
	private String firstName;
	private String lastName;
	private String email;
	private String phno;
	private Date hireDate;
	private String jobTitle;
	private Double salary;
	private Integer managerId;
	private Integer deptId;
	
	public Employee() {
		super();
	}
	
	public Employee(
		int empId,
		String firstName,
		String lastName,
		String email,
		String phno,
		Date hireDate,
		String jobTitle,
		Double salary,
		Integer managerId,
		Integer deptId
	) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phno = phno;
		this.hireDate = hireDate;
		this.jobTitle = jobTitle;
		this.salary = salary;
		this.managerId = managerId;
		this.deptId = deptId;
	}

	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
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
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	public void setFromResultSet(ResultSet rs) {
		try {
			empId = rs.getInt("employee_id");
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			email = rs.getString("email");
			phno = rs.getString("phone_number");
			hireDate = rs.getDate("hire_date");
			jobTitle = rs.getString("job_title");
			salary = (Double) rs.getObject("salary");
			managerId = (Integer) rs.getObject("manager_id");
			deptId = (Integer) rs.getObject("department_id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean equals(Employee emp) {
		return emp != null &&
			   empId == emp.getEmpId();
	}
	
	@Override
	public String toString() {
		return """
				employee: {
				  employee_id: %s,
				  first_name: '%s',
				  last_name: '%s',
				  email: '%s',
				  phone_number: '%s',
				  hire_date: '%s',
				  salary: %s,
				  manager_id: %s,
				  department_id: %s
				}
			   """.formatted(
				   empId,
				   firstName,
				   lastName,
				   email,
				   phno,
				   hireDate.toString(),
				   salary,
				   managerId,
				   deptId
				);
	}
}
