package com.capg.training.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class DBConnectionTest {
	@Test
	void testGetConnection() throws SQLException {
		try (
			Connection connection = DBConnection.getConnection();
		) {
			System.out.println(connection);
			assertNotNull(connection);
		}
	}

}
