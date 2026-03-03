package com.gal.dao;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Employee;

import jakarta.persistence.EntityManager;

public class EmployeeDao {
	public Employee getEmployeeById(int empId) {
		EntityManager em = EntityManagerFactoryProvider.getEntityManager();
		Employee employee = em.find(Employee.class, empId);
		return employee;
	}
}
