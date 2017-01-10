package it.ubmplatform.account;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account test,test2;

	@Before
	public void setUp() throws Exception {
		  //costruttore Account con parametri email, password,tipo
		test= new Account("m.memoli39@studenti.unisa.it", "scientifico96");
		  //costruttore Account con parametri email, password
		test2=new Account("m.memoli39@studenti.unisa.it", "scientifico96", "R");
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void testGetEmail() {
		String email= test.getEmail();
		assertEquals("m.memoli39@studenti.unisa.it",email);
		
		String email2= test2.getEmail();
		assertEquals("m.memoli39@studenti.unisa.it",email2);
	}
	
	@Test
	public void testGetPassword() {
		String password= test.getPassword();
		assertEquals("scientifico96",password);
		
		String password2= test2.getPassword();
		assertEquals("scientifico96",password2);	
	}
	
	@Test
	public void testGetTipo() {
		String tipo= test.getTipo();
		assertEquals("R",tipo);
		
		String tipo2= test2.getTipo();
		assertEquals("R",tipo2);
	}
	
	@Test
	public void testGetDataInvalidazione() {
		Date dataInvalidazione= test.getDataInvalidazione();
		assertEquals(null,dataInvalidazione);
		
		Date dataInvalidazione2= test2.getDataInvalidazione();
		assertEquals(null,dataInvalidazione2);
	}
	
	@Test
	public void testSetEmail() {
		String email="m.memoli39@studenti.unisa.it";
		test.setEmail(email);
		assertEquals("m.memoli39@studenti.unisa.it",test.getEmail());
		
		String email2="m.memoli39@studenti.unisa.it";
		test2.setEmail(email2);
		assertEquals("m.memoli39@studenti.unisa.it",test2.getEmail());
	}
	
	@Test
	public void testSetPassword() {
		String password="scientifico96";
		test.setEmail(password);
		assertEquals("scientifico96",test.getPassword());
		
		String password2="scientifico96";
		test2.setEmail(password2);
		assertEquals("scientifico96",test2.getPassword());
	}
	
	@Test
	public void testSetTipo() {
		String tipo="R";
		test.setTipo(tipo);
		assertEquals("R",test.getTipo());
		
		String tipo2="R";
		test2.setTipo(tipo2);
		assertEquals("R",test2.getTipo());
	}
	
	@Test
	public void testSetDataInvalidazione() {
		java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
		test.setDataInvalidazione(data);
		assertEquals(data, test.getDataInvalidazione());
		
		java.sql.Date data2 = new java.sql.Date(System.currentTimeMillis());
		test2.setDataInvalidazione(data2);
		assertEquals(data2, test2.getDataInvalidazione());
	}
	
}
