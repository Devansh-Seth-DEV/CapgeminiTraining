package com.library.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//1. Tells Hibernate this class is a table
@Entity
//2. Tells Hibernate the specific table name
@Table(name = "library_cards")
public class LibraryCard {
	// 3. Marks this as the Primary Key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private int cardId;
	
	@Column(name = "card_number",
			unique = true,
			nullable = false)
	private String cardNumber;
	
	@Column(name = "issue_date")
	private LocalDate issueDate;
	
	// "libraryCard" is the field name in User 
	@OneToOne(mappedBy = "libraryCard")
	private User user;
	
	// A no-argument constructor is REQUIRED
	public LibraryCard() {}

	public LibraryCard(String cardNumber, LocalDate issueDate) {
		this.cardNumber = cardNumber;
		this.issueDate = issueDate;
	}

	// Standard Getters and Setters are REQUIRED for Hibernate to work

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
