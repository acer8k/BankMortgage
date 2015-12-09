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

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
//import org.apache.commons.codec.binary.Base64;
import java.util.*;




public class AuthDAO {
	static Connection cn;
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/final_bank";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "4580";
	
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int SALT_BYTE_SIZE = 24;
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;
	
	private static boolean slowEquals(byte[] a, byte[] b)
    {
        int diff = a.length ^ b.length;
        for(int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

	public static boolean validatePassword(char[] password, String correctHash)
		        throws NoSuchAlgorithmException, InvalidKeySpecException
		    {
		        // Decode the hash into its parameters
		        String[] params = correctHash.split(":");
		        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
		        byte[] salt = fromHex(params[SALT_INDEX]);
		        byte[] hash = fromHex(params[PBKDF2_INDEX]);
		        // Compute the hash of the provided password, using the same salt, 
		        // iteration count, and hash length
		        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
		        // Compare the hashes in constant time. The password is correct if
		        // both hashes match.
		        return slowEquals(hash, testHash);
		    }
	
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
	        throws NoSuchAlgorithmException, InvalidKeySpecException
	    {
	        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
	        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
	        return skf.generateSecret(spec).getEncoded();
	    }
	
    private static byte[] fromHex(String hex)
    {
        byte[] binary = new byte[hex.length() / 2];
        for(int i = 0; i < binary.length; i++)
        {
            binary[i] = (byte)Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    public static String createHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
        {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[SALT_BYTE_SIZE];
            random.nextBytes(salt);

            // Hash the password
            byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
            // format iterations:salt:hash
            return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" +  toHex(hash);
        }
	
	public static User getUserById(int userId) {
		String username = "";
		String password = "";
		String firstName = "";
		String lastName = "";
		String email = "";
		String type = "";
		
		try {
			//ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			String q = "SELECT username, email, account_Type FROM users"
					+ " JOIN user_profiles ON users.user_Id=user_profiles.user_Id"
					+ " WHERE users.user_Id="
					+ userId;

			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				username = rs.getString("username");
				//firstName = rs.getString("firstName");
				//lastName = rs.getString("lastName");
				email = rs.getString("email");
				type = rs.getString("account_Type");

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
		u.setType(type);
/*		u.setFirstName(firstName);
		u.setLastName(lastName);*/
		u.setUsername(username);
		return u;
	}

	public static int checkUserPass(String username, String password) {
		
		
		int userId = -1;
		String hashedPass = "";
		try {
			//ConnectToDB();
			 Class.forName(DB_DRIVER);
			 cn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

			//String q = "SELECT userId FROM user WHERE BINARY username='" + username + "' AND BINARY password='"
				//	+ password";

			String q ="SELECT user_id,password FROM users WHERE username='"+username+"'";
			
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery(q);
			while (rs.next()) {
				userId = Integer.parseInt(rs.getString("user_Id"));
				hashedPass = rs.getString("password");
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		DB_close();
		
		
		try {
			if (validatePassword(password.toCharArray(),hashedPass))
				return userId;
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String type = "user";
		String hashedPass = "";
		try {
			hashedPass = createHash(password.toCharArray());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

				String q1 = "insert into users values(?,?,?,?,?)";

				PreparedStatement ps = cn.prepareStatement(q1);
				ps.setInt(1, ID);
				ps.setString(2, username);
				ps.setString(3, hashedPass);
				ps.setString(4, email);
				ps.setString(5, type);
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
