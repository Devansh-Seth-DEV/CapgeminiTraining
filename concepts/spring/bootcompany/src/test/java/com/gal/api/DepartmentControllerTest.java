package com.gal.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.gal.model.Department;
import com.gal.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private DepartmentService deptService;

	@Test
	void testGetDepartment() throws Exception {
		Department dept = new Department();
		dept.setDepartmentId(1);
		dept.setDepartmentName("HR");
		dept.setEstablishedDate(LocalDate.of(2020, 5, 8));
		
		when(deptService.find(1)).thenReturn(dept);
		
		mockMvc.perform(
			get("/department?id=1")
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.departmentName").value("HR"));
		
		mockMvc.perform(
			get("/department?id=2")
			.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.code").exists());
		
	}

}
