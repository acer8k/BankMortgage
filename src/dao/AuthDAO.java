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

	public static User getUserById(int userId) {
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";

		try {
			//ConnectToDB();
			 Class.forName("com.mysql.jdbc.Driver");
			 cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "and", "and");
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
		//u.se
		//u.setFirstName(firstName);
	//	u.setLastName(lastName);
	//	u.setUsername(username);
		return u;
	}

	public static int checkUserPass(String username, String password) {
		int userId = -1;
		try {
			//ConnectToDB();
			 Class.forName("com.mysql.jdbc.Driver");
			 cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "and", "and");

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
			 Class.forName("com.mysql.jdbc.Driver");
			 cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "and", "and");
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
				
				 Class.forName("com.mysql.jdbc.Driver");
				 cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "and", "and");

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
						String state, String zipcode,
						String phonenumber) {

		try {
		//	ConnectToDB();
			 Class.forName("com.mysql.jdbc.Driver");
			 cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "and", "and");
			 
			String q1 = "INSERT into user_profiles "
					+ "(user_Id, firstname, lastname, address, city)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setInt(1, userId);
			ps.setString(2, firstname);
			ps.setString(3, lastname);
			ps.setString(4, address);
			ps.setString(5, city);
	
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
