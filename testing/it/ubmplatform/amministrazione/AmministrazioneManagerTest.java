package it.ubmplatform.amministrazione;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.account.Account;
import it.ubmplatform.amministrazione.AmministrazioneManager;
import it.ubmplatform.annunci.Annuncio;

public class AmministrazioneManagerTest {
	private AmministrazioneManager manager;
	private Account existingAccountR, notExistingAccount,existingAccountC,existingAccountF;
	private ArrayList<Account> lista;
	private Annuncio existingAnnuncio, notExistingAnnuncio,existingAnnuncio2;

	@Before
	public void setUp() throws Exception {
		manager = new AmministrazioneManager();
		existingAccountR = new Account("m.memoli39@studenti.unisa.it", "scientifico96", "R");
		existingAccountF = new Account("mario@studenti.unisa.it", "scientifico96", "B");
		existingAccountC = new Account("sara@studenti.unisa.it", "unisasara4", "R");
		notExistingAccount = new Account("nonesisto@studenti.unisa.it", "nonesiste", null);
		lista = new ArrayList<Account>();
		Timestamp data = new java.sql.Timestamp(System.currentTimeMillis());
		existingAnnuncio= new Annuncio(1, "ingegneria", "libro", "informatica", "123dd", "93483824", "autore", 2, "materia", "condizioni", "descrizione", 12, "m.memoli39@studenti.unisa.it",data);
		notExistingAnnuncio= new Annuncio(2, "ingegneria", "libro", "informatica", "123dd", "93483824", "autore", 2, "materia", "condizioni", "descrizione", 12, "m.memoli39@studenti.unisa.it",data);
		existingAnnuncio2= new Annuncio(3, "ingegneria", "libro", "informatica", "123dd", "93483824", "autore", 2, "materia", "condizioni", "descrizione", 12, "mario@studenti.unisa.it",data);

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
		test = manager.queryCancellaAccount(existingAccountC.getEmail());
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

	@Test
	public void testQueryRimuoviAnnuncioAccountCancellato() {
		boolean test;

		//test su un'e-mail non presente nel database
		test = manager.queryRimuoviAnnuncioAccountCancellato(notExistingAccount.getEmail());
		assertFalse(test);

		//test su un'e-mail presente nel database
		test = manager.queryRimuoviAnnuncioAccountCancellato(existingAccountR.getEmail());
		assertTrue(test);
	}

	@Test
	public void testQueryRimuoviAnnuncioNonInerente() throws SQLException {
		boolean test;

		//test su un annuncio non presente nel database
		test = manager.queryRimuoviAnnuncioNonInerente(notExistingAnnuncio.getId());
				assertFalse(test);

		//test su un annuncio presente nel database
		test = manager.queryRimuoviAnnuncioNonInerente(existingAnnuncio2.getId());
				assertTrue(test);
	}
}
