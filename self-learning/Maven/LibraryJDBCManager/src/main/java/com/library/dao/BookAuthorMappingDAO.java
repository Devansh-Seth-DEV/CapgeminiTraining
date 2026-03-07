package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.library.db.DatabaseConnection;
import com.library.model.Author;
import com.library.model.Book;

public class BookAuthorMappingDAO {
	public void linkBookToAuthor(int bookId, int authorId) {
	    String sql = "INSERT INTO book_author_mapping (book_id, author_id) VALUES (?, ?)";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, bookId);
	        pstmt.setInt(2, authorId);
	        
	        pstmt.executeUpdate();
	        System.out.println("Link created: Book " + bookId + " <-> Author " + authorId);
	        
	    } catch (SQLException e) {
	        // This might fail if the link already exists (Duplicate Primary Key)
	        e.printStackTrace();
	    }
	}
	
	public List<Book> getAllBooksWithAuthors() {
	    // We use a Map to track books we've already created
	    Map<Integer, Book> bookMap = new LinkedHashMap<>();
	    
	    String sql = "SELECT b.book_id, b.title, b.isbn, a.author_id, a.first_name, a.last_name" +
	                 " FROM books b" +
	                 " JOIN book_author_mapping bam ON b.book_id = bam.book_id" +
	                 " JOIN authors a ON bam.author_id = a.author_id";

	    try (Connection conn = DatabaseConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	        while (rs.next()) {
	            int bookId = rs.getInt("book_id");

	            // Check if the book is already in our map
	            Book book = bookMap.get(bookId);
	            
	            if (book == null) {
	                // First time seeing this book? Create it!
	                book = new Book(
	                			bookId,
	                			rs.getString("title"),
	                			rs.getString("isbn"),
	                			new ArrayList<>() // Initialize the author list
	                		);
	                
	                bookMap.put(bookId, book);
	            }

	            // Create the Author object for this specific row
	            Author author = new Author(
	            			rs.getInt("author_id"),
	            			rs.getString("first_name"),
	            			rs.getString("last_name")
	            		);

	            // Add the author to the book's list
	            book.getAuthors().add(author);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    // Convert the map values back into a simple List
	    return new ArrayList<>(bookMap.values());
	}
	
	public static void main(String[] args) {
		BookAuthorMappingDAO dao = new BookAuthorMappingDAO();
		List<Book> books = dao.getAllBooksWithAuthors();
		books.forEach(System.out::println);
	}
}
