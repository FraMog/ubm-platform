package it.ubmplatform.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette ad un utente di uscire dal sistema
 */

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//----
	}
	
	/**
	 * Metodo che si occupa della gestione del logout, togliendo dalla sessione l'utente loggato
	 * @return Un booleano che indica se il logout è stato effettuato correttamente
	 */
	private boolean logout(){
		return false;
	}

}
