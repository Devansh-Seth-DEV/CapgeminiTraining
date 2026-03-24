package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.library.model.Author;
import com.library.util.PersistenceManager;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class AuthorDAO {
	public void save(Author author) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			em.persist(author);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveAll(List<Author> authors) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			
			for(Author author: authors) {
				Author saved = em.find(Author.class, author.getAuthorId());
				if (saved == null) {
					em.merge(author);
				}
			}
			
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public Author findById(int authorId) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			return em.find(Author.class, authorId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Author> findByLastNameIgnoreCase(String lastName) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			String hql = "FROM Author a WHERE LOWER(a.lastName) = :authorLastName";
			TypedQuery<Author> query = em.createQuery(hql, Author.class);
			query.setParameter("authorLastName", lastName.toLowerCase());
			List<Author> authors = query.getResultList();
			return authors;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public void deleteById(int authorId) {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			em.getTransaction().begin();
			Author saved = em.find(Author.class, authorId);
			if (saved != null) em.remove(saved);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Pair<Author, Long>> findAuthorAndBookCountNative() {
		try (EntityManager em = PersistenceManager.getEntityManagerFactory()
				.createEntityManager()) {
			String sql = """
					SELECT
						a.*,
						COUNT(bam.book_id) AS book_count
					FROM
						authors a
					JOIN
						book_author_mapping bam 
							ON bam.author_id = a.author_id
					GROUP BY
						a.author_id
					""";
			
			Query query = em.createNativeQuery(sql);
			List<Object[]> rs = query.getResultList();
			
			List<Pair<Author, Long>> result = new ArrayList<>();
			for(Object[] row: rs) {
				Author author = new Author();
				author.setFirstName((String) row[1]);
				author.setLastName((String) row[2]);
				
				Long bookCount = (Long) row[3];
				Pair<Author, Long> p = new Pair<Author, Long>(author, bookCount);
				result.add(p);
			}
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
}
