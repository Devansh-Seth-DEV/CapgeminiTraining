package com.library.service;

import java.util.List;

import com.library.bootstrap.AuthorDataSeeder;
import com.library.dao.AuthorDAO;
import com.library.model.Author;

public class AuthorService {
	private static final AuthorDAO authorDAO = new AuthorDAO();
	
	public void createSampleAuthors() {
		List<Author> authors = AuthorDataSeeder.getSeed();
		authorDAO.saveAll(authors);
	}
	
	public List<Author> findByLastNameIgnoreCase(String lastName) {
		return authorDAO.findByLastNameIgnoreCase(lastName);
	}

	public void deleteById(int authorId) {
		authorDAO.deleteById(authorId);
	}
}
