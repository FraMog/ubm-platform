package it.ubmplatform.registrazione;

import static org.junit.Assert.*;
import it.ubmplatform.registrazione.*;
import it.ubmplatform.account.Account;

import org.junit.Test;

public class RegistrazioneManagerTest {

	@Test
	public void testQueryRegistraAccount() {
		//fail("Not yet implemented");
		Account acc=new Account("webelloooooo@bellissimo.it","munnezza");
		RegistrazioneManager rg= new RegistrazioneManager();
		
		assertTrue(rg.queryRegistraAccount(acc));
	}

	@Test
	public void testQueryAccountEsistente() {
		Account acc=new Account("webelloooooo@bellissimo.it","munnezza");
		RegistrazioneManager rg= new RegistrazioneManager();
		
		assertTrue(rg.queryAccountEsistente(acc.getEmail()));
	}

}
