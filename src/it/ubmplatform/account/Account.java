package it.ubmplatform.account;

import java.sql.Date;

/**
 * Bean Account che rappresenta l'account all'interno del sistema. Contiene le informazioni relative all'account (email, password, tipo)
 * @author Marco
 */

public class Account {

	/**
	 * Crea un account con email, password, tipo e data di invalidazione.
	 * @param newEmail L'email dell'account
	 * @param pw La password dell'account
	 * @param tipo Il tipo di account
	 * @param dataInvalidazione L'eventuale data di invalidazione da parte dell'amministratore.
	 */
  //costruttore Account con parametri email, password,tipo
	public Account(String email, String password, String tipo) {
		this.email = email;
		this.password = password;
		this.tipo = tipo;
	}
	 //costruttore Account con parametri email, password
	public Account(String email, String password){
		this.email = email;
		this.password = password;
		this.tipo="R";
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getTipo() {
		return tipo;
	}

	public Date getDataInvalidazione() {
		return dataInvalidazione;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void setDataInvalidazione(Date dataInvalidazione) {
		this.dataInvalidazione = dataInvalidazione;
	}


	/**
	 * Il tipo dell'account (Bannato, Invalidato, Regolare)
	 */
	private String email, password, tipo;
	private Date dataInvalidazione;

}
