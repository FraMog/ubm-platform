package it.ubmplatform.annunci;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.eccezioni.BadAnnuncioIdException;
import it.ubmplatform.eccezioni.BadInputAnnuncioException;
import it.ubmplatform.eccezioni.BadResearchException;

public class AnnuncioManagerTest {
	
	private AnnuncioManager manager;
	private Annuncio esistenteOk, nonEsistenteOk, emailErrata, fotoNull, titoloNull, descrizioneNull, facoltaNull, categoriaNull, ricercaPerFacoltaOk, ricercaTitoloNull, ricercaCategoriaErrata, ricercaOk;
	private int idEsistente, idNonEsistente, idErrato;
	private String fotoEsistente, fotoNonEsistente;

	@Before
	public void setUp() throws Exception {
		manager=new AnnuncioManager();
		idEsistente=1;
		idNonEsistente=200;
		idErrato=-1;
		esistenteOk=new Annuncio(2,"Lezioni di C","L","Matematica", "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		nonEsistenteOk=new Annuncio(20,"Lezioni di C","L","Matematica", "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		emailErrata=new Annuncio(21,"Lezioni di C","L","Matematica", "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "nonEsistente@unisa.it", new java.sql.Date(2017,1,4));
		fotoNull=new Annuncio(22,"Lezioni di C","L","Matematica", null , "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		titoloNull=new Annuncio(24, null ,"L","Matematica", "fotoannuncio" , "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		descrizioneNull=new Annuncio(26,"Lezioni di C","L","Matematica", "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato", null, 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		facoltaNull=new Annuncio(27,"Lezioni di C","L", null , "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		categoriaNull=new Annuncio(28,"Lezioni di C", null ,"Matematica", "fotoannuncio", "22444-53", "Prova Tartaglione", 1, "Informatica","Sottolineato","Lezioni di base di C", 23.2, "prova@unisa.it", new java.sql.Date(2017,1,4));
		ricercaPerFacoltaOk=new Annuncio();
		ricercaPerFacoltaOk.setFacolta("Informatica");
		ricercaTitoloNull=new Annuncio();
		ricercaTitoloNull.setFacolta("Informatica");
		ricercaTitoloNull.setCategoria("libro");
		ricercaTitoloNull.setTitolo(null);
		ricercaCategoriaErrata=new Annuncio();
		ricercaCategoriaErrata.setTitolo("appunti");
		ricercaCategoriaErrata.setFacolta("Informatica");
		ricercaCategoriaErrata.setCategoria("appnti");
		ricercaOk=new Annuncio();
		ricercaOk.setTitolo("appunti");
		ricercaOk.setFacolta("Informatica");
		ricercaOk.setCategoria("appunti");
		fotoEsistente="fotoannuncio";
		fotoNonEsistente="foto";
	}

	@After
	public void tearDown() throws Exception {
		esistenteOk=null;
		nonEsistenteOk=null;
		emailErrata=null;
		fotoNull=null;
		titoloNull=null;
		descrizioneNull=null;
		facoltaNull=null;
		categoriaNull=null;
		ricercaPerFacoltaOk=null;
		ricercaTitoloNull=null;
		ricercaCategoriaErrata=null;
		ricercaOk=null;
		
	}

	@Test
	public void testQueryCancellaAnnuncio() {
		boolean status;
		status=manager.queryCancellaAnnuncio(idEsistente);
		assertTrue(status);
		status=manager.queryCancellaAnnuncio(idNonEsistente);
		assertFalse(status);
		status=manager.queryCancellaAnnuncio(idErrato);
		assertFalse(status);
	}

	@Test
	public void testQueryInserisciAnnuncio() {
		boolean status;
		try {
			status=manager.queryInserisciAnnuncio(nonEsistenteOk);
			assertTrue(status);
		} catch (InvalidAttributeValueException | SQLException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
		try {
			manager.queryInserisciAnnuncio(emailErrata);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
		try {
			manager.queryInserisciAnnuncio(fotoNull);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
		try {
			manager.queryInserisciAnnuncio(titoloNull);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
		try {
			manager.queryInserisciAnnuncio(categoriaNull);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
		try {
			manager.queryInserisciAnnuncio(descrizioneNull);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
		try {
			manager.queryInserisciAnnuncio(facoltaNull);
			fail("dovrebbe lanciare eccezioni");
		} catch (InvalidAttributeValueException | SQLException e) {
		}
	}

	@Test
	public void testQueryOttieniAnnuncioDaModificare() {
		Annuncio a;
		try {
			a=manager.queryOttieniAnnuncioDaModificare(idEsistente);
			assertNotNull(a);
		} catch (BadInputAnnuncioException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
		try {
			a=manager.queryOttieniAnnuncioDaModificare(idNonEsistente);
			assertNull(a);
		} catch (BadInputAnnuncioException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
		try {
			a=manager.queryOttieniAnnuncioDaModificare(idErrato);
			assertNull(a);
		} catch (BadInputAnnuncioException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
	}

	@Test
	public void testQueryModificaAnnuncio() {
		boolean status;
		try {
			status=manager.queryModificaAnnuncio(esistenteOk);
			assertTrue(status);
		} catch (InvalidAttributeValueException | SQLException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
		try {
			status=manager.queryModificaAnnuncio(nonEsistenteOk);
			assertFalse(status);
		} catch (InvalidAttributeValueException | SQLException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
	}

	@Test
	public void testQueryRicercaAnnuncio() {
		ArrayList<Annuncio> a=null;
		try {
			a=manager.queryRicercaAnnuncio(ricercaCategoriaErrata, "Prezzo");
			fail("dovrebbe lanciare eccezioni");
		} catch (BadResearchException e1) {
			assertNull(a);
		}
		try {
			a=manager.queryRicercaAnnuncio(ricercaTitoloNull,"Prezzo");
			fail("dovrebbe lanciare eccezioni");
		} catch (BadResearchException e1) {
			assertNull(a);
		}
		try {
			a=manager.queryRicercaAnnuncio(ricercaOk, "titolo");
			fail("dovrebbe lanciare eccezioni");
		} catch (BadResearchException e1) {
			assertNull(a);
		}
		try {
			a=manager.queryRicercaAnnuncio(ricercaOk, "Prezzo");
			assertNotNull(a);
		} catch (BadResearchException e1) {
			fail("non dovrebbe lanciare eccezioni");
		}
		try {
			a=manager.queryRicercaAnnuncio(ricercaPerFacoltaOk, null);
			assertNotNull(a);
		} catch (BadResearchException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
	}

	@Test
	public void testQueryVisualizzaDettagliAnnuncio() {
		Annuncio a=null;
		try {
			a=manager.queryVisualizzaDettagliAnnuncio(idNonEsistente);
			fail("dovrebbe lanciare eccezioni");
		} catch (BadAnnuncioIdException e) {
			assertNull(a);
		}
		try {
			a=manager.queryVisualizzaDettagliAnnuncio(idNonEsistente);
			fail("dovrebbe lanciare eccezioni");
		} catch (BadAnnuncioIdException e) {
			assertNull(a);
		}
		try {
			a=manager.queryVisualizzaDettagliAnnuncio(idEsistente);
			assertNotNull(a);
		} catch (BadAnnuncioIdException e) {
			fail("non dovrebbe lanciare eccezioni");
		}
	}
	
	@Test
	public void testQueryCercaAltriAnnunciConQuestaImmagine() {
		boolean status;
		status=manager.queryCercaAltriAnnunciConQuestaImmagine(fotoEsistente);
		assertTrue(status);
		status=manager.queryCercaAltriAnnunciConQuestaImmagine(fotoNonEsistente);
		assertFalse(status);
	}

}
