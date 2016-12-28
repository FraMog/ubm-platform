package it.ubmplatform.annunci;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Logger;


import it.ubmplatform.database.DBManager;
import it.ubmplatform.eccezioni.BadResearchException;

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
	 * @throws BadResearchException Se vi è un errore nel parametri della ricerca.
	 */

	public ArrayList<Annuncio> queryRicercaAnnuncio(String titolo, String facolta, String categoria, String orderBy) throws BadResearchException{
		ArrayList<Annuncio> annunciPertinenti = new ArrayList<Annuncio>();
		Logger logger= Logger.getLogger("Logger");
		Connection connection=null;
		try {
			connection = DBManager.getInstance().getConnection();
			String query= "SELECT ID, Titolo, Foto, DataPubblicazione, Prezzo, Descrizione FROM annuncio ";

			//Se cioè sto cercando usando il form di ricerca nel navbar
			if (titolo!=null && categoria!=null && orderBy!=null){
				query+= "WHERE Titolo = '" + titolo + "' AND Facolta = '" + facolta + "' ";

				//Verifico quale categoria di prodotto è stata scelta
				switch (categoria){
				case "libro": query+="AND Categoria='L' "; break;
				case "appunti": query+="AND Categoria='A' "; break;
				case "tutto": break;
				default: throw new BadResearchException("La categoria specificata non è corretta");
				}

				//Se sto cercando nel navbar e il criterio di ordinamento è la data
				if (orderBy.equals("DataPubblicazione")){
					query+="ORDER BY " + orderBy + " DESC";

				}
				//Se sto cercando nel navbar e il criterio di ordinamento è il prezzo
				else if (orderBy.equals("Prezzo")){
					query+="ORDER BY " + orderBy + " ASC";
				}
				else {//Non dovrebbe mai accadare che il criterio non sia ne data ne prezzo
					throw new BadResearchException("Il criterio di ordinamento non corrisponde ne a data pubblicazione ne a prezzo!");
				}




			}

			//Se sto facendo ricerca Annunci per facoltà
			else if (titolo==null && categoria==null && orderBy==null){
				query+= "WHERE Facolta = '" + facolta + "' ";
				query+="ORDER BY " + "DataPubblicazione DESC";

			}

			//Non dovrebbe mai accadere che non sto ricercando ne nel navbar ne per facoltà!
			else{
				throw new BadResearchException("I parametri passati in input: titolo, categoria, facoltà e ordinamento non sono accettabili");
			}


			logger.info(query);
			Statement statement = connection.createStatement();
			ResultSet resultSet= statement.executeQuery(query);

			while(resultSet.next()){
				Annuncio annuncio = new Annuncio();
				annuncio.setId(resultSet.getInt(1));
				annuncio.setTitoloAnnuncio(resultSet.getString(2));
				annuncio.setFoto(resultSet.getString(3));

				GregorianCalendar dataPubblicazione= new GregorianCalendar();
				dataPubblicazione.setTime(resultSet.getDate(4));
				annuncio.setDataPubblicazione(dataPubblicazione);

				annuncio.setPrezzo(resultSet.getDouble(5));
				annuncio.setDescrizioneProdotto(resultSet.getString(6));
				annunciPertinenti.add(annuncio);
			}

			logger.info("size degli array degli annunci pertinenti = " + annunciPertinenti.size());
			return annunciPertinenti;


		} catch (SQLException e) {
			return null;
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


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
