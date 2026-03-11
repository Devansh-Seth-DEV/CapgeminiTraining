package com.gal.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
	@Column(name="department_id")
    private int departmentId;
	
	@Column(name="department_name",
			nullable=false)
    private String departmentName;
	
	@Column(name="manager_id")
	// Can be null hence `Integer` is used
    private Integer managerId;
	
	@OneToMany(mappedBy="department")
	private List<Employee> allEmployees = new ArrayList<>();
	
	public Department() {
		super();
	}
	
    public Department(
    		int departmentId,
    		String departmentName,
        Integer managerId
    ) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.managerId = managerId;
    }

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

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    
    public List<Employee> getAllEmployees() {
    		return allEmployees;
    }
    
    @Override
    public String toString() {
    	return "Department[departmentId: %s, departmentName: '%s', managerId: %s]"
    			.formatted(
			   departmentId,
			   departmentName,
			   managerId
    		    );
    }
}
