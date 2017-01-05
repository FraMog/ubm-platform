package it.ubmplatform.feedback;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.eccezioni.BadInputFeedbackException;

public class FeedbackTest {
	private Feedback feedback;
	
	@Before
	public void setUp(){
		//testo i costruttori
		try{
			//dovrebbe lanciare l'eccezione per valutazione negativa
			feedback = new Feedback(-10, "Ottimo acquisto!", "marco@studenti.unisa.it", "giorgio@studenti.unisa.it");
			fail("Dovrebbe lanciare l'eccezione per valutazione negativa!");
		}catch(BadInputFeedbackException e){
			assertEquals(null, feedback);				
		}
		
		try{
			//dovrebbe lanciare l'eccezione per email non corretta
			feedback = new Feedback(-10, "Ottimo acquisto!", "marco@yahoo.it", "giorgio@studenti.unisa.it");
			fail("Dovrebbe lanciare l'eccezione per email non corretta!");
		}catch(BadInputFeedbackException e){
			assertEquals(null, feedback);				
		}
		
		try{
			//dovrebbe andare a buon fine
			feedback = new Feedback(1, "Ottimo acquisto!", "marco@studenti.unisa.it", "giorgio@studenti.unisa.it");
		}catch(BadInputFeedbackException e){
			fail("Non dovrebbe lanciare l'eccezione!");
		}
	}

	@After
	public void tearDown() throws Exception {
		feedback = null;
	}


	@Test
	public void testGetValutazione() {
		int valutazione = feedback.getValutazione();
		assertEquals(1, valutazione);
	}

	@Test
	public void testGetDescrizione() {
		String descrizione = feedback.getDescrizione();
		assertEquals("Ottimo acquisto!", descrizione);
	}

	@Test
	public void testGetEmailP() {
		String emailP = feedback.getEmailP();
		assertEquals("marco@studenti.unisa.it", emailP);
	}

	@Test
	public void testGetEmailR() {
		String emailR = feedback.getEmailR();
		assertEquals("giorgio@studenti.unisa.it", emailR);
	}


	@Test
	public void testSetData() {
		java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
		feedback.setData(data);
		assertEquals(data, feedback.getData());
	}

	@Test
	public void testSetValutazione() {
		//Classe Valutazione negativa
		int valutazione = -1;
		try{
			feedback.setValutazione(valutazione);
			fail("Dovrebbe lanciare l'eccezione per valutazione negativa!");
		}catch(BadInputFeedbackException e){
			assertTrue(true);
			
		}
		
		//Classe Valutazione ok
		valutazione = 4;
		try{
			feedback.setValutazione(valutazione);
			assertEquals(4, feedback.getValutazione());
		}catch(BadInputFeedbackException e){
			fail("Non dovrebbe lanciare l'eccezione!");
		}
		
		//Classe Valutazione maggiore di 5
		valutazione = 7;
		try{
			feedback.setValutazione(valutazione);
			fail("Dovrebbe lanciare l'eccezione per valutazione maggiore di 5!");
		}catch(BadInputFeedbackException e){
			assertTrue(true);
		}
			
	}

	@Test
	public void testSetDescrizione() {
		String descrizione = "Bello!";
		feedback.setDescrizione(descrizione);
		assertEquals("Bello!", feedback.getDescrizione());
	}

}
