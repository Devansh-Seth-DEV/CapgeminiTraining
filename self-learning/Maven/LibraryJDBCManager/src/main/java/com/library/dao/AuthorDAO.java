package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.library.db.DatabaseConnection;
import com.library.model.Author;

public class AuthorDAO {
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
}
