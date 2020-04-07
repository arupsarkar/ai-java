package com.ai.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {
	private static String TAG = DatabaseConnection.class.getName();
	
	public static BasicDataSource createConnectionPool() throws URISyntaxException, SQLException {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		System.out.println(new Date() + " : " + TAG + " : " + dbUrl );
		
		  if (dbUri.getUserInfo() != null) {
			  System.out.println(new Date() + " : " + TAG + " : username " + dbUri.getUserInfo().split(":")[0] );
			  System.out.println(new Date() + " : " + TAG + " : password " + dbUri.getUserInfo().split(":")[1] );
			  basicDataSource.setUsername(dbUri.getUserInfo().split(":")[0]);
			  basicDataSource.setPassword(dbUri.getUserInfo().split(":")[1]);
		  }
		  basicDataSource.setDriverClassName("org.postgresql.Driver");
		  basicDataSource.setUrl(dbUrl);
		  basicDataSource.setInitialSize(1);
			  
		return basicDataSource;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BasicDataSource connectionPool = createConnectionPool();
			System.out.println(new Date() + " : " + TAG + " : " + connectionPool.getUsername());
			
			Connection conn = connectionPool.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, text FROM tbl_tweet");
			while (rs.next()) {
			    System.out.println("Read from DB: " + rs.getString("text") + "\n");
			}			
			
			
			
		} catch (URISyntaxException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
