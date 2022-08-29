package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	public  Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";
		String user= "";
		String password= "";
		try {
			System.out.println("Sucesso no getConnection!!!");
			return DriverManager.getConnection(url, user, password);		
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Nao entrou no getConnection");
			return null;
		}				
	}
	
//	public static void testConnection() {
//		try {		
//			Connection connection = new DAO().getConnection();
//			System.out.println("Sucesso!!!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	
	
	
}
