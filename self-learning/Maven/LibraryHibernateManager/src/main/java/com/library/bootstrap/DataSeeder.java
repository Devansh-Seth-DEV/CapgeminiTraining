package com.library.bootstrap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.model.Author;
import com.library.model.Book;
import com.library.model.BorrowedBook;
import com.library.model.LibraryCard;
import com.library.model.User;

public class DataSeeder {
	private DataSeeder() {}
	
	public static <T> List<T> get(Class<T> entityClass) {
		if (entityClass == Book.class)
			return (List<T>) BookDataSeeder.getSeed();

		if (entityClass == Author.class)
			return (List<T>) AuthorDataSeeder.getSeed();

		if (entityClass == User.class)
			return (List<T>) UserDataSeeder.getSeed();

		if (entityClass == LibraryCard.class)
			return (List<T>) LibraryCardDataSeeder.getSeed();

		if (entityClass == BorrowedBook.class)
			return (List<T>) BorrowedBookDataSeeder.getSeed();
		
		return null;
	}
}
