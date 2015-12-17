/**
 * Project: A00964856_Assignment5
 * Date: Dec 4, 2015
 * Time: 12:01:43 PM
 */
package ca.bcit.a00964856.controller.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Natan Hoppe, A00964856
 *
 */
public class Database {
	private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://Beangrinder.bcit.ca";

	private static final String USER = "javastudent";
	private static final String PASSWORD = "compjava";

	private static Connection dbConnection;
	private static Statement statement;

	private static void prepareStatement() throws SQLException, ClassNotFoundException {
		connect();
		statement = dbConnection.createStatement();
	}

	public static void connect() throws SQLException, ClassNotFoundException {
		Class.forName(DB_DRIVER);
		if (dbConnection == null) {
			dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
		}
	}

	public static boolean createTable(String createQuery) throws SQLException, ClassNotFoundException {
		prepareStatement();
		return statement.execute(createQuery);
	}

	public static int insertData(String insertQuery) throws SQLException, ClassNotFoundException {
		prepareStatement();
		return statement.executeUpdate(insertQuery);
	}

	public static ResultSet getData(String selectQuery) throws SQLException, ClassNotFoundException {
		prepareStatement();
		ResultSet result = statement.executeQuery(selectQuery);
		return result;
	}

	public static boolean dropTable(String dropQuery) throws SQLException, ClassNotFoundException {
		prepareStatement();
		return statement.execute(dropQuery);
	}

	public static void shutdown() throws SQLException {
		if (dbConnection != null && !dbConnection.isClosed()) {
			dbConnection.close();
			dbConnection = null;
		}
	}

	public static boolean tableExists(String tableName) throws SQLException, ClassNotFoundException {
		connect();
		DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
		ResultSet resultSet = databaseMetaData.getTables(dbConnection.getCatalog(), "%", "%", null);
		String name = null;
		while (resultSet.next()) {
			name = resultSet.getString("TABLE_NAME");
			if (name.equalsIgnoreCase(tableName)) {
				return true;
			}
		}
		return false;
	}
}
