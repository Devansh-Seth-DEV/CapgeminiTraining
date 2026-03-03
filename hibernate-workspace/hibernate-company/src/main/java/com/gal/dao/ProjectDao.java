package com.gal.dao;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Employee;
import com.gal.model.Project;

import jakarta.persistence.EntityManager;

public class ProjectDao {
	private static EntityManager em = null;
	
	static {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	public Project addProject(Project project) {
		em.persist(project);
		return project;
	}
	
	public Employee getProjectManagerOfProjectId(int projectId) {
		Project project = em.find(Project.class, projectId);
		return project.getProjectManager();
	}
}
