package it.ubmplatform.amministrazione;

import java.sql.SQLException;
import java.util.ArrayList;
import it.ubmplatform.account.Account;

public interface AmministrazioneInterface {

	boolean queryCancellaAccount(String email);
	boolean queryInvalidaAccount(String email);
	boolean queryRimuoviAnnuncioNonInerente(int idAnnuncio) throws SQLException;
	public ArrayList<Account> queryVisualizzaListaUtenti() throws Exception;
	boolean queryCancellaFeedback(String email);
}
