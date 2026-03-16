package com.gal.service;

import java.util.List;

import com.gal.model.Department;

import jakarta.transaction.Transactional;

public interface DepartmentService {

	Department find(int deptId);

	List<Department> findAll();
	
	void addDepartment(Department dept);
	
	void updateDepartment(Department dept);
	
	void deleteDepartment(int deptId);
}
