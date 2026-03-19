package com.gal.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gal.model.CabAssignment;

public interface CabAssignmentRepository
	extends JpaRepository<CabAssignment, Integer>
{
	List<CabAssignment> findByCab_CabIdAndAssignedDate(int cabId, LocalDate assignedDate);
	
	Optional<CabAssignment> findByEmployee_EmployeeIdAndAssignedDate(int empId, LocalDate assignedDate);
	
    List<CabAssignment> findByCab_CabId(int cabId);
}
