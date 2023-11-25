package test;

import java.sql.*;
import java.util.*;
import java.sql.Connection.*;

public class FirstDatabase {
	
	static String url = "jdbc:mysql://localhost:3306/mydb";
	static String user = "root";
	static String pass = "goldwood123";
	
	public static void main(String[] args) {
		FirstDatabase obj = new FirstDatabase();
		obj.createConnection();
	}
	
	void createConnection() {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users2");
			
			while(rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.println(name + " = " + age + " years old");
			}
			
			System.out.println("\nConnected Successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
