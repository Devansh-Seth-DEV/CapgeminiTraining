package com.gal.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Vendor;

import jakarta.persistence.EntityManager;

class VendorDaoTest {
	private static VendorDao dao = new VendorDao();
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
	void testAddVendor() {
		Vendor vendor = new Vendor("Rahul Kumar");
		
		em.getTransaction().begin();
		Vendor actual = dao.addVendor(vendor);
		em.getTransaction().commit();
		
		System.out.println(actual);
		assertNotNull(actual);
	}
}
