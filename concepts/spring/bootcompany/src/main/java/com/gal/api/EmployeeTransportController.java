package com.gal.api;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gal.model.CabAssignment;
import com.gal.model.EmployeeTransportDTO;
import com.gal.request.CabAssignmentRequest;
import com.gal.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeTransportController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeTransportController.class);

	@Autowired
	EmployeeService service;
	
	@RequestMapping(
		value = "/byName",
		method = RequestMethod.GET,
		produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
	)
	public EmployeeTransportDTO getEmployeeByName(
			@RequestParam(name="firstName") String firstName
	) throws JsonProcessingException 
	
	{
		log.info("Fetching employees by first name: {}", firstName);
//		List<Employee> employees = service.findByName(firstName, lastName);
//		Employee e = new Employee();
//		e.setEmployeeId(1);
//		e.setEmail("example@gmail.com");
		
		return service.findByFirstName(firstName);
		
//		ObjectMapper mapper = new ObjectMapper();
//		String str = mapper.writeValueAsString(e);
//		
//		ResponseEntity.ok().body(str);
//
//		ResponseEntity.status(HttpStatus.CREATED).body(str);
//		ResponseEntity.status(HttpStatus.NO_CONTENT);
//
//		return ResponseEntity.ok()
//			.header("content-type", "application/json")
//			.body(str);
	}
	
	@GetMapping("/all")
	public List<EmployeeTransportDTO> getAllEmployee() {
		return service.findAll()
				.stream()
				.map(e -> new EmployeeTransportDTO(e))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/view-cabs")
	public ResponseEntity<EmployeeTransportDTO> getEmployee(
			@RequestParam(name="id") int empId
	) {
		LocalDate today = LocalDate.now();
		EmployeeTransportDTO empDto = service.getCabAssignment(empId, today);
		
		if (empDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(empDto);
	}
	
	@PostMapping(
		path = "/addcab",
		consumes = "application/json",
		produces = "application/json"
	)
	@ResponseStatus(code = HttpStatus.CREATED)
	public CabAssignment addCab(
		@RequestBody CabAssignmentRequest request
	) {
		return service.addCabAssignment(
					request.getEmpId(),
					request.getCabId(),
					request.getDate()
				);
	}
}
