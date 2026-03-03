package com.gal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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
    
    @Override
    public String toString() {
    	return """
    			department: {
    			  department_id: %s,
    			  department_name: '%s',
    			  manager_id: %s
    			}
    			"""
    			.formatted(
			   departmentId,
			   departmentName,
			   managerId
    		    );
    }
}
