package it.ubmplatform.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import it.ubmplatform.database.DBManager;

/**
 * Il model che contiene le query inerenti alla sezione Feedback
 */

public class FeedbackManager implements FeedbackInterface {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un feedback
	 * @param toInsert Il feedback da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryInserisciFeedback(Feedback toInsert){
		//connessione e statement a null per la chiusura in caso di eccezione
		//ed eventuale controllo
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			//prendo la connessione dalla classe statica DBManager
			conn = DBManager.getInstance().getConnection();
			
			//formo la stringa contenente la query da effettuare
			String queryInserisci = "INSERT INTO FEEDBACK VALUES(?,?,?,?,?)";
			
			//preparo lo statement per formare la query
			ps = conn.prepareStatement(queryInserisci);
			
			ps.setString(1, toInsert.getEmailP());
			ps.setString(2, toInsert.getEmailR());
			ps.setInt(3, toInsert.getValutazione());
			ps.setString(4, toInsert.getDescrizione());
			
			//la data in sql (java.sql.Date)
			ps.setDate(5, toInsert.getData());
			
			ps.execute();
			

			//ritorno true se il metodo execute è andato a buon fine
			return true;
			
		
		}catch(SQLException e){
			e.printStackTrace();		
			return false;
		}finally{
			//provo la chiusura
			close(conn);
			close(ps);
		}
		
	}
	
	/**
	 * Metodo di servizio per richiedere al database il feedback che si vuole modificare
	 * per la stampa asincrona all'avvio del modal
	 * @param emailP L'email di chi ha pubblicato il feedback
	 * @param emailR L'email di chi ha ricevuto il feedback
	 * @return il feedback richiesto, null se inesistente (non possibile)
	 */
	public Feedback queryOttieniFeedbackDaModificare(String emailP, String emailR){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try{
			conn = DBManager.getInstance().getConnection();
			String query = "SELECT Valutazione, Descrizione FROM Feedback Where emailP = ? AND emailR = ?";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, emailP);
			ps.setString(2, emailR);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				int valutazione = rs.getInt(1);
				String descrizione = rs.getString(2);
				
				Feedback oldFeedback = new Feedback(valutazione, descrizione, emailP, emailR);
				
				return oldFeedback;
			}else{
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			//provo la chiusura
			close(conn);
			close(ps);
			close(rs);
			
		}
	}
	/**
	 * Si occupa dell'interrogazione al database per la modifica di un feedback
	 * @param changed Il nuovo feedback modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryModificaFeedback(Feedback changed){
		//connessione e statement a null per la chiusura in caso di eccezione
		//ed eventuale controllo
		Connection conn = null;
		PreparedStatement ps = null;
				
		try{
			//prendo la connessione dalla classe statica DBManager
			conn = DBManager.getInstance().getConnection();
					
			//formo la stringa contenente la query da effettuare
			String queryInserisci = "UPDATE FEEDBACK "
					+ "SET Valutazione = ?, Descrizione = ? "
					+ "WHERE emailP = ? AND emailR = ?";
					
			//preparo lo statement per formare la query
			ps = conn.prepareStatement(queryInserisci);
					
			ps.setInt(1, changed.getValutazione());
			ps.setString(2, changed.getDescrizione());
			ps.setString(3, changed.getEmailP());
			ps.setString(4, changed.getEmailR());
					
			ps.execute();
					

			//ritorno true se il metodo execute è andato a buon fine
			
			return true;
					
				
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}finally{
			//provo la chiusura
			close(conn);
			close(ps);
			
		}
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione di tutti i feedback di un utente
	 * @param emailR L'email associata all'account di cui visualizzarne i feedback
	 * @return La lista dei feedback dell'account associato all'email, null se non presenti
	 */
	
	public ArrayList<Feedback> queryVisualizzaFeedbacks(String emailR){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String queryVisualizzaFeedback = "SELECT EmailP, Valutazione, Descrizione, DataPubblicazione "
				+ "FROM Feedback "
				+ "WHERE EmailR = '" + emailR + "'";
		try{
			conn = DBManager.getInstance().getConnection();
			st = conn.createStatement();
			
			//eseguo la query e memorizzo i risultati nel result set
			rs = st.executeQuery(queryVisualizzaFeedback);
			
			ArrayList<Feedback> feedbacks = new ArrayList<Feedback>();
			
			//valuto i risultati.. se c'è qualcosa.. 
			if(rs.next()){
				do{
					String email = rs.getString(1);
					int val = rs.getInt(2);
					String desc = rs.getString(3);
					java.sql.Date data = rs.getDate(4);
					
					//aggiungo gli n feedback all'arraylist
					feedbacks.add(new Feedback(val, desc, email, data));
				}while(rs.next());
				
				return feedbacks;
			}else{
				//non ci sono risultati (no feedback)
				//per capirlo, aggiungo un feedback "vuoto"
				feedbacks.add(new Feedback(0,"","",""));
				return feedbacks;
			}
		}catch(SQLException e){
			e.printStackTrace();

			return null;
			
		}finally{
			close(conn);
			close(st);
			close(rs);
		}
	}
	
	private void close(AutoCloseable closing){
		if(closing != null){
			try {
				closing.close();
			} catch (Exception e) {
				//eventualmente non chiudo nulla
			}
		}
	}
}
