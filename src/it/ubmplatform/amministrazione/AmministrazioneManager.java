package it.ubmplatform.amministrazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

import it.ubmplatform.database.DBManager;
import it.ubmplatform.account.Account;

/**
 * Il model che contiene le query inerenti alla sezione Amministrazione
 *
 */
public class AmministrazioneManager implements AmministrazioneInterface {


	Logger logger = Logger.getLogger("global");
	/**
	 * Si occupa dell'interrogazione al database per la cancellazione di un account dal sistema
	 * @param email L'email dell'account da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	public boolean queryCancellaAccount(String email){
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="UPDATE account SET account.tipo='B' WHERE account.Email='"+email+"'";
			s=conn.createStatement();
			s.executeUpdate(query);
			if(s.getUpdateCount()==1) //verifico che l'update abbia avuto effetto su una riga
				return true;
			else
				return false;
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

	public boolean queryCancellaFeedback(String email){
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="DELETE FROM feedback where feedback.EmailP='"+email+"'";
			s=conn.createStatement();
			s.executeUpdate(query);
			if(s.getUpdateCount()>=0) //verifico che l'update abbia avuto effetto su una riga
				return true;
			else
				return false;
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
	 * Si occupa dell'interrogazione al database per l'invalidazione dell'account. 
	 * In particolare, setta il tipo dell'account ad 'I'
	 * @param email L'email dell'account da invalidare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	public boolean queryInvalidaAccount(String email){
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="UPDATE account SET account.tipo='I',account.DataInvalidazione= CURDATE() WHERE account.Email='"+email+"'";
			s=conn.createStatement();
			s.executeUpdate(query);
			if(s.getUpdateCount()==1) //verifico che l'update abbia avuto effetto su una riga
				return true;
			else
				return false;
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
	 * Si occupa dell'interrogazione al database per la rimozione di un account non inerente. 
	 * @param idAnnuncio L'id dell'annuncio da rimuovere
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */

	public boolean queryRimuoviAnnuncioNonInerente(int idAnnuncio) throws SQLException{
		Connection conn=null;
		PreparedStatement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			s=conn.prepareStatement("DELETE from annuncio WHERE ID=?");
			s.setInt(1, idAnnuncio);
			s.executeUpdate();
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
	 * Si occupa dell'interrogazione al database per l'ottenimento di tutti i profili iscritti al sistema
	 * @return La lista dei profili degli utenti registrati
	 * @throws SQLException 
	 */

	public ArrayList<Account> queryVisualizzaListaUtenti() throws SQLException{
		Connection conn=null;
		Statement s=null;
		ArrayList<Account> lista = new ArrayList<Account>();//creo un arraylist di Profilo 
		try {
			conn=DBManager.getInstance().getConnection(); 
			String query="SELECT account.* "+  						//prendo solo gli account validi
					"FROM account "+
					"WHERE account.Tipo='R' OR account.Tipo='D' "+
					"ORDER BY account.Email";

			s=conn.createStatement();
			//leggo i dati della tabella ritornata dalla query salvata in ResultSet
			ResultSet res=s.executeQuery(query);
			while(res.next()){
				//char[] cbuf = new char[1];
				//res.getCharacterStream("Tipo").read(cbuf);
				//char tipo = cbuf[0];
				lista.add(new Account(res.getString("Email"),res.getString("Password")));
			}
			res.close();
			return lista;
		} catch (SQLException e) {
			logger.info("queryVisualizzaListaIscritti"+e);
			throw(e);
		} 
		finally{
			logger.info("sono nel finally");
			if(s!=null)
				try {
					s.close();
				} catch (SQLException e) {
					e.printStackTrace();				}
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			logger.info("return lista"+lista);
			return lista;
		}

	}

	@Override
	public boolean queryRimuoviAnnuncioAccountCancellato(String email) {
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="DELETE FROM annuncio WHERE annuncio.Email='"+email+"'";
			s=conn.createStatement();
			s.executeUpdate(query);
			if(s.getUpdateCount()>0) //verifico che l'update abbia avuto effetto su una riga
				return true;
			else
				return false;
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
}
