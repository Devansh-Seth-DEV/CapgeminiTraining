package com.gal.service;

import java.time.LocalDate;
import java.util.List;

import com.gal.model.CabAssignment;
import com.gal.model.Employee;
import com.gal.model.EmployeeTransportDTO;

public interface EmployeeService {
	List<Employee> findByName(String firstName, String lastName);
	
	List<Employee> findAll();
	
	EmployeeTransportDTO getCabAssignment(int empId, LocalDate date);
	
	CabAssignment addCabAssignment(int empId, int cabId, LocalDate date);
	
	EmployeeTransportDTO findByFirstName(String firstName);
}
