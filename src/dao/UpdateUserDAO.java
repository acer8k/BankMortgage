package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import static dao.ConnectDB.ConnectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Statement;

import model.*;
import static dao.*;
import dao.UpdateUserDAO;
import model.User_Profile;


public class UpdateUserDAO {
	
	static Connection cn;
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/final_bank";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "4580";
	
	public static boolean updateUserInfo(String address, String city,
			String state_country, String zipcode, String phone_number,int userId) {
		
	

		try {
			// ConnectToDB();
			Class.forName(DB_DRIVER);
			cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

		/*	String q1 = "UPDATE into user_profiles "
					+ "(address, city, state_country, zipcode, phonenumber)"
					+ " values (?, ?, ?, ?, ?) WHERE user_Id="+userId;*/

			/*UPDATE suppliers
			SET supplier_name = 'Apple',
			    product = 'iPhone'
			WHERE supplier_name = 'RIM';
			*/
			String q1 = "UPDATE USER_PROFILES SET address = ?, city = ?, state_country = ?, zipcode = ?,"
					+ "phonenumber = ? WHERE user_Id="+userId;
			
			PreparedStatement ps = cn.prepareStatement(q1);
			ps.setString(1, address);
			ps.setString(2, city);
			ps.setString(3, state_country);
			ps.setString(4, zipcode);
			ps.setString(5, phone_number);

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
