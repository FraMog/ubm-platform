package it.ubmplatform.profilo;

import java.util.Date;

import it.ubmplatform.eccezioni.BadCreaProfiloException;

/**
 * Bean Profilo che rappresenta il profilo all'interno del sistema
 * Esso contiene tutte le informazioni relative al profilo
 * @author Marco
 *
 */
public class Profilo {
	/**
	 * Crea un nuovo profilo con le info associate
	 * @param newEmail L'email dell'utente a cui � associato il profilo
	 * @param name Il nome dell'utente a cui � associato il profilo
	 * @param surname Il cognome dell'utente a cui � associato il profilo
	 * @param residence La residenza dell'utente a cui � associato il profilo
	 * @param phone Il telefono dell'utente a cui � associato il profilo
	 * @param interest I vari interessi dell'utente a cui � associato il profilo
	 * @param photo La foto dell'utente a cui � associato il profilo
	 * @param dataNascita La data di nascita dell'utente
	 * @throws BadCreaProfiloException nel caso in cui alcuni campi siano compilati in modo scorretto
	 */
	public Profilo(String newEmail, String name, String surname, String residence, String phone, String interest, String photo, Date dataNascita) throws BadCreaProfiloException{
		
		if(name == null || name.length() < 1 || surname == null || surname.length() < 1
				|| name.matches(".*\\d+.*") || surname.matches(".*\\d+.*") || 
				newEmail.indexOf("unisa.it") == - 1 || (phone != null && phone.length()!= 0 && phone.length()!= 10)){
			throw new BadCreaProfiloException();
		}
		
		email = newEmail;
		nome = name;
		cognome = surname;
		residenza = residence;
		telefono = phone;
		interessi = interest;
		foto = photo;
		this.dataNascita=dataNascita;
	}
	
	

	public String getFoto() {
		return foto;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getResidenza() {
		return residenza;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getInteressi() {
		return interessi;
	}

	

	void setFoto(String foto) {
		this.foto = foto;
	}



	void setResidenza(String residenza) {
		this.residenza = residenza;
	}



	void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	void setInteressi(String interessi) {
		this.interessi = interessi;
	}
	
	
	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}



	@Override
	public String toString() {
		return "Profilo [foto=" + foto + ", email=" + email + ", nome=" + nome + ", cognome=" + cognome + ", residenza="
				+ residenza + ", telefono=" + telefono + ", interessi=" + interessi + ", dataNascita=" + dataNascita
				+ "]";
	}


	/**
	 * L'attributo foto
	 */
	private String foto;
	private String email, nome, cognome, residenza, telefono, interessi;
	private Date dataNascita;

}
