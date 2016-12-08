package it.ubmplatform.feedback;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce la modifica di un feedback da parte di un utente
 */
@WebServlet("/ModificaFeedbackServlet")
public class ModificaFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ModificaFeedbackServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta di modifica al {@link FeedbackManager}, che contiene le query adatte
	 * @param emailP L'email di chi ha pubblicato il feedback
	 * @param emailR L'email di chi ha ricevuto il feedback
	 * @return Un booleano che indica se l'operazione di modifica è andata a buon fine
	 * @pre emailP != null
	 * @pre emailR != null
	 * @invariant VisualizzaFeedbackServlet.visualizzaFeedbacks(emailR) != null
	 */
	private boolean modificaFeedback(String emailP, String emailR){
		return false;
	}
}
