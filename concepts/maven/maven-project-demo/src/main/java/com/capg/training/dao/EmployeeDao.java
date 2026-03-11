package com.capg.training.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.capg.training.model.Employee;

public class EmployeeDao {
	public static Employee getEmployeeById() throws SQLException {
		Employee emp = new Employee();
		
		try (
				Scanner sc = new Scanner(System.in);
				Connection connection = DBConnection.getConnection()
			)
		{
			System.out.print("Enter your Employee Id: ");
			int empId = sc.nextInt();
			
			String query = """
				SELECT *	
				FROM Employee
				WHERE employee_id = ?
			""";
			PreparedStatement pst = connection.prepareStatement(query);

			pst.setInt(1, empId);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next() == false) {
				System.out.println("Employee not exists");
				return null;
			}
			
			emp.setFromResultSet(rs);
			System.out.println(emp);
		}
		
		return emp;
	}
	
	public Employee addEmployee(Employee emp) throws SQLException {
		try (
				Connection connection = DBConnection.getConnection()
			)
		{
			String query = """
				INSERT INTO EMPLOYEE
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";
			PreparedStatement pst = connection.prepareStatement(query);
			
			pst.setInt(1, emp.getEmpId());
			pst.setString(2, emp.getFirstName());
			pst.setString(3, emp.getLastName());
			pst.setString(4, emp.getEmail());
			pst.setString(5, emp.getPhno());
			pst.setDate(6, emp.getHireDate());
			pst.setString(7, emp.getJobTitle());
			pst.setObject(8, emp.getSalary());
			pst.setObject(9, emp.getManagerId());
			pst.setObject(10, emp.getDeptId());
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated != 1) {
				throw new RuntimeException("some sql error occured");
			} else { return emp; }
		}
	}
}
