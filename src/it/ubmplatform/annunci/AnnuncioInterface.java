package it.ubmplatform.annunci;

import java.util.ArrayList;

import it.ubmplatform.eccezioni.BadResearchException;

public interface AnnuncioInterface {
	
	boolean queryCancellaAnnuncio(int idAnnuncio);
	boolean queryInserisciAnnuncio(Annuncio toInsert);
	boolean queryModificaAnnuncio(Annuncio changed);
	ArrayList<Annuncio> queryRicercaAnnuncio(Annuncio daCercare, String orderBy) throws BadResearchException;
	Annuncio queryVisualizzaDettagliAnnuncio(int idAnnuncio);

}
