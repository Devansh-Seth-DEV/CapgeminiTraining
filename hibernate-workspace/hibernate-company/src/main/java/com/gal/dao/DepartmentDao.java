package com.gal.dao;

import java.util.List;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Department;
import com.gal.model.Employee;

import jakarta.persistence.EntityManager;

public class DepartmentDao {
	private static EntityManager em = null;
	
	static {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	public Department getDepartmentById(int deptId) {
		Department department = em.find(Department.class, deptId);
		return department;
	}
	
	public List<Employee> getAllEmployeesOfDepartmentId(int deptId) {
		Department department = getDepartmentById(deptId);
		if (department == null) return null;
		
		return department.getAllEmployees();
	}
}
