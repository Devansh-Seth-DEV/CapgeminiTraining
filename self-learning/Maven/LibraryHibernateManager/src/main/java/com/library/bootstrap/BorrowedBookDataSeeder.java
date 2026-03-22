package com.library.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;
import com.library.model.BorrowedBook;
import com.library.model.User;

public class BorrowedBookDataSeeder {
	private static List<BorrowedBook> SEED_POOL = null;
	
	private static void seed() {
		final List<User> USER_SEED_POOL = UserDataSeeder.getSeed();
		final List<Book> BOOK_SEED_POOL = BookDataSeeder.getSeed();
		
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(0), BOOK_SEED_POOL.get(0), LocalDate.of(2026, 3, 1)));
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(0), BOOK_SEED_POOL.get(1), LocalDate.of(2026, 3, 2)));
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(1), BOOK_SEED_POOL.get(2), LocalDate.of(2026, 3, 2)));
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(2), BOOK_SEED_POOL.get(3), LocalDate.of(2026, 3, 3))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(3), BOOK_SEED_POOL.get(4), LocalDate.of(2026, 3, 3))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(4), BOOK_SEED_POOL.get(5), LocalDate.of(2026, 3, 4))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(5), BOOK_SEED_POOL.get(6), LocalDate.of(2026, 3, 4))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(6), BOOK_SEED_POOL.get(7), LocalDate.of(2026, 3, 5))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(7), BOOK_SEED_POOL.get(8), LocalDate.of(2026, 3, 5))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(8), BOOK_SEED_POOL.get(9), LocalDate.of(2026, 3, 6))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(9), BOOK_SEED_POOL.get(0), LocalDate.of(2026, 3, 6)));
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(1), BOOK_SEED_POOL.get(4), LocalDate.of(2026, 3, 6))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(3), BOOK_SEED_POOL.get(1), LocalDate.of(2026, 3, 6))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(4), BOOK_SEED_POOL.get(7), LocalDate.of(2026, 3, 6))); 
		SEED_POOL.add(new BorrowedBook(USER_SEED_POOL.get(7), BOOK_SEED_POOL.get(2), LocalDate.of(2026, 3, 6)));
		
		for(int i=0; i<SEED_POOL.size(); i++) {
			BorrowedBook borrow = SEED_POOL.get(i);
			borrow.setBorrowId(i+1);
		}
	}
	
	public static List<BorrowedBook> getSeed() {
		if (SEED_POOL == null) {
			SEED_POOL = new ArrayList<>();
			seed();
		}
		return SEED_POOL;
	}
}
