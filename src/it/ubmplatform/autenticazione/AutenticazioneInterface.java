package it.ubmplatform.autenticazione;

import java.util.GregorianCalendar;

import it.ubmplatform.account.Account;

public interface AutenticazioneInterface {
	
	int queryLogin(Account toSearch);
	String queryRecuperaPassword(String email);
	boolean queryControllaData(GregorianCalendar dataAttuale, Account trovato);
	String queryEstraiNome(Account trovato);

}
