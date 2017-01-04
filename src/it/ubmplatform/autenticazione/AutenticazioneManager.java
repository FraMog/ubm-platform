package it.ubmplatform.autenticazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

import it.ubmplatform.account.Account;
import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.database.DBManager;
public class AutenticazioneManager implements AutenticazioneInterface {


	/**
	 * Si occupa dell'interrogazione al database per la ricerca dell'account
	 * @param toSearch L'account da ricercare
	 * @return 0 se l'utente loggato è l'admin, 1 se l'account è stato trovato ed è Regolare, 2 se l'account è stato trovato ed è Invalidato, 3 se l'account è stato trovato ed è Bannato, -1 in caso di errore
	 */
	
	public int queryLogin(Account toSearch)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isAdmin=false;

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryLoginAdmin = "SELECT * FROM amministratore WHERE Email='"+toSearch.getEmail()+"' && Password='"+toSearch.getPassword()+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryLoginAdmin);
			
			while (resultSet.next())
			{
				if(toSearch.getEmail().equals(resultSet.getString(1)) && toSearch.getPassword().equals(resultSet.getString(2)))
				{
					isAdmin=true;
					return 0; 
				}
			}

			if (isAdmin==false)
			{
				String queryLoginAccount = "SELECT * FROM account WHERE Email='"+toSearch.getEmail()+"' && Password='"+toSearch.getPassword()+"'";
				statement=connection.createStatement();
				resultSet = statement.executeQuery(queryLoginAccount);
				boolean isUtente=false; 
		
				while (resultSet.next())
				{
					Account accountTrovato = new Account(resultSet.getString(1), resultSet.getString(2));
					accountTrovato.setTipo(resultSet.getString(3));
					if(toSearch.getEmail().equals(accountTrovato.getEmail()) && toSearch.getPassword().equals(accountTrovato.getPassword()))
					{
						isUtente = true;
						if (toSearch.getTipo().equals("R"))			//account Regolare
						{
							System.out.println(toSearch.getEmail()+toSearch.getTipo());
							return 1;
						}
						else if (toSearch.getTipo().equals("I"))	//account Invalidato per breve tempo
						{
							System.out.println(toSearch.getEmail()+toSearch.getTipo());
							return 2;
						}
						else if (toSearch.getTipo().equals("B"))	//account Bannato, non è possibile accedere
						{
							System.out.println(toSearch.getEmail()+toSearch.getTipo());
							return 3;
						}
					}
					else
						return -1;
				}
				
				if (isUtente=false)
				{
					return -1;
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
	
	/**
	 * Si occupa dell'interrogazione al database per la ricerca della password dell'account associato all'email
	 * @param email L'email dell'account a cui ricercare la password
	 * @return La password dell'account in caso di successo, null altrimenti (account non trovato)
	 */
	
	public String queryRecuperaPassword(String email)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String passwordRecuperata = null;

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryRecuperaPass = "SELECT Password FROM account WHERE Email='"+email+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryRecuperaPass);

			while (resultSet.next())
			{
				passwordRecuperata = resultSet.getString(1);
			}

			return passwordRecuperata;
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
	 * @param trovato L'account trovato nel database di cui estrarre le informazioni
	 * @return La password dell'account in caso di successo, null altrimenti (account non trovato)
	 */
	
	public String queryEstraiNome(Account trovato)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String nomeUtente = null;

		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryNome = "SELECT Nome FROM profilo WHERE Email='"+trovato.getEmail()+"'";
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
	 * Si occupa dell'interrogazione al database per la ricerca della data di invalidazione.
	 * @param trovato L'account trovato nel database di cui estrarre la data di invalidazione.
	 * @param trovato L'account di cui verificare la data di invalidazione.
	 * @return true se l'utente può accedere al sistema, false altrimenti.
	 */
	
	public boolean queryControllaData(GregorianCalendar dataAttuale, Account trovato)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		GregorianCalendar dataInvalidazione = new GregorianCalendar();
		Date dataDb = null;
		long invDateInMill=0;	//data invalidazione 
		long attDateInMill=0;	//data attuale


		try
		{
			connection=DBManager.getInstance().getConnection();
			String queryData = "SELECT DataInvalidazione FROM account WHERE Email='"+trovato.getEmail()+"'";
			statement=connection.createStatement();
			resultSet = statement.executeQuery(queryData);

			while (resultSet.next())
			{
				dataDb = resultSet.getDate(1);
				dataInvalidazione.setTime(dataDb);
				invDateInMill = dataInvalidazione.getTimeInMillis()+604800000;		//604800000 sono 7 giorni in ms
				attDateInMill = dataAttuale.getTimeInMillis();

				if (attDateInMill<=invDateInMill)		
					return false;
				else
					return true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
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
		return false;

	}

}
