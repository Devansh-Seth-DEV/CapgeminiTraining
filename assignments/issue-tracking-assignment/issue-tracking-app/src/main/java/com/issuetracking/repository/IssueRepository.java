package com.issuetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.issuetracking.model.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
	public List<Issue> findByEmployeeEmployeeIdAndIsResolvedFalse(int empId);
	
	@Query("""
		FROM
			Issue i
		WHERE
			MONTH(i.raiseDate) = MONTH(CURRENT_DATE)
			AND YEAR(i.raiseDate) = YEAR(CURRENT_DATE)
	""")
	public List<Issue> findIssuesRaisedThisMonth();
}
