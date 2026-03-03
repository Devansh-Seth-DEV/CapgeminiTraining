package com.gal.dao;

import com.gal.EntityManagerFactoryProvider;
import com.gal.model.Vendor;

import jakarta.persistence.EntityManager;

public class VendorDao {
	private static EntityManager em = null;
	
	static {
		em = EntityManagerFactoryProvider.getEntityManager();
	}
	
	public Vendor addVendor(Vendor vendor) {
		em.persist(vendor);
		return vendor;
	}
}
