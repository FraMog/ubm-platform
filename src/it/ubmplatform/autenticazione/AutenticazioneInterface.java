package it.ubmplatform.autenticazione;

import it.ubmplatform.account.Account;

public interface AutenticazioneInterface {
	
	int queryLogin(Account toSearch);
	String queryRecuperaPassword(String email);

}
