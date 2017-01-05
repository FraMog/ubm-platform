package it.ubmplatform.annunci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import javax.management.InvalidAttributeValueException;

import it.ubmplatform.database.DBManager;
import it.ubmplatform.eccezioni.BadAnnuncioIdException;
import it.ubmplatform.eccezioni.BadInputAnnuncioException;
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

	public boolean queryCancellaAnnuncio(int idAnnuncio)throws SQLException {
		Connection conn=null;
		PreparedStatement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			s=conn.prepareStatement("DELETE FROM annuncio WHERE ID=?");
			s.setInt(1, idAnnuncio);
			s.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally{
			if(s!=null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un annuncio
	 * @param toInsert L'annuncio da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */

	public boolean queryInserisciAnnuncio(Annuncio toInsert) throws InvalidAttributeValueException, SQLException{
		Connection conn=null;
		PreparedStatement s=null;
		try {
			conn=DBManager.getInstance().getConnection(); //recupero una connessione
			//creo la query
			String query="INSERT INTO annuncio (ID, Titolo, Categoria, Facolta, Foto, ISBN, Autore, Edizione, Materia, Condizione, Descrizione, Prezzo, Email, DataPubblicazione) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,CURDATE())";
			s=conn.prepareStatement(query);
			System.out.println(toInsert.getId());
			s.setInt(1, toInsert.getId());
			s.setString(2, toInsert.getTitolo());
			s.setString(3, toInsert.getCategoria());
			s.setString(4, toInsert.getFacolta());
			s.setString(5, toInsert.getFoto());
			System.out.println(toInsert.getCategoria());
			if (toInsert.getCategoria().equals("L")) { //controllo se è un libro
				s.setString(6, toInsert.getIsbn());
				s.setString(7, toInsert.getAutoreLibro());
				s.setInt(8, toInsert.getEdizione());
				s.setString(9, toInsert.getMateria());
				s.setString(10, toInsert.getCondizioni());
				s.setString(11, toInsert.getDescrizione());
				s.setDouble(12, toInsert.getPrezzo());
				s.setString(13, toInsert.getEmail());	
			}
			else if (toInsert.getCategoria().equals("A")) { //controllo se sono appunti
				s.setString(6, null);
				s.setString(7, null);
				s.setInt(8, 0);
				s.setString(9, toInsert.getMateria());
				s.setString(10, toInsert.getCondizioni());
				s.setString(11, toInsert.getDescrizione());
				s.setDouble(12, toInsert.getPrezzo());
				s.setString(13, toInsert.getEmail());
			}
			s.execute(); //eseguo la query e resituisco true se non lancia eccezioni
			return true;
		} finally{
			if(s!=null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
	
	/** 
	 * Metodo che permette di ricavare l'annuncio esistente da modificare
	 * @param idAnnuncio Id dell'annuncio già esistente da modificare
	 * @return oldAnnuncio Annuncio da modificare 
	 */
	public Annuncio queryOttieniAnnuncioDaModificare(int idAnnuncio) throws BadInputAnnuncioException{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getInstance().getConnection();
			
			String query = "SELECT ID, Titolo, Categoria, Facolta, Foto, ISBN, Autore, Edizione, Materia, Condizione, Descrizione, Prezzo, Email, DataPubblicazione FROM Annuncio Where ID = ?";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, idAnnuncio);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				int id = rs.getInt(1);
				String titolo = rs.getString(2);
				String categoria = rs.getString(3);
				String facolta = rs.getString(4);
				String foto = rs.getString(5);
				String isbn = rs.getString(6);
				String autoreLibro = rs.getString(7);
				int edizione = rs.getInt(8);
				String materia = rs.getString(9);
				String condizioni = rs.getString(10);
				String descrizione = rs.getString(11);
				double prezzo = rs.getDouble(12);
				String email = rs.getString(13);
				java.sql.Date dataPubblicazione = rs.getDate(14);
				
				Annuncio oldAnnuncio = new Annuncio(id, titolo, categoria, facolta, foto, isbn, autoreLibro, edizione, materia, condizioni, descrizione, prezzo, email, dataPubblicazione);
				
				conn.close();
				ps.close();
				rs.close();
				
				return oldAnnuncio;
			}else{
				conn.close();
				ps.close();
				rs.close();
				
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per la modifica dell'annuncio
	 * @param changed L'annuncio modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @throws SQLException 
	 * @throws InvalidAttributeValueException 
	 */

	public boolean queryModificaAnnuncio(Annuncio toUpdate) throws SQLException, InvalidAttributeValueException{
		//connessione e statement a null per la chiusura in caso di eccezione
				//ed eventuale controllo
				Connection conn = null;
				PreparedStatement ps = null;
						
				try{
					//prendo la connessione dalla classe statica DBManager
					conn = DBManager.getInstance().getConnection();
							
					//formo la stringa contenente la query da effettuare
					String queryInserisci = "UPDATE ANNUNCIO "
							+ "SET Titolo = ?, Categoria = ?, Facolta = ?, Foto = ?, ISBN = ?, Autore = ?, Edizione = ?, Materia = ?, Condizione = ?, Descrizione = ?, Prezzo = ?, annuncio.DataPubblicazione = CURDATE()"
							+ "WHERE ID = ?";
							
					//preparo lo statement per formare la query
					ps = conn.prepareStatement(queryInserisci);
							
					ps.setString(1, toUpdate.getTitolo());
					ps.setString(2, toUpdate.getCategoria());
					ps.setString(3, toUpdate.getFacolta());
					ps.setString(4, toUpdate.getFoto());
					ps.setString(5, toUpdate.getIsbn());
					ps.setString(6, toUpdate.getAutoreLibro());
					ps.setInt(7, toUpdate.getEdizione());
					ps.setString(8, toUpdate.getMateria());
					ps.setString(9, toUpdate.getCondizioni());
					ps.setString(10, toUpdate.getDescrizione());
					ps.setDouble(11, toUpdate.getPrezzo());
					ps.setInt(12, toUpdate.getId());
							
					ps.execute();
						

					//ritorno true se il metodo execute è andato a buon fine
					//dopo aver provato la chiusura
					conn.close();
					ps.close();
					return true;
							
						
				}catch(SQLException e){
					e.printStackTrace();
					conn.close();
					ps.close();
							
					return false;
				}
	}

	/**
	 * Si occupa dell'interrogazione al database per la ricerca dell'annuncio
	 * @param nomeAnnuncio Il nome dell'annuncio da ricercare
	 * @param facolta La facoltà in cui ricercare gli annunci
	 * @param orderBy L'ordine con cui ritornare l'ArrayList 
	 * @return La lista degli annunci con i filtri inseriti, null se non presenti
	 * @throws BadResearchException Se vi è un errore nel parametri della ricerca.
	 */

	public ArrayList<Annuncio> queryRicercaAnnuncio(Annuncio daCercare, String orderBy) throws BadResearchException{
		String titolo= daCercare.getTitolo();
		String facolta=daCercare.getFacolta();
		String categoria= daCercare.getCategoria();
		
		ArrayList<Annuncio> annunciPertinenti = new ArrayList<Annuncio>();
		Logger logger= Logger.getLogger("Logger");
		Connection connection=null;
		try {
			connection = DBManager.getInstance().getConnection();
			String query= "SELECT ID, Titolo, Foto, DataPubblicazione, Prezzo, Descrizione, Email FROM annuncio ";

			//Se cioè sto cercando usando il form di ricerca nel navbar
			if (titolo!=null && categoria!=null && orderBy!=null){
				query+= "WHERE Titolo LIKE '%" + titolo + "%' AND Facolta = '" + facolta + "' ";

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
				Annuncio annuncioPertinente = new Annuncio();
				annuncioPertinente.setId(resultSet.getInt(1));
				annuncioPertinente.setTitolo(resultSet.getString(2));
				annuncioPertinente.setFoto(resultSet.getString(3));
				annuncioPertinente.setDataPubblicazione(resultSet.getDate(4));
				annuncioPertinente.setPrezzo(resultSet.getDouble(5));
				annuncioPertinente.setDescrizione(resultSet.getString(6));
				annuncioPertinente.setEmail(resultSet.getString(7));
				annunciPertinenti.add(annuncioPertinente);
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
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione dei dettagli di un annuncio
	 * @param idAnnuncio L'id dell'annuncio di cui visualizzarne i dettagli
	 * @return L'annuncio relativo all'id passato, null in caso di errore
	 * @throws BadAnnuncioIdException Se all'id passato in input non corrisponde nessun annuncio nel database
	 */
	public Annuncio queryVisualizzaDettagliAnnuncio(int idAnnuncio) throws BadAnnuncioIdException{
		//annuncio 
		
		Logger logger= Logger.getLogger("Logger");
		Connection connection=null;
		try {
			connection = DBManager.getInstance().getConnection();
			String query= "SELECT ID, Titolo, Categoria, Facolta, Foto, ISBN, Autore, Edizione, Materia, Condizione, Descrizione, Prezzo, Email, DataPubblicazione FROM annuncio WHERE ID="+idAnnuncio;
			logger.info(query);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (!resultSet.next() ) {
			    throw new BadAnnuncioIdException("Non è stato trovato un annuncio con id = " + idAnnuncio + "!");
			} else {
			   Annuncio annuncioDettagliato= new Annuncio();
			   annuncioDettagliato.setId(resultSet.getInt(1));
			   annuncioDettagliato.setTitolo(resultSet.getString(2));
			 
			   String categoriaDatabase= resultSet.getString(3);
			   if(categoriaDatabase.equalsIgnoreCase("L")){//È un libro
				   annuncioDettagliato.setCategoria("libro");
			   }
			   else if (categoriaDatabase.equalsIgnoreCase("A")){//Sono degli appunti
				   annuncioDettagliato.setCategoria("appunti");
			   }
			   else {
				   annuncioDettagliato.setCategoria(categoriaDatabase); //Non dovrebbe mai accadere ma non lancio eccezioni perché i controlli sulla categoria sono fatti al momento dell'inserimento
			   }
			   annuncioDettagliato.setFacolta(resultSet.getString(4));
			   annuncioDettagliato.setFoto(resultSet.getString(5));
			   annuncioDettagliato.setIsbn(resultSet.getString(6));
			   annuncioDettagliato.setAutoreLibro(resultSet.getString(7));
			   annuncioDettagliato.setEdizione(resultSet.getInt(8));
			   annuncioDettagliato.setMateria(resultSet.getString(9));
			   annuncioDettagliato.setCondizioni(resultSet.getString(10));
			   annuncioDettagliato.setDescrizione(resultSet.getString(11));
			   annuncioDettagliato.setPrezzo(resultSet.getDouble(12));
			   annuncioDettagliato.setEmail(resultSet.getString(13));
			   annuncioDettagliato.setDataPubblicazione(resultSet.getDate(14));
			   return annuncioDettagliato;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
}
