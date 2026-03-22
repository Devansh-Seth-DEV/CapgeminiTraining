package com.library.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrow_id")
	private int borrowId;
	
	@ManyToOne
	// Points to the users table
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	// Points to the books table
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(name = "borrow_date")
	private LocalDate borrowDate;
	
	public BorrowedBook() {}

	public BorrowedBook(User user, Book book, LocalDate borrowDate) {
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
}
