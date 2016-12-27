package it.ubmplatform.factory;

import it.ubmplatform.amministrazione.*;
import it.ubmplatform.annunci.*;
import it.ubmplatform.autenticazione.*;
import it.ubmplatform.feedback.*;
import it.ubmplatform.profilo.*;
import it.ubmplatform.registrazione.*;

public class ManagerFactory extends AbstractFactory{

	@Override
	public ProfiloInterface createProfiloManager() {
		return new ProfiloManager();
	}

	@Override
	public AutenticazioneInterface createAutenticazioneManager() {
		return new AutenticazioneManager();
	}

	@Override
	public AnnuncioInterface createAnnuncioManager() {
		return new AnnuncioManager();
	}

	@Override
	public AmministrazioneInterface createAmministrazioneManager() {
		return new AmministrazioneManager();
	}

	@Override
	public FeedbackInterface createFeedbackManager() {
		return new FeedbackManager();
	}

	@Override
	public RegistrazioneInterface createRegistrazioneManager() {
		return new RegistrazioneManager();
	}

}
