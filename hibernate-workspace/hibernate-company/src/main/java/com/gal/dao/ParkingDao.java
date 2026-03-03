package com.gal.dao;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Parking;

import jakarta.persistence.EntityManager;

public class ParkingDao {
	private static EntityManager em = EntityManagerFactoryProvider.getEntityManager();

	public Parking getParkingById(int prkId) {
		Parking parking = em.find(Parking.class, prkId);
		return parking;
	}
	
	public Parking addParking(Parking parking) {
		em.getTransaction().begin(); // start the transaction
		em.persist(parking);		// do the changes in db
		em.getTransaction().commit(); // commit the transaction
		return parking;
	}
}
