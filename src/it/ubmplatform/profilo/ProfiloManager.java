package it.ubmplatform.profilo;

import java.sql.*;
import java.util.ArrayList;

import it.ubmplatform.database.DBManager;

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
	 * @param changed Il nuovo profilo modificato da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	public boolean queryModificaProfilo(Profilo changed){
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
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
		return null;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per la visualizzazione di un profilo
	 * @param email L'email dell'account associato al profilo da ricercare
	 * @return Il profilo se l'operazione è andata a buon fine, null in caso di errore
	 */
	public Profilo queryVisualizzaProfilo(String email){
		return null;
	}
}
