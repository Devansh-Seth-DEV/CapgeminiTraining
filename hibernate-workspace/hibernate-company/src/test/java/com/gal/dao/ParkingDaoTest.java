package com.gal.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Parking;

import jakarta.persistence.EntityManager;

public class ParkingDaoTest {
	private static ParkingDao dao = new ParkingDao();
	
	@AfterAll
	public static void closeConnections() {
		EntityManagerFactoryProvider.close();
	}
	
	@Test
	@Disabled
	public void testGetParkingById() {
		Parking parking = dao.getParkingById(100);
		assertNull(parking);
	}
	
	@Test
	public void testAddParking() {
		EntityManager em = EntityManagerFactoryProvider.getEntityManager();
		Parking parking = new Parking(13, "SlotNo 4");
		
		em.getTransaction().begin();
		Parking actualParking = dao.addParking(parking);
		em.getTransaction().commit();
		
		System.out.println("[RECIEVED]: " + actualParking);
		assertNotNull(actualParking);
	}
}
