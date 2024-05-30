package com.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Factory {
	
	Properties prop = new Properties();	
	private static String url;
	private static String user;
	private static String password;
	private static Connection connect;
	
	private Factory() {
		
		try {
			
			prop.load(new FileInputStream("conf.properties"));
			
			url = prop.getProperty("jdbc.url");
			user = prop.getProperty("jdbc.user");
			password = prop.getProperty("jdbc.password");
			connect = DriverManager.getConnection(url, user, password);
			
		}	
		catch(SQLException s) {
			s.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getInst(){
		
		if(connect == null){
			new Factory();
		}
		
		return connect;
		
	}
}