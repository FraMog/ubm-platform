package it.ubmplatform.eccezioni;

import java.io.IOException;

/**
 * BadInputFeedbackException estende IOException, e fa quindi parte della classe 
 * degli input non validi. BadInputFeedbackException � lanciata se 
 * l'input ad un determinato costruttore (o setter) non � valido. 
 * In particolare, � lanciata se la valutazione � negativa o maggiore di 
 * 5 e se l'email non rispecchia il formato x@unisa.it
 * 
 * @author Marco
 *
 */
public class BadInputFeedbackException extends IOException{
	/**
	 * Version UID 1
	 */
	
	private static final long serialVersionUID = 1L;

	public BadInputFeedbackException(){
		super();
	}
	
	public BadInputFeedbackException(String message){
		super(message);
	}

}
