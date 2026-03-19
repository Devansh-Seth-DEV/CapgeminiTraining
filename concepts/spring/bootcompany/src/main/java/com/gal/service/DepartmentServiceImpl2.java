package com.gal.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.gal.dao.DepartmentRepository;
import com.gal.model.Department;

import jakarta.transaction.Transactional;

@Service
@Primary
public class DepartmentServiceImpl2 implements DepartmentService {
	@Autowired
	DepartmentRepository repo;
	
	private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl2.class);

	@Override
	public Department find(int deptId) {
		Optional<Department> optDept = repo.findById(deptId);
		if (!optDept.isPresent()) return null;
		return optDept.get();
	}

	@Override
	public List<Department> findAll() {
		List<Department> depts = repo.findAll();
		return depts;
	}

	@Override
	@Transactional
	public Department addDepartment(Department dept) {
		return repo.save(dept);
	}

	@Override
	public Department updateDepartment(Department dept) {
		Optional<Department> opt = repo.findById(dept.getDepartmentId());
		
		if (opt.isEmpty()) {
			log.error("Trying to update department not existing");
			throw new RuntimeException("Department not exists");
		}
		
		return repo.save(dept);
	}

	@Override
	@Transactional
	public void deleteDepartment(int deptId) {
		repo.deleteById(deptId);
	}
	
	@Override
	public List<Department> findByManagerName(String firstName, String lastName) {
		List<Department> depts = repo.findByManagerName(firstName.trim(), lastName.trim());
		return depts;
	}
}
