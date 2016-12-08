package it.ubmplatform.amministrazione;

import java.util.ArrayList;
import it.ubmplatform.profilo.Profilo;

/**
 * Il model che contiene le query inerenti alla sezione Amministrazione
 *
 */
public class AmministrazioneManager {

	/**
	 * Si occupa dell'interrogazione al database per la cancellazione di un account dal sistema
	 * @param email L'email dell'account da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	boolean queryCancellaAccount(String email){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per l'invalidazione dell'account. 
	 * In particolare, setta il tipo dell'account ad 'I'
	 * @param email L'email dell'account da invalidare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	boolean queryInvalidaAccount(String email){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la rimozione di un account non inerente. 
	 * @param idAnnuncio L'id dell'annuncio da rimuovere
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	boolean queryRimuoviAnnuncioNonInerente(int idAnnuncio){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per l'ottenimento di tutti i profili iscritti al sistema
	 * @return La lista dei profili degli utenti registrati
	 */
	
	ArrayList<Profilo> queryVisualizzaListaUtenti(){
		return null;
	}
}
