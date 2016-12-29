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
			ps.setDate(5, new java.sql.Date(toInsert.getData().getTime()));
			
			ps.execute();
			

			//ritorno true se il metodo execute è andato a buon fine
			//dopo aver provato la chiusura
			try{
				close(conn);
				close(ps);
				return true;
			}catch(Exception e){
				return true;
			}
			
		
		}catch(SQLException e){
			e.printStackTrace();
			
			try{
				close(conn);
				close(ps);
				return false;
			}catch(Exception e1){
				return false;
			}
			
			
		}
		
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la modifica di un feedback
	 * @param changed Il nuovo feedback modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	
	public boolean queryModificaFeedback(Feedback changed){
		return false;
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
				+ "WHERE EmailR = \"" + emailR + "\"";
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
					java.util.Date data = new java.util.Date(rs.getDate(4).getTime()); 
					
					feedbacks.add(new Feedback(val, desc, email, data));
				}while(rs.next());
				
				return feedbacks;
			}else{
				//non ci sono risultati (no feedback)
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			try {
				close(conn);
				close(st);
				close(rs);
				return null;
			} catch (Exception e1) {
				return null;
			}
		}
	}
	
	private void close(AutoCloseable closing) throws Exception{
		if(closing != null){
			closing.close();
		}
	}
}
