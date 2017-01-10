package it.ubmplatform.annunci;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.InvalidAttributeValueException;

import it.ubmplatform.eccezioni.BadAnnuncioIdException;
import it.ubmplatform.eccezioni.BadInputAnnuncioException;
import it.ubmplatform.eccezioni.BadResearchException;

public interface AnnuncioInterface {
	
	boolean queryCancellaAnnuncio(int idAnnuncio) throws SQLException;
	boolean queryInserisciAnnuncio(Annuncio toInsert) throws InvalidAttributeValueException, SQLException;
	boolean queryModificaAnnuncio(Annuncio toUpdate) throws InvalidAttributeValueException, SQLException;;
	ArrayList<Annuncio> queryRicercaAnnuncio(Annuncio daCercare, String orderBy) throws BadResearchException;
	Annuncio queryVisualizzaDettagliAnnuncio(int idAnnuncio) throws BadAnnuncioIdException;
	Annuncio queryOttieniAnnuncioDaModificare(int idAnnuncio) throws BadInputAnnuncioException;
	ArrayList<Annuncio> queryAnnunciRecenti ();

}
