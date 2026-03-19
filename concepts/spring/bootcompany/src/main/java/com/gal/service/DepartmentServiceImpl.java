package com.gal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gal.dao.DepartmentDao;
import com.gal.model.Department;

//@Service
//public class DepartmentServiceImpl implements DepartmentService {
//	DepartmentDao departmentDao;
//	
//	public DepartmentServiceImpl(DepartmentDao dao) {
//		departmentDao = dao;
//	}
//
//	@Override
//	public Department find(int deptId) {
//		return departmentDao.find(deptId);
//	}
//
//	@Override
//	public List<Department> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void addDepartment(Department dept) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateDepartment(Department dept) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteDepartment(int deptId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Department> findByManagerName(String firstName, String lastName) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
