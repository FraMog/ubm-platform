package it.ubmplatform.autenticazione;


public interface AutenticazioneInterface {
	
	int queryLogin(String email, String password);
	String queryRecuperaPassword(String email);
	int queryControllaData(long dataAttuale, String emailTrovata);
	String queryEstraiNome(String emailTrovata);

}
