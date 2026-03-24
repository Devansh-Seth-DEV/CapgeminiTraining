package com.issuetracking.service;

import java.util.List;

import com.issuetracking.model.Issue;

public interface IssueService {
	public List<Issue> findUnresolvedIssuesOfEmployee(int empId);
	
	public List<Issue> findIssuesRaisedThisMonth();
}
