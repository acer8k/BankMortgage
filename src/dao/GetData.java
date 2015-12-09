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
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/final_bank";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "4580";
	
	public static ArrayList<User_Profile> getOwners(int aId){
		ArrayList<User_Profile> out = new  ArrayList<User_Profile>();
		 
		Statement getStatement = null;
		Connection dbConnection = null;
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT user_Id FROM ownerships WHERE account_Id = " + aId;
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
		
			while(rs.next()){
				out.add(dao.GetData.getProfileFromId(rs.getInt("user_Id")));
			}
			
		 
		}catch (SQLException e) {

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
		return out;
	}
	
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
				output.setPhone_number(rs.getString("phonenumber"));
				//output.setPhone_number(rs.getInt("phonenumber"));
				output.setZipcode(rs.getInt("zipcode"));
				output.setState_country(rs.getString("state_country"));
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
	
	public static ArrayList<User_Profile> getAllProfiles(){
		ArrayList<User_Profile> out = new ArrayList<User_Profile>();
		Statement getStatement = null;
		Connection dbConnection = null;
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM user_profiles";
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
			
			//Account in = null;
		
			while(rs.next()){
				User_Profile output = new User_Profile();
				output.setUserId(rs.getInt("user_Id"));
				output.setFirstName(rs.getString("firstName"));
				output.setLastName(rs.getString("lastName"));
				output.setCity(rs.getString("city"));
				output.setAddress(rs.getString("address"));
				output.setPhone_number(rs.getString("phonenumber"));
				//output.setPhone_number(rs.getInt("phonenumber"));
				output.setZipcode(rs.getInt("zipcode"));
				output.setState_country(rs.getString("state_country"));
				//output.setAccounts(getAccounts(userId));
				
				out.add(output);
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
		return out;
		
	}
	
	public static ArrayList<Account> getAllAccounts(){
		ArrayList<Account> out = new ArrayList<Account>();
		Statement getStatement = null;
		Connection dbConnection = null;
		
		try{
			dbConnection = getDBConnection();
		
			String getID = "SELECT * FROM accounts";
			getStatement = dbConnection.createStatement();
		
			ResultSet rs = getStatement.executeQuery(getID);
			
			//Account in = null;
		
			while(rs.next()){
				Account in = null;
				if(rs.getString("type").equals("Savings"))
					 in = new SavingsAccount();
				if(rs.getString("type").equals("Mortgage"))
					 in = new MortgageAccount();
				if(rs.getString("type").equals("Checking"))
					 in = new SavingsAccount();
			
				in.setAccountId(new Integer(rs.getString("account_Id")).intValue());
				in.setBalance(new Double(rs.getString("balance")).doubleValue());
				in.setIntrestRate(new Double(rs.getString("intrestRate")).doubleValue());
				in.setType(rs.getString("type"));
				
				out.add(in);
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
		return out;
		
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