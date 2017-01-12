package it.ubmplatform.autenticazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.ubmplatform.account.Account;
import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.database.DBManager;
public class AutenticazioneManager implements AutenticazioneInterface {


	/**
	 * Si occupa dell'interrogazione al database per la ricerca dell'account
	 * @param toSearch L'account da ricercare
	 * @return 0 se l'utente loggato è l'admin, 1 se l'account è stato trovato ed è Regolare, 2 se l'account è stato trovato ed è Invalidato, 3 se l'account è stato trovato ed è Bannato, -1 se l'account non è presente, -2 in caso di errore
	 */

	public int queryLogin(String emailToSearch, String passwordToSearch)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isAdmin=false;

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryLoginAdmin = "SELECT * FROM amministratore WHERE Email='"+emailToSearch+"' && Password='"+passwordToSearch+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryLoginAdmin);

			while (resultSet.next())
			{
				if(emailToSearch.equals(resultSet.getString(1)) && passwordToSearch.equals(resultSet.getString(2)))
				{
					isAdmin=true;
					return 0; 
				}
			}

			if (isAdmin==false)
			{
				String queryLoginAccount = "SELECT * FROM account WHERE Email='"+emailToSearch+"' && Password='"+passwordToSearch+"'";
				statement=connection.createStatement();
				resultSet = statement.executeQuery(queryLoginAccount);
				boolean isUtente=false; 

				while (resultSet.next())
				{
					if(emailToSearch.equals(resultSet.getString(1)) && passwordToSearch.equals(resultSet.getString(2)))
					{
						isUtente = true;
						Account accountTrovato = new Account(emailToSearch, passwordToSearch, resultSet.getString(3));
						if (accountTrovato.getTipo().equals("R"))		//account Regolare
						{
							return 1;
						}
						else if (accountTrovato.getTipo().equals("I"))	//account Invalidato per breve tempo
						{
							return 2;
						}
						else if (accountTrovato.getTipo().equals("B"))	//account Bannato, non è possibile accedere
						{
							return 3;
						}
						else if(accountTrovato.getTipo().equals("D")){ //account disattivato
							statement.executeUpdate("UPDATE account SET Tipo='R' WHERE Email='"+emailToSearch+"'");
							return 1;
						}
					}
				}
				if (isUtente==false)
				{
					return -1;
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			return -2;
		} finally {
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return -2;
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return -2;
				}			
		}
		return -1;
	}

	/**
	 * Si occupa dell'interrogazione al database per la ricerca della password dell'account associato all'email
	 * @param email L'email dell'account a cui ricercare la password
	 * @return La password dell'account in caso di successo, null altrimenti (account non trovato).
	 */

	public String queryRecuperaPassword(String email)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String passwordRecuperata = null;
		boolean isAdmin=false;

		try
		{
			//cerca prima nella tabella dell'amministratore
			connection=DBManager.getInstance().getConnection();
			String queryRecuperaPass = "SELECT Password FROM amministratore WHERE Email='"+email+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryRecuperaPass);

			while (resultSet.next())
			{
				passwordRecuperata = resultSet.getString(1);
			}

			if (passwordRecuperata!=null)
			{
				isAdmin=true;
				return passwordRecuperata;
			}

			//altrimenti in quella dell'account
			else
			{
				connection=DBManager.getInstance().getConnection();
				queryRecuperaPass = "SELECT Password FROM account WHERE Email='"+email+"'";
				statement=connection.createStatement();
				resultSet = statement.executeQuery(queryRecuperaPass);

				while (resultSet.next())
				{
					passwordRecuperata = resultSet.getString(1);
				}

				return passwordRecuperata;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}


	/**
	 * Si occupa dell'interrogazione al database per la ricerca dei dati dell'account associato all'email trovata
	 * @param emailTrovata L'e-mail dell'utente di cui bisogna trovare il nome.
	 * @return Una stringa che indica il nome in caso di successo, null altrimenti.
	 */

	public String queryEstraiNome(String emailTrovata)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String nomeUtente = null;

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryNome = "SELECT Nome FROM profilo WHERE Email='"+emailTrovata+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryNome);

			while (resultSet.next())
			{
				nomeUtente = resultSet.getString(1);
			}

			return nomeUtente;
		} catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		} finally
		{
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

	/**
	 * Il metodo che si occupa di controllare che un utente invalidato possa accedere al sistema.
	 * @param dataAttuale La data attuale (in millisecondi) per verificare se la settimana di invalidazione è terminata.
	 * @param emailTrovata L'email dell'account di cui verificare la data di invalidazione.
	 * @return 0 se l'utente può accedere al sistema, giorni Un intero che indica quanto tempo aspettare prima di poter effettuare l'accesso, -1 in caso di errore nel recupero della data.
	 * @pre dataAttuale != null
	 */

	public int queryControllaData(long dataAttuale, String emailTrovata)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		long dataInvalidazione = 0; //data invalidazione in millisecondi
		Date dataDb = null;	

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryData = "SELECT DataInvalidazione FROM account WHERE Email='"+emailTrovata+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryData);

			while (resultSet.next())
			{
				dataDb = resultSet.getDate(1);
				dataInvalidazione = dataDb.getTime() + 691200000;	//691200000 sono 8 giorni in ms

				if (dataAttuale<=dataInvalidazione)
				{
					long giorniInMill= dataInvalidazione-dataAttuale;
					int giorni = (int) Math.floor(giorniInMill / 86400000);		//1 giorno medio = 1000*60*60*24 ms = 86400000 ms
					return giorni;
				}
				else
				{
					connection=DBManager.getInstance().getConnection();
					String queryModificaTipo = "UPDATE account SET Tipo = 'R' WHERE Email ='"+emailTrovata+"'";
					statement=connection.createStatement();
					statement.executeUpdate(queryModificaTipo);

					String queryEliminaData = "UPDATE account SET DataInvalidazione = null WHERE Email ='"+emailTrovata+"'";
					statement=connection.createStatement();
					statement.executeUpdate(queryEliminaData);

					return 0;
				}
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		} finally
		{
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return -1;

	}

}
