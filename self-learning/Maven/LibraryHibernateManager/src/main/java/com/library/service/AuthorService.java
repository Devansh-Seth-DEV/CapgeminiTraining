package com.library.service;

import java.util.List;

import com.library.bootstrap.DataSeeder;
import com.library.dao.AuthorDAO;
import com.library.model.Author;
import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class AuthorService {
	private static final AuthorDAO authorDAO = new AuthorDAO();
	
	public void createSampleAuthors() {
		List<Author> authors = DataSeeder.get(Author.class);
		authorDAO.saveAll(authors);
	}
	
	public List<Author> findByLastNameIgnoreCase(String lastName) {
		return authorDAO.findByLastNameIgnoreCase(lastName);
	}

	public void deleteById(int authorId) {
		authorDAO.deleteById(authorId);
	}
	
	public List<Pair<Author, Long>> findAuthorAndBookCountNative() {
		return authorDAO.findAuthorAndBookCountNative();
	}
}
