package it.ubmplatform.registrazione;

import it.ubmplatform.account.Account;

/**
 * Il model che gestisce la query per la registrazione di un account
 */

public class RegistrazioneManager implements RegistrazioneInterface {

	/**
	 * Si occupa dell'interrogazione al database per la registrazione di un account
	 * @param toInsert L'account da registrare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	public boolean queryRegistraAccount(Account toInsert){
		return false;
	}
}
