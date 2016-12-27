package it.ubmplatform.factory;

import it.ubmplatform.amministrazione.AmministrazioneInterface;
import it.ubmplatform.annunci.AnnuncioInterface;
import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.feedback.FeedbackInterface;
import it.ubmplatform.profilo.ProfiloInterface;
import it.ubmplatform.registrazione.RegistrazioneInterface;

public abstract class AbstractFactory {
	
	public abstract ProfiloInterface createProfiloManager();
	public abstract AutenticazioneInterface createAutenticazioneManager();
	public abstract AnnuncioInterface createAnnuncioManager();
	public abstract AmministrazioneInterface createAmministrazioneManager();
	public abstract FeedbackInterface createFeedbackManager();
	public abstract RegistrazioneInterface createRegistrazioneManager();
	

}
