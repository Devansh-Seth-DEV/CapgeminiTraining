package com.gal.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.gal.dao.EmployeeDao;
import com.gal.model.Project;

public class MockProjectDataSeeder {
	private static List<Project> allProjects = new ArrayList<>();
	private static EmployeeDao dao = new EmployeeDao();
	
	static {
		allProjects.add(
			new Project(
				"Project Alpha",
				"Space X",
				Date.valueOf("2026-01-10"),
				Date.valueOf("2026-08-15"),
				750000.0,
				dao.getEmployeeById(103)
			)
		);
		
		allProjects.add(
			new Project(
				"Project Beta",
				"Tesla",
				Date.valueOf("2026-02-15"),
				Date.valueOf("2027-02-15"),
				450000.0,
				dao.getEmployeeById(124)
			)
		);
	
		
		allProjects.add(
			new Project(
				"Project Gamma",
				"Google",
				Date.valueOf("2026-03-01"),
				Date.valueOf("2026-04-15"),
				900000.0,
				dao.getEmployeeById(102)
			)
		);

		
		allProjects.add(
			new Project(
				"Project Delta",
				"Microsoft",
				Date.valueOf("2026-05-20"),
				Date.valueOf("2026-11-20"),
				300000.0,
				dao.getEmployeeById(101)
			)
		);

		
		allProjects.add(
			new Project(
				"Project Epsilon",
				"Apple",
				Date.valueOf("2026-01-01"),
				Date.valueOf("2026-12-31"),
				60000.0,
				dao.getEmployeeById(100)
			)
		);
	}
	
	public static List<Project> getAllProjects() {
		return allProjects;
	}
}
