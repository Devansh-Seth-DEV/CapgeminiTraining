package com.library.bootstrap;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library.model.LibraryCard;

public class LibraryCardDataSeeder {
	private static List<LibraryCard> SEED_POOL = null;

	private static void seed() {
		SEED_POOL.add(new LibraryCard("LC-1001", LocalDate.of(2024, 1, 10)));
		SEED_POOL.add(new LibraryCard("LC-1002", LocalDate.of(2024, 1, 12)));
		SEED_POOL.add(new LibraryCard("LC-1003", LocalDate.of(2024, 1, 15)));
		SEED_POOL.add(new LibraryCard("LC-1004", LocalDate.of(2024,2,1)));
		SEED_POOL.add(new LibraryCard("LC-1005", LocalDate.of(2024,2,5)));
		SEED_POOL.add(new LibraryCard("LC-1006", LocalDate.of(2024,2,10)));
		SEED_POOL.add(new LibraryCard("LC-1007", LocalDate.of(2024,2,20)));
		SEED_POOL.add(new LibraryCard("LC-1008", LocalDate.of(2024,3,1)));
		SEED_POOL.add(new LibraryCard("LC-1009", LocalDate.of(2024,3,5)));
		SEED_POOL.add(new LibraryCard("LC-1010", LocalDate.of(2024,3,12)));
	}
	
	public static List<LibraryCard> getSeed() {
		if (SEED_POOL == null) {
			SEED_POOL = new ArrayList<>();
			seed();
		}
		return SEED_POOL;
	}
}
