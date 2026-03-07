package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.db.DatabaseConnection;
import com.library.model.Book;

public class BookDAO {
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		
		String query = "SELECT * FROM books";
		
		try (
			Connection conn = DatabaseConnection.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		) {
			
			while(rs.next()) {
				// Extract data from current row
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String isbn = rs.getString("isbn");
				
				// Create a book object and add it to our list
				books.add(new Book(bookId, title, isbn));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	
	public void addBook(Book book) {
		String sql = "INSERT INTO books (title, isbn) VALUES (?, ?)";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			// We "set" the values for the question marks by their index (starting at 1)
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getIsbn());
			
			// Use executeUpdate for INSERT, UPDATE and DELETE
			// executeUpdate returns an `int` representing how many rows were affected in the database.
			pstmt.executeUpdate();
			System.out.println("Book added Successfully!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int id) {
		String sql = "DELETE FROM books WHERE book_id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, id);
			int rowsAffected = pstmt.executeUpdate();
			
			if (rowsAffected > 0) {
				System.out.println("Book with ID " + id + " deleted successfully!");
	        } else {
	            System.out.println("No book found with ID " + id);
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBookTitle(int id, String newTitle) {
	    String sql = "UPDATE books SET title = ? WHERE book_id = ?";

	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, newTitle); // Index 1
	        pstmt.setInt(2, id);          // Index 2
	        
	        int rowsAffected = pstmt.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            System.out.println("Book ID " + id + " updated successfully!");
	        } else {
	            System.out.println("Update failed. Book ID not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public static void main(String[] args) {
	    BookDAO bookDAO = new BookDAO();

	    // Testing Book 1
//	    Book newBook1 = new Book(0, "The Great Gatsby", "978-0743273565");
//	    bookDAO.addBook(newBook1);

	    // Testing Book 2
//	    Book newBook2 = new Book(0, "1984", "978-0451524935");
//	    bookDAO.addBook(newBook2);
	    
	    // Update the title of the book with ID 11
//	    System.out.println("Updating book title...");
//	    bookDAO.updateBookTitle(11, "The Great Gatsby - Special Edition");
	    
	    System.out.println("Current Books in Database:");
	    bookDAO.getAllBooks().forEach(System.out::println);
	}
}
