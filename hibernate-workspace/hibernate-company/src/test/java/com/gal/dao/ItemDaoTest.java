package com.gal.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Item;

import jakarta.persistence.EntityManager;

class ItemDaoTest {
	private static ItemDao dao = new ItemDao();
	private static EntityManager em = null;
	
	@BeforeAll
	public static void openConnections() {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	@AfterAll
	public static void closeConnections() {
		EntityManagerFactoryProvider.close();
	}

	@Test
	void testAddItem() {
		Item item = new Item("Electronics",
							 "Laptop",
							 55000);
		
		em.getTransaction().begin();
		Item actual = dao.addItem(item);
		em.getTransaction().commit();
		
		System.out.print(actual);
		assertNotNull(actual);
	}

}
