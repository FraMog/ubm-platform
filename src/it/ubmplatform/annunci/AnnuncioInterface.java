package it.ubmplatform.annunci;

import java.util.ArrayList;

public interface AnnuncioInterface {
	
	boolean queryCancellaAnnuncio(int idAnnuncio);
	boolean queryInserisciAnnuncio(Annuncio toInsert);
	boolean queryModificaAnnuncio(Annuncio changed);
	ArrayList<Annuncio> queryRicercaAnnuncio(String nomeAnnuncio, String facolta, String orderBy);
	Annuncio queryVisualizzaDettagliAnnuncio(int idAnnuncio);

}
