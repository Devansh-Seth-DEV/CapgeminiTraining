package com.library.service;

import java.util.List;

import com.library.bootstrap.DataSeeder;
import com.library.dao.BorrowDAO;
import com.library.model.BorrowedBook;

public class BorrowService {
	private static final BorrowDAO borrowDAO = new BorrowDAO();
	
	public void createSampleBorrows() {
		List<BorrowedBook> borrows = DataSeeder.get(BorrowedBook.class);
		borrowDAO.saveAll(borrows);
	}
	
	public List<BorrowedBook> findAll() {
		return borrowDAO.findAll();
	}
	
	public List<BorrowedBook> findByLibraryCardNumber(String cardNumber) {
		return borrowDAO.findByLibraryCardNumber(cardNumber);
	}
}
