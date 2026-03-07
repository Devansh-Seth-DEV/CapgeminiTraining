package com.library.model;

import java.time.LocalDate;

public class LibraryCard {
	private int cardId;
	private String cardNumber;
	private LocalDate issueDate;
	
	public LibraryCard() {
		super();
	}
	
	public LibraryCard(int cardId, String cardNumber, LocalDate issueDate) {
		this.cardId = cardId;
		this.cardNumber = cardNumber;
		this.issueDate = issueDate;
	}

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

	@Override
	public String toString() {
		return String.format("LibraryCard [cardId=%s, cardNumber=%s, issueDate=%s]", cardId, cardNumber, issueDate);
	}
}
