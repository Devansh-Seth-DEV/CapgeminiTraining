package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;
import com.library.util.PersistenceManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class BookDAO {
	public void save(Book book) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			em.persist(book);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveAll(List<Book> books) {
	    try (EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager()) {
	        em.getTransaction().begin();
	        
	        for (Book book : books) {
	            Book saved = em.find(Book.class, book.getBookId());
	            
	            if (saved == null) {
	                book.setBookId(0);
	                em.persist(book); 
	            }
	        }
	        
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }   
	}
	
	public Book findById(int bookId) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			return em.find(Book.class, bookId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Book> findByTitleIgnoreCase(String title) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			String hql = "FROM Book b JOIN FETCH b.authors WHERE LOWER(b.title) = :bookTitle";
			TypedQuery<Book> query = em.createQuery(hql, Book.class);
			query.setParameter("bookTitle", title.toLowerCase());
			List<Book> books = query.getResultList();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public List<Book> findAll() {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			String hql = "SELECT DISTINCT b FROM Book b JOIN FETCH b.authors";
			TypedQuery<Book> query = em.createQuery(hql, Book.class);
			List<Book> books = query.getResultList();
			return books;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public void updateTitleById(int bookId, String newTitle) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			Book saved = em.find(Book.class, bookId);
			if (saved != null) saved.setTitle(newTitle);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteById(int bookId) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			Book saved = em.find(Book.class, bookId);
			if (saved != null) em.remove(saved);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
