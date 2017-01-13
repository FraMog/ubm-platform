package it.ubmplatform.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBManager {

	private static DBManager manager=new DBManager();
	private static BasicDataSource datasource;
	
	private DBManager(){
		datasource=new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost/ubmplatform");
		datasource.setUsername("root");
		datasource.setPassword("root");
		datasource.setMaxTotal(100);
		datasource.setInitialSize(1);
		datasource.setMaxIdle(20);
		datasource.setMaxWaitMillis(60000);
	}
	
	public Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
	public static DBManager getInstance(){
		return manager;
	}
}
