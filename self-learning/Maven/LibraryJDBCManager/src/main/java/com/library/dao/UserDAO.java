package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.db.DatabaseConnection;
import com.library.model.LibraryCard;
import com.library.model.User;

public class UserDAO {
	public User getUserWithCard(int userId) {
		String sql = "SELECT" +
					 "		u.user_id, u.first_name, u.last_name, u.email," +
					 "		lc.card_id, lc.card_number, lc.issue_date" +
					 "	FROM users u" +
					 "	JOIN library_cards lc" +
					 "		ON u.card_id = lc.card_id" +
					 "	WHERE u.user_id = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.next()) return null;
			
			// Create Library Card object first
			LibraryCard card = new LibraryCard();
			card.setCardId(rs.getInt("card_id"));
			card.setCardNumber(rs.getString("card_number"));
			card.setIssueDate(rs.getDate("issue_date").toLocalDate());


			// Create the User object
			User user = new User();
			user.setUserId(rs.getInt("user_id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			user.setEmail(rs.getString("email"));

			// Link the card with the user
			user.setLibraryCard(card);

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		UserDAO dao = new UserDAO();
		User user = dao.getUserWithCard(1);
		System.out.println(user);
	}
}
