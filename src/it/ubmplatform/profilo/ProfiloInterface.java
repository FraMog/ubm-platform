package it.ubmplatform.profilo;

import java.util.ArrayList;

public interface ProfiloInterface {
	
	boolean queryCreaProfilo(Profilo toInsert);
	boolean queryModificaProfilo(Profilo changed);
	boolean queryDisattivaProfilo(String email);
	ArrayList<Profilo> queryRicercaProfilo(String nome, String cognome, String email);
	Profilo queryVisualizzaProfilo(String email);

}
