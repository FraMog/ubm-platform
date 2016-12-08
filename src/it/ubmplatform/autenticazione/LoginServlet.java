package it.ubmplatform.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.account.Account;

/**
 * La servlet che si occupa di loggare l'utente al sistema, interrogando l'AutenticazioneManager
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//------
	}

	/**
	 * Il metodo che si occupa di smistare la richiesta di ricerca all'{@link AutenticazioneManager}
	 * @param toSearch L'account da cercare
	 * @return 1 se l'operazione di login è stata effettuata con successo, un numero negativo che indica la condizione di errore altrimenti. 
	 * @pre toSearch != null
	 */
	private int login(Account toSearch){
		return -1;
	}
}
