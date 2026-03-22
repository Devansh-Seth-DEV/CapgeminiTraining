package com.library.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.library.model.Author;

public class AuthorDataSeeder {
	private static List<Author> SEED_POOL = null;
	
	private static void seed() {
		SEED_POOL.add(new Author("Joshua", "Bloch"));
		SEED_POOL.add(new Author("Robert", "Martin"));
		SEED_POOL.add(new Author("James", "Gosling"));
		SEED_POOL.add(new Author("Kathy", "Sierra"));
		SEED_POOL.add(new Author("Bert", "Bates"));
		SEED_POOL.add(new Author("Herbert", "Schildt"));
		SEED_POOL.add(new Author("Gayle", "McDowell"));
		SEED_POOL.add(new Author("Erich", "Gamma"));
		SEED_POOL.add(new Author("Richard", "Helm"));
		SEED_POOL.add(new Author("Ralph", "Johnson"));	
	}
	
	public static List<Author> getSeed() {
		if (SEED_POOL == null) {
			SEED_POOL = new ArrayList<>();
			seed();
		}
		return SEED_POOL;
	}
}
