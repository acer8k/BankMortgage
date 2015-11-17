package dao;

import static dao.ConnectDB.ConnectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;

import model.*;
import static dao.*;

public class AuthDAO {
	static Connection cn;
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bank";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "4580";

	public static User getUserById(int userId) {
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";
		String email = "";

		try {
			//ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String q = "SELECT username, firstName, lastName FROM users"
					+ " JOIN user_profiles ON users.user_Id=user_profiles.user_Id"
					+ " WHERE users.user_Id="
					+ userId;

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				username = rs.getString("username");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				email = rs.getString("email");

			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();

		User u = new User();
		u.setUserId(userId);
		u.setEmail(email);
/*		u.setFirstName(firstName);
		u.setLastName(lastName);*/
		u.setUsername(username);
		return u;
	}

	public static int checkUserPass(String username, String password) {
		int userId = -1;
		try {
			//ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

			//String q = "SELECT userId FROM user WHERE BINARY username='" + username + "' AND BINARY password='"
				//	+ password";

			String q ="SELECT user_Id FROM users WHERE username='"+username+"' AND password='"+password+"'";
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("user_Id"));
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		if (userId != -1)
			return userId;
		return -1;
	}

	public static boolean checkUserNameAvailable(String username) {
		try {
			//ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String q = "SELECT username FROM users";
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				if (rs.getString("username").equals(username)) {
					return false;
				}
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return true;
	}

	public static int enterNewUser(String username, String password, String email) {
		int userId = -1;
		int ID = 0;
		try {
			if (checkUserNameAvailable(username) == true) {
				//ConnectToDB();
				
				 Class.forName(DB_DRIVER);
				 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

				String q0 = "Select user_Id from users ORDER BY user_Id DESC LIMIT 1";
				Statement st = cn.createStatement();
				ResultSet rs = st.executeQuery(q0);

				if (rs.next()) {
					// rs.last(); // Get ID of last User
					ID = rs.getInt("user_Id");
					ID++;
				} else
					ID = 1; // Empty Table, so start with ID 1

				rs.close();
				st.close();

				String q1 = "insert into users values(?,?,?,?)";

				PreparedStatement ps = cn.prepareStatement(q1);
				ps.setInt(1, ID);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, email);
				ps.executeUpdate();
				ps.close();

				String q2 = "SELECT user_Id FROM users WHERE username='" + username + "'";
				st = cn.createStatement();
				rs = st.executeQuery(q2);
				while (rs.next()) {
					userId = Integer.parseInt(rs.getString("user_Id"));
				}
				rs.close();
				st.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		if (userId != -1)
			return userId;
		return -1;
	}

	public static boolean enterUsername(int userId, 
						String firstname, String lastname,
						String address, String city,
						String state_country, String zipcode,
						String phone_number) {

		try {
		//	ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			 
			String q1 = "INSERT into user_profiles "
					+ "(user_Id, firstname, lastname, address, city, state_country, zipcode, phonenumber)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, userId);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setString(4, address);
			ps.setString(5, city);
			ps.setString(6, state_country);
			ps.setString(7, zipcode);
			ps.setString(8, phone_number);
	
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		return false;
	}

	public static void DB_close() {
		try {
			if (cn != null)
				cn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
