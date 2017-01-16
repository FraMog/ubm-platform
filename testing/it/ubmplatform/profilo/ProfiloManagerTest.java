package it.ubmplatform.profilo;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.account.Account;
import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.registrazione.RegistrazioneManager;

public class ProfiloManagerTest {
	
	private Profilo creaNotOk, creaOk, modificaOk, modificaNotOk, modificaPassOk, modificaPassNotOk;
	private String emailVisualizzaOk, emailVisualizzaNotOk, emailAnnunciOk, emailAnnunciNotOk, oldPassOk, newPassOk, oldPassN, newPassN, disattivaOk, disattivaN;
	private Account accountOk;
	private ProfiloManager manager = new ProfiloManager();
	private RegistrazioneManager rManager = new RegistrazioneManager();
	
	@Before
	public void setUp() throws Exception {
		creaNotOk = new Profilo("nonFunziona@unisa.it", "Giovanni", "Ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		creaOk = new Profilo("test0@unisa.it", "Giovanni", "Ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		modificaOk = new Profilo("test0@unisa.it", "Giovanni", "Ciampi", "Salerno", "3456677890", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		modificaNotOk = new Profilo("test10@unisa.it", "Giovanni", "Ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		modificaPassOk = new Profilo("test0@unisa.it", "Giovanni", "Ciampi", "Salerno", "3456677890", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		modificaPassNotOk = new Profilo("test0@unisa.it", "Giovanni", "Ciampi", "Salerno", "3456677890", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
		newPassOk = "Password2";
		oldPassOk = "Password1";
		oldPassN = "Password2";
		newPassN = "Password1";
		accountOk = new Account("test0@unisa.it", "Password1");
		disattivaOk = "test0@unisa.it";
		disattivaN = "marco192@unisa.it";
		emailVisualizzaOk = "test0@unisa.it";
	}

	@After
	public void tearDown() throws Exception {
		creaNotOk = null;
		creaOk= null;
		modificaOk= null;
		modificaNotOk= null;
		modificaPassOk= null;
		modificaPassNotOk= null;
		oldPassN= null;
		oldPassOk= null;
		newPassN= null;
		newPassOk= null;
		accountOk= null;
		disattivaN= null;
		disattivaOk= null;
	}

	@Test
	public void testCreaProfilo() {
		try {
			boolean status = manager.queryCreaProfilo(creaNotOk);
			assertFalse(status);
		} catch (SQLException e) {
			fail("Non deve lanciare eccezioni");
		}
		try {
			rManager.queryRegistraAccount(accountOk);
			boolean status = manager.queryCreaProfilo(creaOk);
			assertTrue(status);
		} catch (SQLException e) {
			fail("Non deve lanciare eccezioni");
		}
		
	}
	
	@Test
	public void testModificaProfilo() {
		try {
			boolean status = manager.queryModificaProfilo(modificaNotOk);
			fail("deve lanciare eccezione");
		} catch (Exception e) {
			
		}
		try {
			boolean status = manager.queryModificaProfilo(modificaOk);
			assertTrue(status);
		} catch (Exception e) {
			fail("Non deve lanciare eccezioni");
		}
		
	}
	@Test
	public void testModificaProfiloPassword() {
		try {
			boolean status = manager.queryModificaProfiloPassword(modificaPassNotOk, oldPassN, newPassN);
			fail("deve lanciare eccezione");
		} catch (Exception e) {
		}
		try {
			boolean status = manager.queryModificaProfiloPassword(modificaPassOk, oldPassOk, newPassOk);
			assertTrue(status);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Non deve lanciare eccezioni");
		}
		
	}
	

	@Test
	public void testVisualizzaProfilo() {
		try {
			Profilo p = manager.queryVisualizzaProfilo(emailVisualizzaNotOk);
			fail("deve lanciare eccezione");
		} catch (Exception e) {
		}
		try {
			Profilo p = manager.queryVisualizzaProfilo(emailVisualizzaOk);
		} catch (Exception e) {
			fail("Non deve lanciare eccezioni");
		}
		
	}
	
	@Test
	public void testGetElencoAnnunci() {
		try {
			ArrayList<Annuncio> p = manager.queryGetElencoAnnunci(emailAnnunciNotOk);
			assertEquals(0, p.size());
		} catch (Exception e) {
			fail("non deve lanciare eccezione");
		}
		try {
			ArrayList<Annuncio> p = manager.queryGetElencoAnnunci(emailAnnunciOk);
			int size = p.size();
		} catch (Exception e) {
			fail("Non deve lanciare eccezioni");
		}
		
	}
	
	@Test
	public void testDisattivaProfilo() {
		try {
			boolean status = manager.queryDisattivaProfilo(disattivaN);
			assertFalse(status);
		} catch (Exception e) {
		}
		try {
			boolean status = manager.queryDisattivaProfilo(disattivaOk);
			assertTrue(status);
		} catch (Exception e) {
			fail("Non deve lanciare eccezioni");
		}
		
	}
	

}
