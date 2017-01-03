package it.ubmplatform.database;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DBManager extends BasicDataSource{

	private static DBManager datasource=new DBManager();
	
	private DBManager(){
		super();
		setDriverClassName("com.mysql.jdbc.Driver");
		setUrl("jdbc:mysql://localhost/ubmplatform");
		setUsername("root");
		setPassword("root");
		setMaxTotal(100);
		setInitialSize(1);
		setMaxIdle(20);
		setMaxWaitMillis(60000);
	}
	
	public static DBManager getInstance(){
		return datasource;
	}
}
