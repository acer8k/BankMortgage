package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

public class InsertData {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bank";
	private static final String DB_USER = "and";
	private static final String DB_PASSWORD = "and";
	
	public int insertUser(User in) throws SQLException {

		int flag = -1;
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO users"
				+ "(email, username, password) " + "VALUES"
				+ "('"+in.getEmail()+"','"+in.getUsername()+"','"+in.getPassword()+"')";
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into USERS table!");
			
			String getID = "SELECT user_Id FROM users WHERE 'username = " + in.getUsername() + "'";
			Statement getStatement = dbConnection.createStatement();
			
			ResultSet rs = getStatement.executeQuery(getID);
			
			if(rs.next()){
			flag = rs.getInt("user_Id");	
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return flag;
	}

	public boolean insertUserProfile(User_Profile in) throws SQLException {

		boolean flag = false;
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO user_profiles"
				+ "(user_Id, firstName, lastName, city, address) " + "VALUES"
				+ "('"+in.getUserId()+"','"+in.getFirstName()+"','"+in.getLastName()+"','"+in.getCity()+"','"+in.getAddress()+"')";
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into USERS table!");
			flag = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return flag;
	}
	
	public int insertAccount(Account in) throws SQLException {

		int flag = -1;
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO accounts"
				+ "(balance, intrestRate, type) " + "VALUES"
				+ "('"+in.getBalance()+"','"+in.getIntrestRate()+"','"+in.getType()+"')";
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into USERS table!");
			
			String getID = "SELECT MAX(account_Id) AS newId FROM accounts";
			Statement getStatement = dbConnection.createStatement();
			
			ResultSet rs = getStatement.executeQuery(getID);
			
			if(rs.next()){
			flag = rs.getInt("newId");	
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
return flag;
	}
	
	public boolean insertOwnership(int uId, int aId) throws SQLException {

		boolean flag = false;
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO ownerships"
				+ "(account_Id, user_Id) " + "VALUES"
				+ "('"+aId+"','"+uId+"')";
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into USERS table!");
			flag = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return flag;
	}

	public static int insertTransaction(Transaction in){

		int flag = -1;
		Connection dbConnection = null;
		Statement statement = null;

		String insertTableSQL = "INSERT INTO transactions"
				+ "(type, ammount, account_Id, time, date, oldValue) " + "VALUES"
				+ "('"+in.getType()+"','"+in.getAmmount()+"','"+in.getAccountId()+"','"+in.getTime()+"','"+in.getDate()+"','"+in.getOldValue()+"')";
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			System.out.println(insertTableSQL);

			// execute insert SQL statement
			statement.executeUpdate(insertTableSQL);

			System.out.println("Record is inserted into USERS table!");
			
			String getID = "SELECT MAX(transaction_Id) AS newId FROM transactions";
			Statement getStatement = dbConnection.createStatement();
			
			ResultSet rs = getStatement.executeQuery(getID);
			
			if(rs.next()){
			flag = rs.getInt("newId");	
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return flag;
	}
	
	public static boolean makeTransaction(Transaction in){
		boolean flag = false;
		Connection dbConnection = null;
		Statement statement = null;

		String updateTableSQL = "UPDATE accounts SET balance = balance + " + in.getAmmount() + " WHERE account_Id = " + in.getAccountId();
			//	+ "('Kashif','mkyong','system','123')";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();

			statement.executeUpdate(updateTableSQL);
			flag = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return flag;
	}
		
	

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                               DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
}