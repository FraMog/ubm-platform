package it.ubmplatform.registrazione;

import static org.junit.Assert.*;
import it.ubmplatform.registrazione.*;
import it.ubmplatform.account.Account;

import org.junit.Test;

public class RegistrazioneManagerTest {

	@Test
	public void testQueryRegistraAccount() {
		//fail("Not yet implemented");
		Account acc=new Account("gaetano@pisco.it","bellissimo");
		RegistrazioneManager rg= new RegistrazioneManager();
		
		assertTrue(rg.queryRegistraAccount(acc));
	}

	@Test
	public void testQueryAccountEsistente() {
		Account acc=new Account("m.memoli39@studenti.unisa.it","scientifico96");
		RegistrazioneManager rg= new RegistrazioneManager();
		
		assertTrue(rg.queryAccountEsistente(acc.getEmail()));
	}

}
