package com.capg.training.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.capg.training.model.Employee;

class EmployeeDaoTest {
	@Test
	@Disabled
	void testGetEmployeeById() {
		Employee expected = new Employee(
			102,
			"Lex",
			"De Haan",
			"LDEHAAN",
			"515.123.4569",
			Date.valueOf("2003-06-13"),
			"Vice President",
			Double.valueOf(1700),
			(Integer) 100,
			(Integer) 10
		);
		
		try {
			Employee actual = EmployeeDao.getEmployeeById();
			assertNotNull(actual);
			assertTrue(actual.equals(expected));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddEmployee() {
		EmployeeDao dao = new EmployeeDao();
		
		try {
			Employee emp = new Employee(
				130,
				"Vedansh",
				"Seth",
				"VSETH",
				"124.356.789",
				Date.valueOf("2026-02-21"),
				"CTO",
				Double.valueOf(300000000),
				Integer.valueOf(100),
				Integer.valueOf(10)
			);
			
			System.out.println(emp);
			Employee actual = dao.addEmployee(emp);
			
			assertNotNull(actual);
			assertTrue(actual.equals(emp));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
