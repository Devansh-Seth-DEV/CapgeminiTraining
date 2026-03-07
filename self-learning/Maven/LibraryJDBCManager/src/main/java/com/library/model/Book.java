package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Book {
	private int bookId;
	private String title;
	private String isbn;
	
	private List<Author> authors; // Collection to hold multiple authors

	public Book() {
		super();
		authors = new ArrayList<>();
	}

	public Book(int bookId, String title, String isbn) {
		this.bookId = bookId;
		this.title = title;
		this.isbn = isbn;
	}

	public Book(int bookId, String title, String isbn, List<Author> authors) {
		this.bookId = bookId;
		this.title = title;
		this.isbn = isbn;
		this.authors = authors;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return String.format("Book [bookId=%s, title=%s, isbn=%s, authors=%s]", bookId, title, isbn, authors);
	}
}
