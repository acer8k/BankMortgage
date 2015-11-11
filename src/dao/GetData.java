package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.*;

public class GetData {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bank";
	private static final String DB_USER = "and";
	private static final String DB_PASSWORD = "and";
	
	public static ArrayList<Account> getAccounts(int userId){
		
		Statement getStatement = null;
		Connection dbConnection = null;
		ArrayList<Account> output = new ArrayList<Account>();
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM ownerships INNER JOIN accounts ON ownerships.account_Id = accounts.account_Id WHERE ownerships.user_Id = " + userId;
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
		
			while(rs.next()){
				if(rs.getString("type").equals("Mortgage")){
					MortgageAccount x = new MortgageAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
					output.add(x);
				}
				if(rs.getString("type").equals("Savings")){
					SavingsAccount x = new SavingsAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
					output.add(x);
				}
				if(rs.getString("type").equals("Checking")){
					CheckingAccount x = new CheckingAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
					output.add(x);
				}
			}
		}
		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (getStatement != null) {
				try {
					getStatement.close();
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
		//output.get(i).getAccountId;
		return output;
	}
	
	public static Account getAccountFromId(int accId){
		Statement getStatement = null;
		Connection dbConnection = null;
		Account x = null;
		
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM accounts  WHERE account_Id = " + accId;
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
			
			if(rs.next()){
				if(rs.getString("type").equals("Mortgage")){
					x = new MortgageAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
					//output.add(x);
				}
				if(rs.getString("type").equals("Savings")){
					x = new SavingsAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
					//output.add(x);
				}
				if(rs.getString("type").equals("Checking")){
					x = new CheckingAccount();
					x.setAccountId(rs.getInt("account_id"));
					x.setBalance(rs.getDouble("balance"));
					x.setIntrestRate(rs.getDouble("intrestRate"));
					x.setType(rs.getString("type"));
				//	output.add(x);
				}
			}
		
			}
		
		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (getStatement != null) {
				try {
					getStatement.close();
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
		//output.get(i).getAccountId;
		return x;
	}

	public static User_Profile getProfileFromId(int userId){
		Statement getStatement = null;
		Connection dbConnection = null;
		User_Profile output = null;
		
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM user_profiles  WHERE user_Id = " + userId;
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
			
			if(rs.next()){
				output = new User_Profile();
				output.setUserId(userId);
				output.setFirstName(rs.getString("firstName"));
				output.setLastName(rs.getString("lastName"));
				output.setCity(rs.getString("city"));
				output.setAddress(rs.getString("address"));
				output.setAccounts(getAccounts(userId));
			}
		
			}
		
		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (getStatement != null) {
				try {
					getStatement.close();
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
		//output.get(i).getAccountId;
		return output;
		
		
	}
	
	public static ArrayList<Transaction> getHistory(int accountId){
		
		Statement getStatement = null;
		Connection dbConnection = null;
		ArrayList<Transaction> output = new ArrayList<Transaction>();
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM transactions WHERE transactions.account_Id = " + accountId;
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
		
			while(rs.next()){
				Transaction in = new Transaction();
				in.setAccountId(rs.getInt("account_Id"));
				in.setAmmount(rs.getDouble("ammount"));
				in.setDate(rs.getString("date"));
				in.setOldValue(rs.getDouble("oldValue"));
				in.setTime(rs.getString("time"));
				in.setTransactionId(rs.getInt("transaction_Id"));
				in.setType(rs.getString("type"));
				output.add(in);
				}
			}
		
		catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (getStatement != null) {
				try {
					getStatement.close();
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
		//output.
		//output.get(i).toString();
		return output;
		
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