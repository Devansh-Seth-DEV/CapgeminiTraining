package com.issuetracking.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.issuetracking.model.Issue;
import com.issuetracking.repository.IssueRepository;


@Service
public class IssueServiceImpl implements IssueService {
	private static final Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

	@Autowired
	IssueRepository issueRepo;

	@Override
	public List<Issue> findUnresolvedIssuesOfEmployee(int empId) {
		log.info("Fetching unresolved issues of employeeId: " + empId);

		List<Issue> unresolvedIssues = issueRepo.findByEmployeeEmployeeIdAndIsResolvedFalse(empId);
		
		if (unresolvedIssues == null || unresolvedIssues.isEmpty()) {
			log.info("No unresolved issues found for employeeId: " + empId);
		} else {
			log.info("%s unresolved issues found for employeeId %s"
					.formatted(unresolvedIssues.size(), empId));
		}
		
		return unresolvedIssues;
	}

	@Override
	public List<Issue> findIssuesRaisedThisMonth() {
		LocalDate today = LocalDate.now();
		String currentMonth = today.getMonth().toString() + "-" + today.getYear();

		log.info("Fetching all the issues raised this month: " + currentMonth);

		List<Issue> issues = issueRepo.findIssuesRaisedThisMonth();
		
		if (issues == null || issues.isEmpty()) {
			log.info("No issue raised this month: " + currentMonth);
		} else {
			log.info("%s issues raised this month %s".formatted(issues.size(), currentMonth));
		}
		
		return issues;
	}
}
