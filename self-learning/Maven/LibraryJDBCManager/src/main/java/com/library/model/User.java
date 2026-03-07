package com.library.model;

public class User {
	private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private LibraryCard libraryCard; // The One-to-One link
    
    public User() {
    	super();
    }

	public User(int userId, String firstName, String lastName, String email, LibraryCard libraryCard) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.libraryCard = libraryCard;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LibraryCard getLibraryCard() {
		return libraryCard;
	}

	public void setLibraryCard(LibraryCard libraryCard) {
		this.libraryCard = libraryCard;
	}

	@Override
	public String toString() {
		return String.format("User [userId=%s, firstName=%s, lastName=%s, email=%s, libraryCard=%s]", userId, firstName,
				lastName, email, libraryCard);
	}

}
