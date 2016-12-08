package it.ubmplatform.feedback;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che gestisce l'inserimento di un feedback da parte di un utente
 */
@WebServlet("/InserisciFeedbackServlet")
public class InserisciFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public InserisciFeedbackServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che smista la richiesta di inserimento feedback al {@link FeedbackManager}
	 * @param toInsert Il feedback da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre toInsert != null
	 * @post VisualizzaFeedbackServlet.visualizzaFeedbacks(toInsert.emailR) != null
	 */
	
	private boolean inserisciFeedback(Feedback toInsert){
		return false;
	}
}
