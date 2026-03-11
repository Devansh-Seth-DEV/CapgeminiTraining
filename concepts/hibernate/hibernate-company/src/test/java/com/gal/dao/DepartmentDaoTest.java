package com.gal.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Department;
import com.gal.model.Employee;

class DepartmentDaoTest {
	private static DepartmentDao dao = new DepartmentDao();
	
	@AfterAll
	public static void closeConnections() {
		EntityManagerFactoryProvider.close();
	}

	@Test
	@Disabled
	void testGetDepartmentById() {
		Department department = dao.getDepartmentById(30);
		
		assertNotNull(department);
		System.out.println("[RECIEVED]: " + department);
		assertEquals("Sales", department.getDepartmentName());
	}
	
	@Test
	void testGetAllEmployeesOfDepartmentId() {
		List<Employee> allEmployees = dao.getAllEmployeesOfDepartmentId(10);
		if (allEmployees != null)
			allEmployees.forEach(System.out::println);
	}
}
