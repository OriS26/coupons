package com.ori.utils;

import java.sql.*;
import java.util.TimeZone;

public class JdbcUtils {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/couponcode?serverTimezone="+ TimeZone.getDefault().getID(), "root", "1234");
//		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/couponcode?serverTimezone=UTC", "root", "1234");
		return connection;
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		closeResources(connection, preparedStatement);
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement1,
			PreparedStatement preparedStatement2) {
		closeResources(connection, preparedStatement1);
		try {
			if (preparedStatement2 != null) {
				preparedStatement2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
