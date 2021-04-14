package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PingJdbc {
	static ConnectionFactory connectionfactory;
	
	
	public static ConnectionFactory getConnectionfactory() {
		return connectionfactory;}


		public static Connection getConnectionByProperties() throws IOException, FileNotFoundException{
	      Properties properties = new Properties();
	      FileInputStream input = new FileInputStream("jdbc.properties");
	      try{
	         properties.load(input);
	      }finally{
		         input.close();
		         
		  }
	        
	         try {
	        	Class.forName(properties.getProperty("jdbc.driver.class"));
	 			
	 		}catch (ClassNotFoundException e) {
	 			System.out.println("Driver non présent dans le CLASSPATH  -  " + e.getMessage());
	 			System.exit(1);
	 		}

	 		//Ouvrir une connexion 

	 		try {
	 			Connection cnx = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.login"), properties.getProperty("jdbc.password"));
	 			cnx.setAutoCommit(true);
	 			
	 			return cnx;
	 			
	 		} catch (SQLException e1) {
	 			System.out.println("Pb pour atteindre la BD  -  " + e1.getMessage());
	 			System.exit(2);
	 		}
	 		return null;
	   } 
		
		
	
}
