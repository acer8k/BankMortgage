package dao;

import static dao.ConnectDB.cn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	static Connection cn;
	
	public static void ConnectToDB(){
		try{

			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "root", "4580");
		}catch(SQLException sql){
			System.err.println(sql.getMessage());
			sql.printStackTrace();
		}catch(ClassNotFoundException c){
			System.err.println(c.getMessage());
			c.printStackTrace();
		}
	}

}
