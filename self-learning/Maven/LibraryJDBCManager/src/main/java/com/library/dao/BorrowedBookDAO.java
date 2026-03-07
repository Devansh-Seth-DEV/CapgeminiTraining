package com.library.dao;

import java.sql.Connection;
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
import com.library.model.BorrowedBook;
import com.library.model.LibraryCard;
import com.library.model.User;

public class BorrowedBookDAO {
	public List<BorrowedBook> getAllBorrows() {
	    String sql = "SELECT" +
	    			 "		bb.borrow_id, bb.borrow_date," +
	                 "		u.user_id, u.first_name, u.last_name, u.email," +
	                 "		lc.card_id, lc.card_number, lc.issue_date," +
	                 "		b.book_id, b.title, b.isbn," +
	                 "		a.author_id, a.first_name AS auth_first_name, a.last_name AS auth_last_name" +
	                 "	FROM borrowed_books bb" +
	                 "	JOIN users u" +
	                 "		ON bb.user_id = u.user_id" +
	                 "	JOIN library_cards lc" + 
	                 "		ON u.card_id = lc.card_id"  +
	                 "	JOIN books b" +
	                 "		ON bb.book_id = b.book_id" +
	                 "	LEFT JOIN book_author_mapping bam" +
	                 "		ON b.book_id = bam.book_id" +
	                 "	LEFT JOIN authors a" +
	                 "		ON bam.author_id = a.author_id";
	    
	    Map<Integer, BorrowedBook> borrowMap = new LinkedHashMap<>();

	    try (Connection conn = DatabaseConnection.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {

	    	while(rs.next()) {
	    		int borrowId = rs.getInt("borrow_id");
	    		BorrowedBook borrow = borrowMap.get(borrowId);
	    		
	    		if (borrow == null) { // First time seeing the BorrowedBook record
	    			LibraryCard card = new LibraryCard();
	    			card.setCardId(rs.getInt("card_id"));
	    			card.setCardNumber(rs.getString("card_number"));
	    			card.setIssueDate(rs.getDate("issue_date").toLocalDate());
	    			
	    			User user = new User();
	    			user.setUserId(rs.getInt("user_id"));
	    			user.setFirstName(rs.getString("first_name"));
	    			user.setLastName(rs.getString("last_name"));
	    			user.setEmail(rs.getString("email"));
	    			user.setLibraryCard(card);
	    			
	    			Book book = new Book();
	    			book.setBookId(rs.getInt("book_id"));
	    			book.setTitle(rs.getString("title"));
	    			book.setIsbn(rs.getString("isbn"));
	    			
	    			borrow = new BorrowedBook();
	    			borrow.setBorrowId(borrowId);
	    			borrow.setBook(book);
	    			borrow.setUser(user);
	    			borrow.setBorrowDate(rs.getDate("borrow_date").toLocalDate());
	    			
	    			borrowMap.put(borrowId, borrow);
	    		}
	    		
	    		
	    		// add Author from the current row
	    		int authorId = rs.getInt("author_id");
	    		
	    		if (authorId > 0) { // check if author exists in this row
	    			Author author = new Author();
	    			author.setAuthorId(authorId);
	    			author.setFirstName(rs.getString("auth_first_name"));
	    			author.setLastName(rs.getString("auth_last_name"));
	    			
	    			borrow.getBook()
	    				  .getAuthors()
	    				  .add(author);
	    		}
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return new ArrayList<>(borrowMap.values());
	}
	
	public static void main(String[] args) {
		BorrowedBookDAO dao = new BorrowedBookDAO();
		List<BorrowedBook> borrowedBooks = dao.getAllBorrows();
		borrowedBooks.forEach(System.out::println);
	}
}
