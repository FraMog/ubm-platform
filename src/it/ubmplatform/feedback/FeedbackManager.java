package it.ubmplatform.feedback;

import java.util.ArrayList;

/**
 * Il model che contiene le query inerenti alla sezione Feedback
 */

public class FeedbackManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un feedback
	 * @param toInsert Il feedback da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	boolean queryInserisciFeedback(Feedback toInsert){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la modifica di un feedback
	 * @param changed Il nuovo feedback modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	boolean queryModificaFeedback(Feedback changed){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione di tutti i feedback di un utente
	 * @param emailR L'email associata all'account di cui visualizzarne i feedback
	 * @return La lista dei feedback dell'account associato all'email, null se non presenti
	 */
	
	ArrayList<Feedback> queryVisualizzaFeedbacks(String emailR){
		return null;
	}
}
