package it.ubmplatform.feedback;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce la visualizzazione dei feedback di un utente
 */

@WebServlet("/VisualizzaFeedbackServlet")
public class VisualizzaFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VisualizzaFeedbackServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta al {@link FeedbackManager} che contiene la query adatta
	 * @param emailR L'email di chi ha ricevuto i feedback da visualizzare
	 * @return Un arraylist contenente i feedbacks dell'utente, null se non ci sono
	 * @pre emailR != null
	 */
	private ArrayList<Feedback> visualizzaFeedbacks(String emailR){
		return null;
	}

}
