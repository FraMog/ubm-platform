package it.ubmplatform.database;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Test;

public class DBManagerTest {
  private DBManager dbManager;
	
    
	@After
    public void tearDown(){
		dbManager=null;
	}
	
	@Test
	public void testGetInstance(){
		dbManager=DBManager.getInstance();
		assertNotNull(dbManager);
	}
	
	@Test
	public void testGetConnection(){
		Connection connection=null;
		try {
			connection = DBManager.getInstance().getConnection();
			assertNotNull(connection);
		} catch (SQLException e){}
		
	}
	
}
