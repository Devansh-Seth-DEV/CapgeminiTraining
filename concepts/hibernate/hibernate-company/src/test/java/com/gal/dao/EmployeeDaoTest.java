package com.gal.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Employee;

import jakarta.persistence.EntityManager;

class EmployeeDaoTest {
	private static EmployeeDao dao = new EmployeeDao();
	private static EntityManager em = null;
	
	@BeforeAll
	public static void openConnections() {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	@AfterAll
	public static void closeConnections() {
		EntityManagerFactoryProvider.close();
	}

	@Test
	@Disabled
	void testGetEmployeeById() {
		Employee employee = dao.getEmployeeById(100);
		
		System.out.println(employee);
		assertNotNull(employee);
		assertEquals("Steven", employee.getFirstName());
	}
	
	@Test
	@Disabled
	void testUpdateEmployee() {
		Employee psEmp = dao.getEmployeeById(130);
		
		psEmp.setSalary(67000.0);
		psEmp.setAddress("Galgotias University");
		
		em.getTransaction().begin();
		Employee actual = dao.updateEmployee(psEmp);		
		em.getTransaction().commit();
		
		System.out.println(actual);
		assertNotNull(actual);
	}
	
	@Test
	void testGetAllEmployees() {
		List<Employee> allEmployees = dao.getAllEmployees();
		allEmployees.forEach(System.out::println);
		assertNotNull(allEmployees);
	}

}
