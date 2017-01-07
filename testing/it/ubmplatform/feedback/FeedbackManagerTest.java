package it.ubmplatform.feedback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.ubmplatform.eccezioni.BadInputFeedbackException;

public class FeedbackManagerTest {
	FeedbackManager manager;
	private Feedback existsFeedbackOk, feedbackNoOk, notExistsFeedbackOk;
	
	@Before
	public void setUp(){
		//istanzio il feedback manager
		manager = new FeedbackManager();
		
		//instanzio i feedback per l'inserimento (non esistente)
		//modifica (esistente)
		//non corretto (per entrambe)
		try {
			notExistsFeedbackOk = new Feedback(1, "Ottimo acquisto!", "francesco@unisa.it", "prova@unisa.it");
			existsFeedbackOk = new Feedback(1, "Ottimo acquisto!", "francesco@unisa.it", "marco@unisa.it");
			feedbackNoOk = new Feedback(1, "Ottimo acquisto!", "NonEsisto@unisa.it", "NonEsisto@unisa.it");
		} catch (BadInputFeedbackException e) {
			fail("Non dovrebbe lanciare l'eccezione!");
		}
		
	}

	@After
	public void tearDown(){
		notExistsFeedbackOk = null;
		existsFeedbackOk = null;
		feedbackNoOk = null;
		manager = null;
	}

	@Test
	public void testQueryInserisciFeedback() {
		boolean status;
		
		//provo il feedback non funzionante .. deve essere false il ritorno
		status = manager.queryInserisciFeedback(feedbackNoOk);
		assertFalse(status);
		
		//provo il feedback funzionante.. deve essere true il ritorno
		status = manager.queryInserisciFeedback(notExistsFeedbackOk);
		assertTrue(status);
		
	}

	@Test
	public void testQueryOttieniFeedbackDaModificare() {
		Feedback feedback;
		
		//provo ad ottenere il feedback inserito.. non dovrebbe essere null
		String emailP = existsFeedbackOk.getEmailP();
		String emailR = existsFeedbackOk.getEmailR();
		feedback = manager.queryOttieniFeedbackDaModificare(emailP, emailR);
		
		assertNotNull(feedback);
		
		//FEEDBACK NON CORRETTO --- DOVREBBE RITORNARE NULL
		emailP = feedbackNoOk.getEmailP();
		emailR = feedbackNoOk.getEmailR();
		feedback = manager.queryOttieniFeedbackDaModificare(emailP, emailR);
		
		assertNull(feedback);
	}

	@Test
	public void testQueryModificaFeedback() {
		//provo a modificare il feedback buono.. dovrebbe essere ok
		boolean status;
		
		status = manager.queryModificaFeedback(existsFeedbackOk);
		assertTrue(status);
		
		status = manager.queryModificaFeedback(feedbackNoOk);
		assertFalse(status);
		
	}

	@Test
	public void testQueryVisualizzaFeedbacks() {
		ArrayList<Feedback> feeds = new ArrayList<Feedback>();
		
		//ottengo i feedback dell'email del feedback presente (modifica)
		feeds = manager.queryVisualizzaFeedbacks(existsFeedbackOk.getEmailR());
		assertNotNull(feeds);
		
		//ottengo i feedback dell'email non buona.. dovrebbe ritornare un arraylist vuoto
		feeds = manager.queryVisualizzaFeedbacks(feedbackNoOk.getEmailR());
		assertEquals(0, feeds.size());
		
		
	}

}
