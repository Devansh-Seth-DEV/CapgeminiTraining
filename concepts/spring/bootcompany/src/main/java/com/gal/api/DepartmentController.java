package com.gal.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gal.model.Department;
import com.gal.service.DepartmentService;

import java.util.List;
import java.util.ArrayList;

@Controller
public class DepartmentController {
	private static final String List = null;
	@Autowired
	DepartmentService deptService;
	
	@RequestMapping("/department")
	@ResponseBody // returned string is not a view template (not html or jsp)
	// it is the actual response body
	public String getDepartment() {
		Department department = deptService.find(10);
		if (department == null) return null;
		return department.toString();
	}

	@RequestMapping("/all-departments")
	@ResponseBody // returned string is not a view template (not html or jsp)
	// it is the actual response body
	public String getAllDepartments() {
		List<Department> depts = deptService.findAll();

		String formatted = "Departments: [<br>";
		for(int i=0; i<depts.size()-1; i++) {
			formatted += "&emsp;" + depts.get(i) + ",<br>";
		}
		formatted += "&emsp;" + depts.get(depts.size()-1) + "<br>]<br>";

		return formatted;
	}
}
