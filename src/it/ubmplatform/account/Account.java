package it.ubmplatform.account;

import java.util.GregorianCalendar;

/**
 * Bean Account che rappresenta l'account all'interno del sistema. Contiene le informazioni relative all'account (email, password, tipo)
 * @author Marco
 */

public final class Account {
	/**
	 * Crea un account con email, password e tipo = R (regolare)
	 * @param newEmail L'email dell'account
	 * @param pw La password dell'account
	 */
	
	public Account(String newEmail, String pw){
		email = newEmail;
		password = pw;
		tipo = "R";
		dataInvalidazione = null;
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

	public GregorianCalendar getDataInvalidazione() {
		return dataInvalidazione;
	}

	public void setDataInvalidazione(GregorianCalendar dataInvalidazione) {
		this.dataInvalidazione = dataInvalidazione;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	private GregorianCalendar dataInvalidazione;
	/**
	 * Il tipo dell'account (Bannato, Invalidato, Regolare)
	 */
	private String tipo;
	private String email, password;

}
