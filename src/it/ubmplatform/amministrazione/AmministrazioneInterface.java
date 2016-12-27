package it.ubmplatform.amministrazione;

import java.util.ArrayList;
import it.ubmplatform.profilo.Profilo;

public interface AmministrazioneInterface {

	boolean queryCancellaAccount(String email);
	boolean queryInvalidaAccount(String email);
	boolean queryRimuoviAnnuncioNonInerente(int idAnnuncio);
	ArrayList<Profilo> queryVisualizzaListaUtenti();
	
}
