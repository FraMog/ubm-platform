package it.ubmplatform.amministrazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce la cancellazione di utente da parte di un amministratore
 */
@WebServlet("/CancellaAccountServlet")
public class CancellaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CancellaAccountServlet() {
        super();
     
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Il metodo che si occupa di effettuare la cancellazione dell'utente, smistandolo all'{@link AmministrazioneManager}
	 * @param email L'email dell'utente da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre email != null
	 */
	private boolean cancellaAccount(String email){
		return false;
	}

}
