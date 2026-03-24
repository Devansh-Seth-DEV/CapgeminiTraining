package com.library.service;

import java.util.List;

import com.library.bootstrap.DataSeeder;
import com.library.dao.UserDAO;
import com.library.model.User;

public class UserService {
	private static final UserDAO userDAO = new UserDAO();

	public void createSampleUsers() {
		List<User> users = DataSeeder.get(User.class);
		userDAO.saveAll(users);
	}
	
	public void updateEmail(int userId, String newEmail) {
		User user = userDAO.findById(userId);
		if (user == null) {
			System.out.println("Failed to update email, No user found with ID: " + userId);
		}
		
		user.setEmail(newEmail);
		
		userDAO.update(user);
		System.out.println("%s %s's email has be updated to %s successfully"
				.formatted(user.getFirstName(), user.getLastName(), user.getEmail()));
	}
}
