package com.library.service;

import java.util.List;

import com.library.bootstrap.BookDataSeeder;
import com.library.dao.BookDAO;
import com.library.model.Book;

public class BookService {
	private static final BookDAO bookDAO = new BookDAO();
	
	public void createSampleBooks() {
		List<Book> books = BookDataSeeder.getSeed();
		bookDAO.saveAll(books);
	}
	
	public List<Book> findAll() {
		List<Book> books = bookDAO.findAll();
		return books;
	}
	
	public void updateTitleById(int bookId, String newTitle) {
		bookDAO.updateTitleById(bookId, newTitle);
	}

	public Book findById(int bookId) {
		return bookDAO.findById(bookId);
	}
	
	public void save(Book book) {
		bookDAO.save(book);
	}

	public void deleteById(int bookId) {
		bookDAO.deleteById(bookId);
	}
}
