package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.db.DatabaseConnection;
import com.library.model.Author;

public class AuthorDAO {
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<>();
		
		String sql = "SELECT * FROM authors";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			
			while(rs.next()) {
				Author author = new Author();
				author.setAuthorId(rs.getInt("author_id"));
				author.setFirstName(rs.getString("first_name"));
				author.setLastName(rs.getString("last_name"));
				
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return authors;
	}

	public void saveAuthor(Author author) {
	    String sql = "INSERT INTO authors (first_name, last_name) VALUES (?, ?)";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, author.getFirstName());
	        pstmt.setString(2, author.getLastName());
	        
	        pstmt.executeUpdate();
	        System.out.println("Author saved successfully!");
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		AuthorDAO dao = new AuthorDAO();
		List<Author> authors = dao.getAllAuthors();
		authors.forEach(System.out::println);
	}
}
