package com.gal.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gal.model.Department;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@Component
public class DepartmentDaoImpl implements DepartmentDao {
	@Autowired
	EntityManagerFactory factory;

	@Override
	public Department find(int deptId) {
		EntityManager manager = factory.createEntityManager();
		Department department = manager.find(Department.class, deptId);
		return department;
	}
}
