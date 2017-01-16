package it.ubmplatform.profilo;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.eccezioni.BadCreaProfiloException;
import it.ubmplatform.eccezioni.BadInputAnnuncioException;

public class ProfiloTest {
	private Profilo p;
	@Before
	public void setUp(){
		//testo i costruttori
		try{
			//dovrebbe lanciare l'eccezione per email non valida
			p  = new Profilo("g.ciampi@gmail.com", "giovanni", "ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date(1995, 10, 10));
			fail("Dovrebbe lanciare l'eccezione per email errata");
		}catch(BadCreaProfiloException e){
			assertEquals(null, p);				
		}
		try{
			//dovrebbe lanciare l'eccezione per nome non valido
			p  = new Profilo("g.ciampi@studenti.unisa.it", "giovanni11", "ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date());
			fail("Dovrebbe lanciare l'eccezione per nome non valido");
		}catch(BadCreaProfiloException e){
			assertEquals(null, p);				
		}
		try{
			//dovrebbe lanciare l'eccezione per cognome non valido
			p  = new Profilo("g.ciampi@studenti.unisa.it", "giovanni", "", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date());
			fail("Dovrebbe lanciare l'eccezione per cognome non valido");
		}catch(BadCreaProfiloException e){
			assertEquals(null, p);				
		}
		try{
			//dovrebbe lanciare l'eccezione per numero telefono non valido
			p  = new Profilo("g.ciampi@studenti.unisa.it", "giovanni", "ciampi", "Benevento", "34566429", "Musica", "img.jpg", new java.util.Date());
			fail("Dovrebbe lanciare l'eccezione per numero di telefono non valido");
		}catch(BadCreaProfiloException e){
			assertEquals(null, p);				
		}
		try{
			//non dovrebbe lanciare eccezioni
			p  = new Profilo("g.ciampi@studenti.unisa.it", "giovanni", "ciampi", "Benevento", "3484566429", "Musica", "img.jpg", new java.util.Date(1995, 12, 16));
			
		}catch(BadCreaProfiloException e){
			fail("Non dovrebbe lanciare eccezioni");
		}
	}
	

	@Test
	public void testGetEmail() {
		String email = p.getEmail();
		assertEquals("g.ciampi@studenti.unisa.it", email);
	}
	
	@Test
	public void testGetFoto() {
		String foto = p.getFoto();
		assertEquals("img.jpg", foto);
	}

	

	@Test
	public void testGetNome() {
		String nome = p.getNome();
		assertEquals("giovanni", nome);
	}

	@Test
	public void testGetCognome() {
		String cognome = p.getCognome();
		assertEquals("ciampi", cognome);
	}

	@Test
	public void testGetResidenza() {
		String residenza = p.getResidenza();
		assertEquals("Benevento", residenza);
	}

	@Test
	public void testGetTelefono() {
		String telefono = p.getTelefono();
		assertEquals("3484566429", telefono);
	}

	@Test
	public void testGetInteressi() {
		String interessi = p.getInteressi();
		assertEquals("Musica", interessi);
	}

	@Test
	public void testSetFoto() {
		String foto = "img1.jpg";
		p.setFoto(foto);
		assertEquals(foto, p.getFoto());
	}

	@Test
	public void testSetResidenza() {
		String residenza = "Salerno";
		p.setResidenza(residenza);
		assertEquals("Salerno", p.getResidenza());
	}

	@Test
	public void testSetTelefono() {
		String telefono = "3412233456";
		p.setTelefono(telefono);
		assertEquals(telefono, p.getTelefono());
	}

	@Test
	public void testSetInteressi() {
		String interessi = "Chitarra";
		p.setInteressi(interessi);
		assertEquals(interessi, p.getInteressi());
	}

	@Test
	public void testGetDataNascita() {
		java.util.Date dataNascita = new java.util.Date(1995, 12, 16);
		assertEquals(dataNascita, p.getDataNascita());
	}

	@Test
	public void testSetDataNascita() {
		Date dataNascita = new Date(1997, 10, 16);
		p.setDataNascita(dataNascita);
		assertEquals(dataNascita, p.getDataNascita());
	}

	@After
	public void tearDown() throws Exception {
		p = null;
	}
}
