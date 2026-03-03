package com.gal.dao;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Department;

import jakarta.persistence.EntityManager;

public class DepartmentDao {
	public Department getDepartmentById(int deptId) {
		EntityManager em = EntityManagerFactoryProvider.getEntityManager();
		Department department = em.find(Department.class, deptId);
		return department;
	}
}
