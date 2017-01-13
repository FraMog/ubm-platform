package it.ubmplatform.profilo;

import java.sql.SQLException;
import java.util.ArrayList;

import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.eccezioni.BadModificaException;
import it.ubmplatform.eccezioni.BadOldPasswordException;
import it.ubmplatform.eccezioni.BadVisualizzaProfiloException;

public interface ProfiloInterface {
	
	boolean queryCreaProfilo(Profilo toInsert) throws SQLException;
	boolean queryModificaProfilo(Profilo changed) throws SQLException, BadModificaException;
	boolean queryDisattivaProfilo(String email) throws SQLException;
	ArrayList<Profilo> queryRicercaProfilo(String nome, String cognome, String email);
	Profilo queryVisualizzaProfilo(String email) throws BadVisualizzaProfiloException;
	boolean queryModificaProfiloPassword(Profilo changed, String oldPassword, String newPassword) throws BadOldPasswordException, SQLException, BadModificaException;
	ArrayList<Annuncio> queryGetElencoAnnunci(String email);

}
