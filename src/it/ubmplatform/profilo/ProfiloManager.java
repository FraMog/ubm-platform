package it.ubmplatform.profilo;

import java.sql.*;
import java.util.ArrayList;

import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.database.DBManager;
import it.ubmplatform.eccezioni.BadEmailException;
import it.ubmplatform.eccezioni.BadModificaException;
import it.ubmplatform.eccezioni.BadOldPasswordException;
import it.ubmplatform.eccezioni.BadResearchException;
import it.ubmplatform.eccezioni.BadVisualizzaProfiloException;

/**
 * Il model che contiene le query inerenti alla sezione Profilo
 */

public class ProfiloManager implements ProfiloInterface {
	
	/**
	 * Si occupa dell'interrogazione al database per l'aggiunta di un profilo
	 * @param toInsert Il profilo da inserire
	 * @throws SQLException 
	 */
	public void queryCreaProfilo(Profilo toInsert) throws SQLException{
		Connection conn=null;
		PreparedStatement s=null;
		try {
			conn=DBManager.getInstance().getConnection(); //recupero una connessione
			//creo la query
			String query="INSERT INTO profilo (Email, Nome,Cognome,Foto,Residenza,Telefono,Interessi, DataNascita) VALUES (?,?,?,?,?,?,?,?)";
			s=conn.prepareStatement(query);
			s.setString(1, toInsert.getEmail());
			s.setString(2, toInsert.getNome());
			s.setString(3, toInsert.getCognome());
			s.setString(4, toInsert.getFoto());
			s.setString(5, toInsert.getResidenza());
			s.setString(6, toInsert.getTelefono());
			s.setString(7, toInsert.getInteressi());
			java.sql.Date date=null;
			if(toInsert.getDataNascita()!=null)
				date=new java.sql.Date(toInsert.getDataNascita().getTime());
			s.setDate(8, date);
			s.execute(); //eseguo la query e resituisco true se non lancia eccezioni
		} finally{
			if(s!=null)
				try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	 * Si occupa dell'interrogazione al database per la modifica di un profilo
	 * 
	 * @param changed
	 *            Il nuovo profilo modificato da inserire
	 * @return Un booleano che indica se l'operazione � andata a buon fine
	 * @throws BadModificaException 
	 */
	public boolean queryModificaProfilo(Profilo toUpdate) throws SQLException, BadModificaException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBManager.getInstance().getConnection();

			String queryModifica = "UPDATE PROFILO "
					+ "SET Nome = ?, Cognome = ?, Foto = ?, Residenza = ?, Interessi = ?, DataNascita = ?, Telefono = ? "
					+ "WHERE Email = ? ";
			
			java.sql.Date date = null;
			if(toUpdate.getDataNascita() != null)
				date = new java.sql.Date(toUpdate.getDataNascita().getTime());
			String nome = (String) toUpdate.getNome();
			String cognome = (String) toUpdate.getCognome();
			
			if(nome == null || cognome == null)
				throw new BadModificaException();
			
			
			statement = connection.prepareStatement(queryModifica);
			statement.setString(1, toUpdate.getNome());
			statement.setString(2, toUpdate.getCognome());
			statement.setString(3, toUpdate.getFoto());
			statement.setString(4, toUpdate.getResidenza());
			statement.setString(5, toUpdate.getInteressi());
			statement.setDate(6, date);
			statement.setString(7,  toUpdate.getTelefono());
			statement.setString(8, toUpdate.getEmail());
			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * Si occupa di modificare un profilo utente, compresa la sua password
	 * @param toUpdate il profilo aggiornato
	 * @param oldPassword la vecchia password, serve per controllare se quella inserita era giusta
	 * @param newPassword la nuova password
	 * @return Un booleano che indica se l'operazione � andata a buon fine, in particolare ritorna false se la 
	 * vecchia password non corrisponde a quella presente nel database.
	 * @throws BadOldPasswordException 
	 * @throws BadModificaException 
	 */
	public boolean queryModificaProfiloPassword(Profilo toUpdate, String oldPassword, String newPassword) throws BadOldPasswordException, SQLException, BadModificaException {

		
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statementGetPass = null;
		PreparedStatement statementSetPass = null;
		try {
			
			java.sql.Date date = null;
			if(toUpdate.getDataNascita() != null)
				date = new java.sql.Date(toUpdate.getDataNascita().getTime());
			String nome = (String) toUpdate.getNome();
			String cognome = (String) toUpdate.getCognome();
			
			if(date == null || nome == null || cognome == null)
				throw new BadModificaException();

			
			connection = DBManager.getInstance().getConnection();
			
			String queryRicerca = "SELECT password FROM account WHERE email = '" + toUpdate.getEmail() + "'";
			

			String queryModifica = "UPDATE PROFILO "
					+ "SET Nome = ?, Cognome = ?, Foto = ?, Residenza = ?, Interessi = ?, DataNascita = ? "
					+ "WHERE Email = ? ";
			
			
			statementGetPass = connection.prepareStatement(queryRicerca);
			ResultSet oldPassSet = statementGetPass.executeQuery();
			String oldPasString;
			
			oldPassSet.next();
			oldPasString = oldPassSet.getString(1);
			
			
			if(!oldPasString.equals(oldPassword))
				throw new BadOldPasswordException("La vecchia password inserita non è corretta");
		
			String queryModificaPassword = "UPDATE ACCOUNT "
					+ "SET Password = ? "
					+ "WHERE Email = ? ";
			
			statementSetPass = connection.prepareStatement(queryModificaPassword);
			
			statementSetPass.setString(1, newPassword);
			statementSetPass.setString(2, toUpdate.getEmail());
			
			statementSetPass.execute();
			
			statement = connection.prepareStatement(queryModifica);
			
			statement.setString(1, toUpdate.getNome());
			statement.setString(2, toUpdate.getCognome());
			statement.setString(3, toUpdate.getFoto());
			statement.setString(4, toUpdate.getResidenza());
			statement.setString(5, toUpdate.getInteressi());

			statement.setDate(6, date);
			statement.setString(7, toUpdate.getEmail());
			statement.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		} finally {
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}	
	/**
	 * Si occupa dell'interrogazione al database per la disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @return Un booleano che indica se l'operazione � andata a buon fine
	 * @throws SQLException 
	 */
	public boolean queryDisattivaProfilo(String email) throws SQLException{
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="UPDATE account SET tipo='d' WHERE Email='"+email+"'";
			s=conn.createStatement();
			s.executeUpdate(query);
			if(s.getUpdateCount()==1) //verifico che l'update abbia avuto effetto su una riga
				return true;
			else
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
	 * Si occupa dell'interrogazione al database per la ricerca di un profilo
	 * @param nome Il nome dell'utente associato al profilo da ricercare
	 * @param cognome Il cognome dell'utente associato al profilo da ricercare
	 * @param email L'email dell'account associato al profilo da ricercare
	 * @return I profili trovati con i filtri inseriti, null se non trovati
	 */
	public ArrayList<Profilo> queryRicercaProfilo(String nome, String cognome, String email){
		
		Connection connection = null;
		Statement statement = null;
		try{
			connection = DBManager.getInstance().getConnection();
			statement = connection.createStatement();
		} catch(Exception e){
			return null;
		}
		
		boolean n = false, c = false, e = false;
		
		if(nome != null && !nome.equals(""))
			n = true;
		if(cognome != null && !cognome.equals(""))
			c = true;
		if(email != null && !email.equals(""))
			e = true;
		
		ArrayList<Profilo> resultN = null, resultC = null, resultE = null, resultD = new ArrayList<>();
		String query1 = "SELECT nome, cognome, dataNascita, foto, residenza, email FROM profilo WHERE ";
		String query2;
		String query3 = "SELECT email FROM account WHERE tipo = 'D'";
		ResultSet rsD;
		ResultSet rs;
		

		try{
			rsD = statement.executeQuery(query3);
			while(rsD.next()){
				Profilo p = new Profilo(rsD.getString(1), "", "", null, null, null, null, null);
				resultD.add(p);
			}
		} catch (Exception t) {
			
			return null;
		}
		if(e){
			query2 = "email = '" + email + "'";
			try{
				rs = statement.executeQuery(query1+query2);
				resultE = getParsedProfiles(rs);
			} catch (Exception t){
				
				return null;
			}

			if(resultE.size() > 0 && resultD.size() > 0){
				for (Profilo p1 : resultE) {
					for(Profilo p2 : resultD){
						if(cfrProfilo(p1, p2))
							resultE.remove(p1);
					}
				}
				return resultE;
			}
		}
		if(n){
			query2 = "nome = '" + nome + "'";
			try{
				rs = statement.executeQuery(query1+query2);
				resultN = getParsedProfiles(rs);
			} catch (Exception t){
				
				return null;
			}

			if(!c){
				for (Profilo p1 : resultN) {
					for(Profilo p2 : resultD){
						if(cfrProfilo(p1, p2))
							resultN.remove(p1);
					}
				}
				return resultN;
			}
		}
		if(c){
			query2 = "cognome = '" + cognome + "'";
			try{
				rs = statement.executeQuery(query1+query2);
				resultC = getParsedProfiles(rs);
			} catch (Exception t){
			
				return null;
			}

			if(!n){
				for (Profilo p1 : resultC) {
					for(Profilo p2 : resultD){
						if(cfrProfilo(p1, p2))
							resultC.remove(p1);
					}
				}
				return resultC;
			}
		}
		
		ArrayList<Profilo> result = new ArrayList<>();
		for (Profilo p1 : resultN) {
			for(Profilo p2 : resultC){
				if(cfrProfilo(p1, p2))
					result.add(p1);
			}
		}
		

		for (Profilo p1 : result) {
			for(Profilo p2 : resultD){
				if(cfrProfilo(p1, p2))
					result.remove(p1);
			}
		}
		
		return result;
				
	}
	
	public Profilo queryVisualizzaProfilo(String email) throws BadVisualizzaProfiloException  {

		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			Statement statementTest = connection.createStatement();
			
			
			String queryTest = "SELECT * FROM account WHERE email = '" + email + "' and tipo = 'R'";
			
			ResultSet resultSetTest = statementTest.executeQuery(queryTest);
			
			if(!resultSetTest.next()){
				throw new BadVisualizzaProfiloException("Impossibile visualizzare il profilo desiderato");
			}
			
			String query = "SELECT Nome, Cognome, Foto, Residenza, Telefono, Interessi, DataNascita FROM profilo WHERE Email= '"
					+ email +"'";
			
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			
			
			
			if (!resultSet.next())
				return null;
			else {
				String nome = resultSet.getString(1);
				String cognome = resultSet.getString(2);
				String foto = resultSet.getString(3);
				String residenza = resultSet.getString(4);
				String telefono = resultSet.getString(5);
				String interessi = resultSet.getString(6);
				Date dataNascita1 = resultSet.getDate(7);
				java.util.Date dataNascita=null;
				if(dataNascita1!=null)
					dataNascita = new Date(dataNascita1.getTime());
				return new Profilo(email, nome, cognome, residenza, telefono, interessi, foto, dataNascita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	/**
	 * Interroga il database per la ricerca di tutti gli annunci di un utente
	 * 
	 * @param email
	 *            la mail di cui ricercare tutti gli annunci
	 * @return La lista di tutti gli annunci dell'utente specificato, null se
	 *         tale lista è vuota
	 */
	public ArrayList<Annuncio> queryGetElencoAnnunci(String email)  {

		ArrayList<Annuncio> annunciPertinenti = new ArrayList<Annuncio>();
		Connection connection = null;
		try {
			connection = DBManager.getInstance().getConnection();
			String query = "SELECT ID, Titolo, Foto, DataPubblicazione, Prezzo, Descrizione, Email FROM annuncio WHERE email ='"
					+ email +"'";

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
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

			return annunciPertinenti;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	
	private ArrayList<Profilo> getParsedProfiles(ResultSet rs) throws SQLException{
		ArrayList<Profilo> result = new ArrayList<>();
		while(rs.next()){
			String name = rs.getString(1);
			String surname = rs.getString(2);
			Date date = new Date(rs.getDate(3).getTime());	
			String foto = rs.getString(4);
			String residence = rs.getString(5);
			String email = rs.getString(6);
			Profilo p = new Profilo(email, name, surname, residence, "", "", foto, date);
			result.add(p);
		}
		return result;
	}
	
	private boolean cfrProfilo(Profilo p1, Profilo p2){
		if( p1.getEmail().equals(p2.getEmail()) )
				return true;
		return false;
	}


}
