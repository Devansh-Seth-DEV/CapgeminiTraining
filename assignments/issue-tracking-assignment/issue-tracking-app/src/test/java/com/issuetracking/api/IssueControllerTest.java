package com.issuetracking.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.issuetracking.model.Employee;
import com.issuetracking.model.Issue;
import com.issuetracking.service.IssueService;

@WebMvcTest(IssueController.class)
class IssueControllerTest {
	private static final Logger log = LoggerFactory.getLogger(IssueControllerTest.class);

	@Autowired
	private MockMvc mockClient;
	
	@MockitoBean
	private IssueService issueService;

	@Test
	void testGetUnresolvedIssueRaisedByEmployee() throws Exception {
		log.info("Testing `GetUnresolvedIssueRaisedByEmployee` method of `IssueService`");
		
		Employee emp1 = new Employee(1, "Devansh", "Seth", "devansh.seth@email.com");
		Employee emp2 = new Employee(2, "Sachin", "Mishra", "sachin.mishra@email.com");
		Employee emp3 = new Employee(3, "Unnati", "Bharadwaj", "unnati.b@email.com");
		Employee emp4 = new Employee(4, "Rhythm", "Gupta", "rhythm.g@email.com");

		Issue issue1 = new Issue(1, "Cloud Issue", LocalDate.now(), emp1);
		Issue issue2 = new Issue(2, "Access Permission", LocalDate.now().minusDays(3), emp2);
		Issue issue3 = new Issue(3, "Desktop Issue", true, LocalDate.now().minusDays(9), emp3);
		Issue issue4 = new Issue(4, "Other Issue", LocalDate.now().plusDays(2), emp4);
		Issue issue5 = new Issue(5, "Other Issue", LocalDate.now().minusDays(1), emp1);
		
		List<Issue> unresolvedIssueOfEmp1 = new ArrayList<>();
		unresolvedIssueOfEmp1.add(issue1);
		unresolvedIssueOfEmp1.add(issue5);

		when(issueService.findUnresolvedIssuesOfEmployee(1)).thenReturn(unresolvedIssueOfEmp1);
		when(issueService.findUnresolvedIssuesOfEmployee(3)).thenReturn(null);
		
		log.info("Performing Unresolved issue request at URL: `/issue/unresolved-issue?empid=1`");
		mockClient.perform(
					get("/issue/unresolved-issue?empid=1")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(result -> {
					log.info("Test succesfully give the desired output!");
					log.info("Output: " + result.getResponse().getContentAsString());
				});
		
		mockClient.perform(
					get("/issue/unresolved-issue?empid=3")
				)
				.andExpect(status().isNoContent())
				.andDo(result -> {
					log.info("Test succesfully give the desired output!");
					log.info("Output: " + result.getResponse().getContentAsString());
				});
	}

	@Test
	void testGetIssuesRaisedThisMonth() throws Exception {
		log.info("Testing `GetIssueRaisedThisMonth` method of `IssueService`");
		
		Employee emp1 = new Employee(1, "Devansh", "Seth", "devansh.seth@email.com");
		Employee emp2 = new Employee(2, "Sachin", "Mishra", "sachin.mishra@email.com");
		Employee emp3 = new Employee(3, "Unnati", "Bharadwaj", "unnati.b@email.com");
		Employee emp4 = new Employee(4, "Rhythm", "Gupta", "rhythm.g@email.com");

		Issue issue1 = new Issue(1, "Cloud Issue", LocalDate.now(), emp1);
		Issue issue2 = new Issue(2, "Access Permission", LocalDate.now().minusDays(3), emp2);
		Issue issue3 = new Issue(3, "Desktop Issue", true, LocalDate.now().minusDays(9), emp3);
		Issue issue4 = new Issue(4, "Other Issue", LocalDate.now().plusDays(2), emp4);
		Issue issue5 = new Issue(5, "Other Issue", LocalDate.now().minusDays(1), emp1);
		
		List<Issue> issues = new ArrayList<>();
		issues.add(issue1);
		issues.add(issue2);
		issues.add(issue3);
		issues.add(issue4);
		issues.add(issue5);

		when(issueService.findIssuesRaisedThisMonth()).thenReturn(issues);
		
		log.info("Performing raised-issues request at URL: `/issue/raised-issues-current-month`");
		mockClient.perform(
					get("/issue/raised-issues-current-month")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(result -> {
					log.info("Test succesfully give the desired output!");
					log.info("Output: " + result.getResponse().getContentAsString());
				});
	}

	@Test
	void testGetEmployeesWhoRaisedIssuesThisMonth() throws Exception {
		log.info("Testing `GetEmployeesWhoRaisedIssuesThisMonth` method of `IssueService`");
		
		Employee emp1 = new Employee(1, "Devansh", "Seth", "devansh.seth@email.com");
		Employee emp2 = new Employee(2, "Sachin", "Mishra", "sachin.mishra@email.com");
		Employee emp3 = new Employee(3, "Unnati", "Bharadwaj", "unnati.b@email.com");
		Employee emp4 = new Employee(4, "Rhythm", "Gupta", "rhythm.g@email.com");

		Issue issue1 = new Issue(1, "Cloud Issue", LocalDate.now(), emp1);
		Issue issue2 = new Issue(2, "Access Permission", LocalDate.now().minusDays(3), emp2);
		Issue issue3 = new Issue(3, "Desktop Issue", true, LocalDate.now().minusDays(9), emp3);
		Issue issue4 = new Issue(4, "Other Issue", LocalDate.now().plusDays(2), emp4);
		Issue issue5 = new Issue(5, "Other Issue", LocalDate.now().minusDays(1), emp1);
		
		List<Issue> issues = new ArrayList<>();
		issues.add(issue1);
		issues.add(issue2);
		issues.add(issue3);
		issues.add(issue4);
		issues.add(issue5);

		when(issueService.findIssuesRaisedThisMonth()).thenReturn(issues);
		
		log.info("Performing raised-issues request at URL: `/current-month-raisedby`");
		mockClient.perform(
					get("/issue/current-month-raisedby")
					.contentType(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk())
				.andDo(result -> {
					log.info("Test succesfully give the desired output!");
					log.info("Output: " + result.getResponse().getContentAsString());
				});
	}

}
