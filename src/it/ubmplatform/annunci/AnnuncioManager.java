package it.ubmplatform.annunci;

import java.util.ArrayList;

/**
 * Il model che contiene le query inerenti alla sezione Annunci
 */

public class AnnuncioManager implements AnnuncioInterface {
	
	/**
	 * Si occupa dell'interrogazione al database per la cancellazione dell'annuncio
	 * @param idAnnuncio L'id dell'annuncio da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryCancellaAnnuncio(int idAnnuncio){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un annuncio
	 * @param toInsert L'annuncio da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryInserisciAnnuncio(Annuncio toInsert){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la modifica dell'annuncio
	 * @param changed L'annuncio modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryModificaAnnuncio(Annuncio changed){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la ricerca dell'annuncio
	 * @param nomeAnnuncio Il nome dell'annuncio da ricercare
	 * @param facolta La facoltà in cui ricercare gli annunci
	 * @param orderBy L'ordine con cui ritornare l'ArrayList 
	 * @return La lista degli annunci con i filtri inseriti, null se non presenti
	 */
	
	public ArrayList<Annuncio> queryRicercaAnnuncio(String nomeAnnuncio, String facolta, String orderBy){
		return null;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione dei dettagli di un annuncio
	 * @param idAnnuncio L'id dell'annuncio di cui visualizzarne i dettagli
	 * @return L'annuncio relativo all'id passato, null in caso di errore
	 */
	public Annuncio queryVisualizzaDettagliAnnuncio(int idAnnuncio){
		return null;
	}
}
