package com.gal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gal.dao.CabAssignmentRepository;
import com.gal.dao.CabRepository;
import com.gal.dao.EmployeeRepository;
import com.gal.model.Cab;
import com.gal.model.CabAssignment;
import com.gal.model.Employee;
import com.gal.model.EmployeeTransportDTO;

@Service
public class EmployeeServiceImpl
	implements EmployeeService
{
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	CabAssignmentRepository assignmentRepo;
	
	@Autowired
	CabRepository cabRepo;

	@Override
	public List<Employee> findByName(String firstName, String lastName) {
		return employeeRepo.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepo.findAll();
	}

	@Override
	public EmployeeTransportDTO getCabAssignment(int empId, LocalDate date) {
		Optional<Employee> optEmp = employeeRepo.findById(empId);
		if (optEmp.isEmpty()) {
			throw new RuntimeException("Employee Not Found");
		}
		
		CabAssignment assigned = assignmentRepo
				.findByEmployee_EmployeeIdAndAssignedDate(empId, date)
				.orElse(null);
		
		return new EmployeeTransportDTO(optEmp.get(), assigned);
	}

	@Override
	public CabAssignment addCabAssignment(int empId, int cabId, LocalDate date) {
		Employee emp = employeeRepo.findById(empId)
				.orElseThrow(() -> new RuntimeException("Employee not found"));
		
		Cab cab = cabRepo.findById(cabId)
				.orElseThrow(() -> new RuntimeException("Cab not found"));
		
		CabAssignment assignment = new CabAssignment();
		assignment.setCab(cab);
		assignment.setAssignedDate(date);
		assignment.setEmployee(emp);
		
		CabAssignment saved = assignmentRepo.save(assignment);
		
		return saved;
	}
}
