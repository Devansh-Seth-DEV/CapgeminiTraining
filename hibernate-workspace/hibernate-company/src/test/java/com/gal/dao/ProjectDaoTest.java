package com.gal.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Employee;
import com.gal.model.Project;
import com.gal.repository.MockProjectDataSeeder;

import jakarta.persistence.EntityManager;

class ProjectDaoTest {
	private static ProjectDao dao = new ProjectDao();
	
	@BeforeAll
	public static void openConnections() {
		EntityManagerFactoryProvider.getEntityManager();
	}
	
	@AfterAll
	public static void closeConnections() {
		EntityManagerFactoryProvider.close();
	}

	@Test
	@Disabled
	void testAddProject() {
		EntityManager em = EntityManagerFactoryProvider.getEntityManager();
		
		List<Project> allProjects = MockProjectDataSeeder.getAllProjects();
		List<Project> allActualProjects = new ArrayList<>();
		
		em.getTransaction().begin();
		
		allProjects.forEach(project -> {
			Project actual = dao.addProject(project);
			allActualProjects.add(actual);
		});

		allActualProjects.forEach(project -> assertNotNull(project));
		
		allActualProjects.forEach(System.out::println);
		
		em.getTransaction().commit();
	}

	@Test
	void testGetProjectManagerOfProjectId() {
		Employee employee = dao.getProjectManagerOfProjectId(53);
		
		assertNotNull(employee);
		assertEquals(124, employee.getEmpId());
		
		System.out.println(employee);
	}
}
