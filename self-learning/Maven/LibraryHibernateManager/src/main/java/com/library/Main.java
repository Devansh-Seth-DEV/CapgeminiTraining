package com.library;

import com.library.runner.DevTestRunner;
import com.library.util.PersistenceManager;

import jakarta.persistence.EntityManager;

public class Main {
	private static final DevTestRunner devTestRunner = new DevTestRunner();
	
    public static void main(String[] args) {
    	checkHibernateConnection();
    	devTestRunner.run();
    }
    
    public static void checkHibernateConnection() {
        try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
        	if (em.isOpen()) {
        		System.out.println("Hibernate has Started Successfully!");
        	} else {
        		System.out.println("Failed to Start Hibernate!!!");
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
}