package it.ubmplatform.account;

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
		tipo = 'R';
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public char getTipo() {
		return tipo;
	}

	
	
	/**
	 * Il tipo dell'account (Bannato, Invalidato, Regolare)
	 */
	private char tipo;
	private String email, password;
}
