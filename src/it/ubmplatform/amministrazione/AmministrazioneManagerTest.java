package it.ubmplatform.amministrazione;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.account.Account;

public class AmministrazioneManagerTest {
	private AmministrazioneManager manager;
	private Account existingAccountR, notExistingAccount;
	private ArrayList<Account> lista;
	
	@Before
	public void setUp() throws Exception {
		manager = new AmministrazioneManager();
		existingAccountR = new Account("m.memoli39@studenti.unisa.it", "scientifico96", "R");
		notExistingAccount = new Account("nonesisto@studenti.unisa.it", "nonesiste", null);
		lista = new ArrayList<Account>();
	}

	@After
	public void tearDown() throws Exception {
		manager = null;
		existingAccountR = null;
		notExistingAccount = null;
		lista=null;
	}

	@Test
	public void testQueryCancellaAccount() {
		boolean test;
		
		//test su un'e-mail non presente nel database
		test = manager.queryCancellaAccount(notExistingAccount.getEmail());
		assertFalse(test);
		
		//test su un'e-mail presente nel database
		test = manager.queryCancellaAccount(existingAccountR.getEmail());
		assertTrue(test);
	}
	
	@Test
	public void testQueryInvalidaAccount() {
		boolean test;
		
		//test su un'e-mail non presente nel database
		test = manager.queryInvalidaAccount(notExistingAccount.getEmail());
		assertFalse(test);
		
		//test su un'e-mail presente nel database
		test = manager.queryInvalidaAccount(existingAccountR.getEmail());
		assertTrue(test);
	}
	
	@Test
	public void testQueryVisualizzaListaUtenti() throws SQLException{

		
		//test che la query restituisce un arraylist non null;
		lista = manager.queryVisualizzaListaUtenti();
		assertNotNull(lista);	

	}
}
