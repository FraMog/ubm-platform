package it.ubmplatform.registrazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.account.Account;

/**
 * La servlet che gestisce la registrazione al sistema. 
 * In particolare, preleva i dati in input, ne effettua la verifica e,
 * se i dati sono corretti, richiede l'inserimento dei dati nel database al RegistrazioneManager.
 */
@WebServlet("/RegistraUtenteServlet")
public class RegistraUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegistraUtenteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--------
	}

	/**
	 * Il metodo che si occupa di smistare le richieste di inserimento nel database al {@link RegistrazioneManager}
	 * @param toWrite L'account da inserire nel database
	 * @return 1 se l'operazione di registrazione è stata effettuata correttamente, un numero negativo che indica la tipologia di errore altrimenti
	 * @pre toWrite != null
	 */
	private int registraAccount(Account toWrite){
		return 0;
	}
}
