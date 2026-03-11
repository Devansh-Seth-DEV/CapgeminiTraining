package com.capg.training.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {
	public static Connection getConnection() {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUser("root");
		ds.setPassword("toor");
		ds.setUrl("jdbc:mysql://localhost:3306/company_db");
		
		Connection connection = null;
		
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
