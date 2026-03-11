package com.gal.dao;

import java.util.List;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class EmployeeDao {
	private static EntityManager em = null;
	
	static {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	public Employee getEmployeeById(int empId) {
		Employee employee = em.find(Employee.class, empId);
		return employee;
	}
	
	public Employee updateEmployee(Employee emp) {
		em.persist(emp);
		return emp;
	}
	
	public List<Employee> getAllEmployees() {
		String objQuery = """
				SELECT e
				FROM Employee e
				""";
		
		Query query = em.createQuery(objQuery);
		List<Employee> allEmployees = query.getResultList();
		
		return allEmployees;
	}
}
