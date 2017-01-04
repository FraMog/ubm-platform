package it.ubmplatform.registrazione;

import com.mysql.jdbc.Statement;

import it.ubmplatform.account.Account;
import javax.sql.*;
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
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3060/ubmplatform",
			"root","root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		java.sql.Statement st = null;
		try {
			st =  con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		ResultSet rs; 
		try {
			int i=st.executeUpdate("INSERT INTO account VALUES ('"+toInsert.getEmail()+"','"+toInsert.getPassword()+"','n')");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return false;
	}
}
