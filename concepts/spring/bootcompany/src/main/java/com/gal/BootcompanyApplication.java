package com.gal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.gal.api.DepartmentController;
import com.gal.dao.DepartmentDao;
import com.gal.model.Department;
import com.gal.service.DepartmentService;
import com.gal.service.DepartmentServiceImpl2;

@SpringBootApplication
public class BootcompanyApplication {
	private final DepartmentServiceImpl2 deptServiceImpl;
	
	public BootcompanyApplication(DepartmentServiceImpl2 service) {
		this.deptServiceImpl = service;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BootcompanyApplication.class, args);
		DepartmentService service = context.getBean(DepartmentService.class);
		
		Department dept = new Department();
		dept.setDepartmentId(60);
		dept.setDepartmentName("DevOps");
		dept.setManagerId(124);
		
		service.addDepartment(dept);
		
		System.out.println("After adding new department");
		service.findAll().forEach(System.out::println);
		
		Department dept60 = service.find(60);
		dept60.setDepartmentName("UI/UX");

		service.updateDepartment(dept60);
		
		System.out.println("After updating newly added department");
		service.findAll().forEach(System.out::println);
		
		System.out.println("After deleting newly added department");
		service.deleteDepartment(60);
	}

}
