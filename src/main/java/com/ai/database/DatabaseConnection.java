package com.ai.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnection {
	private static String TAG = DatabaseConnection.class.getName();
	
	public static BasicDataSource createConnectionPool() throws URISyntaxException, SQLException {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
		
		
		  if (dbUri.getUserInfo() != null) {
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
		} catch (URISyntaxException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
