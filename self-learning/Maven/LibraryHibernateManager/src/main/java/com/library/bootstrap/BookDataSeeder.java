package com.library.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.library.model.Author;
import com.library.model.Book;

public class BookDataSeeder {
	private static List<Book> SEED_POOL = null;
	
	private static void seed() {
		SEED_POOL.add(new Book("Effective Java", "978-0134685991"));
		SEED_POOL.add(new Book("Clean Code", "978-0132350884"));
		SEED_POOL.add(new Book("The Java Programming Language", "978-0321349804"));
		SEED_POOL.add(new Book("Head First Java", "978-0596009205"));
		SEED_POOL.add(new Book("Head First Design Patterns", "978-0596007126"));
		SEED_POOL.add(new Book("Java: The Complete Reference", "978-1260440232"));
		SEED_POOL.add(new Book("Cracking the Coding Interview", "978-0984782857"));
		SEED_POOL.add(new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "978-0201633610"));
		SEED_POOL.add(new Book("Refactoring", "978-0134757599"));
		SEED_POOL.add(new Book("Clean Architecture", "978-0134494166"));
		
		final List<Author> AUTHOR_SEED_POOL = AuthorDataSeeder.getSeed();
		SEED_POOL.get(0).setAuthors(List.of(AUTHOR_SEED_POOL.get(0)));
		SEED_POOL.get(1).setAuthors(List.of(AUTHOR_SEED_POOL.get(1)));
		SEED_POOL.get(2).setAuthors(List.of(AUTHOR_SEED_POOL.get(2)));
		SEED_POOL.get(3).setAuthors(List.of(AUTHOR_SEED_POOL.get(3), AUTHOR_SEED_POOL.get(4)));
		SEED_POOL.get(4).setAuthors(List.of(AUTHOR_SEED_POOL.get(3), AUTHOR_SEED_POOL.get(4)));
		SEED_POOL.get(5).setAuthors(List.of(AUTHOR_SEED_POOL.get(5)));
		SEED_POOL.get(6).setAuthors(List.of(AUTHOR_SEED_POOL.get(6)));
		SEED_POOL.get(7).setAuthors(List.of(AUTHOR_SEED_POOL.get(7), AUTHOR_SEED_POOL.get(8), AUTHOR_SEED_POOL.get(9)));
		SEED_POOL.get(8).setAuthors(List.of(AUTHOR_SEED_POOL.get(1)));
		SEED_POOL.get(9).setAuthors(List.of(AUTHOR_SEED_POOL.get(1)));
		
		for(int i=0; i<SEED_POOL.size(); i++) {
			Book book = SEED_POOL.get(i);
			book.setBookId(i+1);
		}
	}
	
	public static List<Book> getSeed() {
		if (SEED_POOL == null) {
			SEED_POOL = new ArrayList<>();
			seed();
		}
		return SEED_POOL;
	}
}
