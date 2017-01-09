package it.ubmplatform.database;

import static org.junit.Assert.*;

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
	
	
}
