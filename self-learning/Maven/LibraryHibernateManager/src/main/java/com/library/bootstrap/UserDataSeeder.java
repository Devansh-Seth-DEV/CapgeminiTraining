package com.library.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.library.model.LibraryCard;
import com.library.model.User;

public class UserDataSeeder {
	private static List<User> SEED_POOL = null;
	
	private static void seed() {
		SEED_POOL.add(new User("Amit", "Sharma", "amit.sharma@email.com"));
		SEED_POOL.add(new User("Priya", "Patel", "priya.p@email.com"));
		SEED_POOL.add(new User("Rahul", "Verma", "r.verma@email.com"));
		SEED_POOL.add(new User("Sita", "Reddy", "sita.reddy@email.com"));
		SEED_POOL.add(new User("Arjun", "Singh", "arjun.s@email.com"));
		SEED_POOL.add(new User("Deepa", "Nair", "deepa.n@email.com"));
		SEED_POOL.add(new User("Vikram", "Seth", "v.seth@email.com"));
		SEED_POOL.add(new User("Anjali", "Gupta", "anjali.g@email.com"));
		SEED_POOL.add(new User("Rohan", "Mehta", "rohan.m@email.com"));
		SEED_POOL.add(new User("Kavita", "Rao", "kavita.rao@email.com"));

		final List<LibraryCard> CARD_SEED_POOL = LibraryCardDataSeeder.getSeed();
		for(int i=0; i<SEED_POOL.size(); i++) {
			User user = SEED_POOL.get(i);
			LibraryCard card = CARD_SEED_POOL.get(i);
			user.setLibraryCard(card);
			user.setUserId(i+1);
		}
	}
	
	
	public static List<User> getSeed() {
		if (SEED_POOL == null) {
			SEED_POOL = new ArrayList<>();
			seed();
		}
		return SEED_POOL;
	}
}
