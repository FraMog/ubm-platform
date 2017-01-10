package it.ubmplatform.registrazione;
import it.ubmplatform.account.Account;
import it.ubmplatform.database.DBManager;

import java.sql.*;
/**
 * Il model che gestisce la query per la registrazione di un account
 */

public class RegistrazioneManager implements RegistrazioneInterface {

	/**
	 * Si occupa dell'interrogazione al database per la registrazione di un account
	 * @param toInsert L'account da registrare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 */
	public boolean queryRegistraAccount(Account toInsert){
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="INSERT INTO account VALUES ('"+toInsert.getEmail()+"','"+toInsert.getPassword()+"','R',NULL)";
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
	
	public boolean queryAccountEsistente(String email){
		Connection conn=null;
		Statement s=null;
		try {
			conn=DBManager.getInstance().getConnection();
			String query="SELECT * FROM account WHERE account.Email='"+email+"'";
			s=conn.createStatement();
			ResultSet res=s.executeQuery(query);
			if(res.next())
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
