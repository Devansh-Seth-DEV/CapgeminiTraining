package com.gal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	public static void main(String[] args) {
		EntityManagerFactory emf = getEntityManagerFactory();
		System.out.println(emf);
		
		close();
	}
	
	public static void close() {
		getEntityManager().close();
		getEntityManagerFactory().close();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null || !emf.isOpen()) {
			emf = Persistence.createEntityManagerFactory("company_db_pu");
		}
		
		System.out.println(emf);
		return emf;
	}

	public static EntityManager getEntityManager() {
		if (emf == null) getEntityManagerFactory();
		
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		System.out.println(em);
		return em;
	}
}
