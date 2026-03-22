package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.library.model.BorrowedBook;
import com.library.util.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class BorrowDAO {
    public void save(BorrowedBook borrow) {
        try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
            em.getTransaction().begin();

            em.persist(borrow);

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void saveAll(List<BorrowedBook> borrows) {
        try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
			em.getTransaction().begin();
			
			for(BorrowedBook borrow: borrows) {
				BorrowedBook saved = em.find(BorrowedBook.class, borrow.getBorrowId());
				if (saved == null) {
					em.merge(borrow);
				}
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
    
	public BorrowedBook findById(int borrowId) {
        try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
			return em.find(BorrowedBook.class, borrowId);
		} catch (Exception e) {
			e.printStackTrace();
		}	
        return null;
	}
	
	public List<BorrowedBook> findAll() {
        try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
        	String hql = "FROM BorrowedBook b";
        	TypedQuery<BorrowedBook> query = em.createQuery(hql, BorrowedBook.class);
        	List<BorrowedBook> borrows = query.getResultList();
        	return borrows;
		} catch (Exception e) {
			e.printStackTrace();
		}	
        return new ArrayList<>();
	}
	
	public List<BorrowedBook> findByLibraryCardNumber(String cardNumber) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
        		.createEntityManager()) {
        	String hql = "FROM BorrowedBook b WHERE LOWER(b.user.libraryCard.cardNumber) = :libraryCardNumber";
        	TypedQuery<BorrowedBook> query = em.createQuery(hql, BorrowedBook.class);
        	query.setParameter("libraryCardNumber", cardNumber.toLowerCase());
        	List<BorrowedBook> borrows = query.getResultList();
        	return borrows;
		} catch (Exception e) {
			e.printStackTrace();
		}	
        return new ArrayList<>();	
	}
}