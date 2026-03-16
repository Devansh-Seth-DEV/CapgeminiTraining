package com.gal.service;

import java.util.List;
import java.util.Optional;

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
	public void addDepartment(Department dept) {
		repo.save(dept);
	}

	@Override
	@Transactional
	public void updateDepartment(Department dept) {
		repo.save(dept);
		
	}

	@Override
	@Transactional
	public void deleteDepartment(int deptId) {
		repo.deleteById(deptId);
	}
}
