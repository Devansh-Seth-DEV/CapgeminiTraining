package com.gal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gal.model.Employee;

@Repository
public interface EmployeeRepository
	extends JpaRepository<Employee, Integer>
{
	List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
	
	List<Employee> findByFirstName(String firstName);
}
