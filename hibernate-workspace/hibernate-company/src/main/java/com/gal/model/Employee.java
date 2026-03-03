package com.gal.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private int empId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;

	private String email;
	
	@Column(name="phone_number")
	private String phno;
	
	@Column(name="hire_date")
	private Date hireDate;
	
	@Column(name="job_title")
	private String jobTitle;
	
	private Double salary;
	
	@Column(name="manager_id")
	private Integer managerId;
	
//	@Column(name="department_id")
//	private Integer deptId;
	
	private String address;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
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
//		Integer deptId,
		String address
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
//		this.deptId = deptId;
		this.address = address;
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
	
//	public Integer getDeptId() {
//		return deptId;
//	}
//	public void setDeptId(Integer deptId) {
//		this.deptId = deptId;
//	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
//			deptId = (Integer) rs.getObject("department_id");
			address = rs.getString("address");
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
		return "Employee[empId: %s, firstName: '%s', lastName: '%s', email: '%s', phno: '%s'"
				.formatted(
					empId,
					firstName,
					lastName,
					email,
					phno
				) +
				
				"hireDate: '%s', salary: %s, managerId: %s, deptId: %s, address: '%s']"
			   .formatted(
				   phno,
				   hireDate.toString(),
				   salary,
				   managerId,
				   department.getDepartmentId(),
				   address
				);
	}
}
