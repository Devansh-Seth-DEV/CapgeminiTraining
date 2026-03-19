package com.gal.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gal.model.Department;
import com.gal.service.DepartmentService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@RestController
@RequestMapping("/department")
public class DepartmentController {
//	private static final Logger log = LoggerFactory.getLogger("com.gal");
	private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentService deptService;
	
	@GetMapping
//	@ResponseBody // returned string is not a view template (not html or jsp)
	// it is the actual response body
	public ResponseEntity getDepartment(@RequestParam(name = "id") int deptid) {
		log.debug("request for deparmentId: 10");
		Department department = deptService.find(deptid);
		if (department == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(department);
	}

	@RequestMapping("/all")
	// it is the actual response body
	public List<Department> getAllDepartments() {
		List<Department> depts = deptService.findAll();
		return depts;
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	Department addDepartment(@RequestBody Department dept) {
		log.debug("Request for adding department " + dept);
		Department saved = deptService.addDepartment(dept);
		return saved;
	}
	
	@PutMapping(consumes = "application/json", produces = "application/json")
	Department updateDepartment(@RequestBody Department dept) {
		log.debug("request for updating department data " + dept);
		return deptService.updateDepartment(dept);
	}
}
