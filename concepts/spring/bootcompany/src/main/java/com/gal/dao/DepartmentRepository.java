package com.gal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gal.model.Department;

@Repository
public interface DepartmentRepository
	extends JpaRepository<Department, Integer>
{
	// HQL Query
	@Query("FROM Department d "
		 + "WHERE d.departmentName = :deptName")
	
	// SQL Query
//	@Query(value = "FROM Department d "
//		 + "WHERE d.departmentName = :deptName",
//		nativeQuery = true // means this is a sql query
//	)
	Department findByName(String deptname);
	
	
	// Find the latest department started
	@Query(
		value = "FROM Department d "
				+ "WHERE d.establishedDate = ( "
				+ 	"SELECT MAX(d2.establishedDate) FROM Department d2 "
				+ ")"
	)
	Department findLatestDepartment();
	
	// Find the department having no manager
	@Query(
		value = "FROM Department d "
				+ "WHERE d.manager IS NULL"
	)
	List<Department> findByManagerIsNull(); 
	
	// Find the department(s) whose managerId is given
//	@Query(
//		value = "FROM Department d "
//				+ "WHERE d.manager.employeeId = :managerId"
//	)
//	List<Department> findByManagerId(int managerId);

	// Find the department(s) whose manager name is given
	@Query(
		value = "FROM Department d "
			+ "WHERE d.manager.firstName = :managerFirstName "
			+ " AND d.manager.lastName = :managerLastName"
	)
	List<Department> findByManagerName(String managerFirstName, String managerLastName);
}
