package it.ubmplatform.profilo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProfiloInterface {
	
	void queryCreaProfilo(Profilo toInsert) throws SQLException;
	boolean queryModificaProfilo(Profilo changed);
	boolean queryDisattivaProfilo(String email) throws SQLException;
	ArrayList<Profilo> queryRicercaProfilo(String nome, String cognome, String email);
	Profilo queryVisualizzaProfilo(String email);

}
