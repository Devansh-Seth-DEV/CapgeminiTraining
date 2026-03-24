package com.issuetracking.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.issuetracking.dto.EmployeeIssueDTO;
import com.issuetracking.dto.IssueDTO;
import com.issuetracking.model.Issue;
import com.issuetracking.service.IssueService;

@RestController
@RequestMapping("/issue")
public class IssueController {
	private static final Logger log = LoggerFactory.getLogger(IssueController.class);
	private IssueService issueService;
	
	@Autowired
	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@GetMapping("/unresolved-issue")
	public ResponseEntity getUnresolvedIssueRaisedByEmployee(
		@RequestParam(name = "empid") int empId
	) {
		log.info("Request for fetching unresolved issues of employeeId " + empId);
		List<Issue> unresolvedIssues = issueService.findUnresolvedIssuesOfEmployee(empId);
		List<IssueDTO> unresolvedIssuesDTO = new ArrayList<>();
		
		if (unresolvedIssues == null || unresolvedIssues.isEmpty()) {
			log.info("No content was found for unresolved issues of employeeId " + empId);
			return ResponseEntity.noContent().build();
		}
		
		unresolvedIssues.forEach(issue -> 
			unresolvedIssuesDTO.add(new IssueDTO(issue))
		);
		
		log.info("Request executed successfully, returning the unresolved issues");
		return ResponseEntity.ok().body(unresolvedIssuesDTO);
	}
	
	@GetMapping("/raised-issues-current-month")
	public ResponseEntity getIssuesRaisedThisMonth() {
		log.info("Request for fetching all issues raised this month");
		List<Issue> currentMonthIssues = issueService.findIssuesRaisedThisMonth();
		List<IssueDTO> currentMonthIssuesDTO = new ArrayList<>();
		
		if (currentMonthIssues == null || currentMonthIssues.isEmpty()) {
			log.info("No content was found for issues raised this month");
			return ResponseEntity.noContent().build();
		}
		
		currentMonthIssues.forEach(issue -> 
			currentMonthIssuesDTO.add(new IssueDTO(issue))
		);

		log.info("Request executed successfully, returning all issues that are raised this month");
		return ResponseEntity.ok().body(currentMonthIssuesDTO);
	}

	@GetMapping("/current-month-raisedby")
	public ResponseEntity getEmployeesWhoRaisedIssuesThisMonth() {
		log.info("Request for fetching all employees who raised issues this month");
		List<Issue> currentMonthIssues = issueService.findIssuesRaisedThisMonth();
		List<EmployeeIssueDTO> currentMonthIssuesDTO = new ArrayList<>();
		
		if (currentMonthIssues == null || currentMonthIssues.isEmpty()) {
			log.info("No content was found for issues raised this month");
			return ResponseEntity.noContent().build();
		}

		currentMonthIssues.forEach(issue -> 
			currentMonthIssuesDTO.add(new EmployeeIssueDTO(issue))
		);

		log.info("Request executed successfully, returning all employees who raised issues this month");
		return ResponseEntity.ok().body(currentMonthIssuesDTO);
	}
}
