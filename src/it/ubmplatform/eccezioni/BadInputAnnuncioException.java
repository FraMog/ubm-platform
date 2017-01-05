package it.ubmplatform.eccezioni;

import java.io.IOException;

/**
 * BadInputAnnuncioException estende IOException, e fa quindi parte della classe 
 * degli input non validi. BadInputAnnuncioException � lanciata se 
 * l'input ad un determinato costruttore (o setter) non � valido. 
 * In particolare, � lanciata se l'edizione � negativa, se il prezzo � negativo
 * e se l'email non rispecchia il formato x@unisa.it
 * 
 * @author Alfonso
 *
 */
public class BadInputAnnuncioException extends IOException{
	/**
	 * Version UID 1
	 */
	
	private static final long serialVersionUID = 1L;

	public BadInputAnnuncioException(){
		super();
	}
	
	public BadInputAnnuncioException(String message){
		super(message);
	}

}