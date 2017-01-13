package it.ubmplatform.feedback;

import java.sql.Date;

import it.ubmplatform.eccezioni.BadInputFeedbackException;

/**
 * Bean che rappresenta un Feedback all'interno del sistema
 * @author Marco
 *
 */
public class Feedback{
	/**
	 * Crea un feedback con valutazione (int da 1 a 5) e descrizione
	 * @param val La valutazione assegnata
	 * @param desc La descrizione assegnata
	 * @param emailPublisher L'email di chi ha pubblicato il feedback
	 * @param emailReceiver L'email di chi ha ricevuto il feedback
	 * @throws BadInputFeedbackException Se l'input non � valido
	 * @pre 0 &lt; val &lt; 6
	 */

	Feedback(int val, String desc, String emailPublisher, String emailReceiver) throws BadInputFeedbackException{
		if(val < 0 || val > 5 || emailPublisher.indexOf("unisa.it") == - 1 || emailReceiver.indexOf("unisa.it") == - 1 ){
			throw new BadInputFeedbackException();
		}else{
			valutazione = val;
			descrizione = desc;
			emailP = emailPublisher;
			emailR = emailReceiver;
			data = new Date(System.currentTimeMillis());
		}
	}

	/**
	 * Crea un feedback con valutazione (int da 1 a 5) e descrizione
	 * SENZA EMAIL DI CHI RICEVE IL FEEDBACK (in caso di visualizza feedback)
	 * @param val La valutazione assegnata
	 * @param desc La descrizione assegnata
	 * @param emailPublisher L'email di chi ha pubblicato il feedback
	 * @throws BadInputFeedbackException Se l'input non � valido
	 * @pre 0 &lt; val &lt; 6
	 */

	Feedback(int val, String desc, String emailPublisher, Date dataPublished) throws BadInputFeedbackException{
		if(val < 0 || val > 5 || emailPublisher.indexOf("unisa.it") == - 1){
			throw new BadInputFeedbackException();
		}else{
			valutazione = val;
			descrizione = desc;
			emailP = emailPublisher;
			data = dataPublished;
		}
	}

	public int getValutazione() {
		return valutazione;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public String getEmailP() {
		return emailP;
	}

	public String getEmailR() {
		return emailR;
	}

	public Date getData() {
		return data;
	}

	void setData(Date data) {
		this.data = data;
	}

	/**
	 * Setto la valutazione. Se non compresa tra 1 e 5, lancia BadInputFeedbackException
	 * @param valutazione
	 * @throws BadInputFeedbackException Se la valutazione non � compresa tra 1 e 5
	 */
	void setValutazione(int valutazione) throws BadInputFeedbackException {
		if(valutazione < 0 || valutazione > 5) throw new BadInputFeedbackException();
		else this.valutazione = valutazione;
	}

	void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	private int valutazione;
	private String descrizione, emailP, emailR;
	private Date data;
}
