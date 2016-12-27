package it.ubmplatform.autenticazione;

import it.ubmplatform.account.Account;
public class AutenticazioneManager implements AutenticazioneInterface {


	/**
	 * Si occupa dell'interrogazione al database per la ricerca dell'account
	 * @param toSearch L'account da ricercare
	 * @return 1 se l'account è stato trovato, 0 se non è stato trovato, un numero negativo in caso di errore
	 */
	
	public int queryLogin(Account toSearch){
		return -1;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la ricerca della password dell'account associato all'email
	 * @param email L'email dell'account a cui ricercare la password
	 * @return La password dell'account in caso di successo, null altrimenti (account non trovato)
	 */
	
	public String queryRecuperaPassword(String email){
		return null;
	}
}
