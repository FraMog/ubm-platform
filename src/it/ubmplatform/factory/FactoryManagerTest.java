package it.ubmplatform.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import it.ubmplatform.amministrazione.AmministrazioneManager;
import it.ubmplatform.annunci.AnnuncioManager;
import it.ubmplatform.autenticazione.AutenticazioneManager;
import it.ubmplatform.feedback.FeedbackManager;
import it.ubmplatform.profilo.ProfiloManager;
import it.ubmplatform.registrazione.RegistrazioneManager;

public class FactoryManagerTest {

	@Test
	public void testCreateProfiloManager() {
		ProfiloManager pr = new ProfiloManager();
		assertTrue(pr != null);
	}
	@Test
	public void testCreateAutenticazioneManager() {
		AutenticazioneManager au = new AutenticazioneManager();
		assertTrue(au != null);
	}
	@Test
	public void testCreateAnnuncioManager() {
		AnnuncioManager an = new AnnuncioManager();
		assertTrue(an != null);
	}
	@Test
	public void testCreateAmministrazioneManager() {
		AmministrazioneManager am = new AmministrazioneManager();
		assertTrue(am != null);
	}
	@Test
	public void testCreateFeedbackManager() {
		FeedbackManager fb = new FeedbackManager();
		assertTrue(fb != null);
	}
	@Test
	public void testCreateRegistrazioneManager() {
		RegistrazioneManager rg = new RegistrazioneManager();
		assertTrue(rg != null);
	}
	
}
