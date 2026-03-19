package com.gal.service;

import java.util.List;

import com.gal.model.Department;

import jakarta.transaction.Transactional;

public interface DepartmentService {

	Department find(int deptId);

	List<Department> findAll();
	
	Department addDepartment(Department dept);
	
	Department updateDepartment(Department dept);
	
	void deleteDepartment(int deptId);

	List<Department> findByManagerName(String firstName, String lastName);
}
