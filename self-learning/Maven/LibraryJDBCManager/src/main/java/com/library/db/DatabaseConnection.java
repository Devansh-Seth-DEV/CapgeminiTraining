package com.library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	// Connection Properties as Constants
	/// url format := jdbc:<protocol>://<serverLocation>:<portToListen>/<databaseName>
	private static final String URL = "jdbc:mysql://localhost:3306/library_jdbc_db";
	private static final String USER = "root";
	private static final String PASSWORD = "toor";
	
	// Method to get a connection
    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the library database successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed! Check your URL, Username, or Password.");
            e.printStackTrace(); // This prints the detailed error for debugging
        }

        return conn;
    } 
}
