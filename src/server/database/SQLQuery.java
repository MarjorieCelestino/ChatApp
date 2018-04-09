package server.database;

import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author marge
 */
public class SQLQuery {	

	public static void createTable() throws Exception{
		Statement statement = MySQLConnection.connection.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS users " +
				"(username VARCHAR(100) NOT NULL, " +
				" password VARCHAR(100) NOT NULL)";
		statement.executeUpdate(sql);
		System.out.println("Table created, if not exists");
	}

	public static void createNewUser(String username, String password) throws Exception{
		Statement statement = MySQLConnection.connection.createStatement();
		String sql = "INSERT INTO users (username, password)" +
				"VALUES ('" + username + "', '" + password + "')";
		statement.executeUpdate(sql);
		System.out.println("New user added to the database");
	}

	public static Boolean findUser(String username) throws Exception{
		Boolean userExists = false;
		Statement statement = MySQLConnection.connection.createStatement();
		String sql = "SELECT * FROM users WHERE username = '" + username + "'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			userExists = true;
		}
		return userExists;
	}

	public static String findUserPassword(String username) throws Exception{
		String password = "";
		Statement statement = MySQLConnection.connection.createStatement();
		String sql = "SELECT password FROM users WHERE username = '" + username + "'";
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			password = rs.getString("password");
		}
		return password;
	}
}
