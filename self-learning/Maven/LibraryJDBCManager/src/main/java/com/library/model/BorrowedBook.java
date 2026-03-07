package com.library.model;

import java.time.LocalDate;

public class BorrowedBook {
	private int borrowId;
    private User user;       // The whole User object
    private Book book;       // The whole Book object
    private LocalDate borrowDate;
    
    public BorrowedBook() {
    	super();
    }

	public BorrowedBook(int borrowId, User user, Book book, LocalDate borrowDate) {
		this.borrowId = borrowId;
		this.user = user;
		this.book = book;
		this.borrowDate = borrowDate;
	}

	public int getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	@Override
	public String toString() {
		return String.format("BorrowedBook [borrowId=%s, user=%s, book=%s, borrowDate=%s]", borrowId, user, book,
				borrowDate);
	}
}
