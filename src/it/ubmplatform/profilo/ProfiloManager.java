package it.ubmplatform.profilo;

import java.util.ArrayList;

/**
 * Il model che contiene le query inerenti alla sezione Profilo
 */

public class ProfiloManager {
	
	/**
	 * Si occupa dell'interrogazione al database per l'aggiunta di un profilo
	 * @param toInsert Il profilo da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	boolean queryCreaProfilo(Profilo toInsert){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la modifica di un profilo
	 * @param changed Il nuovo profilo modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	boolean queryModificaProfilo(Profilo changed){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	boolean queryDisattivaProfilo(String email){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la ricerca di un profilo
	 * @param nome Il nome dell'utente associato al profilo da ricercare
	 * @param cognome Il cognome dell'utente associato al profilo da ricercare
	 * @param email L'email dell'account associato al profilo da ricercare
	 * @return I profili trovati con i filtri inseriti, null se non trovati
	 */
	ArrayList<Profilo> queryRicercaProfilo(String nome, String cognome, String email){
		return null;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione di un profilo
	 * @param email L'email dell'account associato al profilo da ricercare
	 * @return Il profilo se l'operazione è andata a buon fine, null in caso di errore
	 */
	Profilo queryVisualizzaProfilo(String email){
		return null;
	}
}
