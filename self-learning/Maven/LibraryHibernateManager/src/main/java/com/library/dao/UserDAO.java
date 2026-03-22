package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.library.model.User;
import com.library.util.PersistenceManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UserDAO {
	public void save(User user) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()){
			em.getTransaction().begin();
			
			// Because of CascadeType.ALL, this saves the Card too!
			em.persist(user);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveAll(List<User> users) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()){
			em.getTransaction().begin();
			
			for(User user: users) {
				User saved = em.find(User.class, user.getUserId());
				if (saved == null) {
					user.setUserId(0);
					em.persist(user);
				}
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public User findById(int userId) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()){
			// find(EntityClass, ID) returns the object or null if not found
			return em.find(User.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(User user) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()){
			em.getTransaction().begin();
			
			em.merge(user);
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<User> findAll() {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()){
			// Short for "SELECT u FROM User u"
			String hql = "FROM User";
			TypedQuery<User> query = em.createQuery(hql, User.class);
			List<User> users = query.getResultList();
			return users;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
