package com.library.dto;

public class BookSummaryDTO {
	private String title;
	private String isbn;
	
	BookSummaryDTO(String title, String isbn) {
		this.title = title;
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}
}
