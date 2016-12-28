package it.ubmplatform.feedback;

/**
 * Bean che rappresenta un Feedback all'interno del sistema
 * @author Marco
 *
 */
public class Feedback {
		/**
	 * Crea un feedback con valutazione (int da 1 a 5) e descrizione
	 * @param val La valutazione assegnata
	 * @param desc La descrizione assegnata
	 * @param emailPublisher L'email di chi ha pubblicato il feedback
	 * @param emailReceiver L'email di chi ha ricevuto il feedback
	 * @pre 0 &lt; val &lt; 6
	 */
	
	Feedback(int val, String desc, String emailPublisher, String emailReceiver){
		valutazione = val;
		descrizione = desc;
		emailP = emailPublisher;
		emailR = emailReceiver;
		
		//SETTARE LA DATA-----
	}
	
	
	int getValutazione() {
		return valutazione;
	}


	String getDescrizione() {
		return descrizione;
	}


	String getEmailP() {
		return emailP;
	}

	String getEmailR() {
		return emailR;
	}

	String getData() {
		return data;
	}

	void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	private int valutazione;
	private String descrizione, emailP, emailR, data;
}
