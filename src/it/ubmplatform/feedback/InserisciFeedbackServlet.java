package it.ubmplatform.feedback;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.istack.internal.logging.Logger;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce l'inserimento di un feedback da parte di un utente
 */
@WebServlet(name = "InserisciFeedbackServlet", urlPatterns={"/InserisciFeedbackServlet"})
public class InserisciFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public InserisciFeedbackServlet() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if(request.getSession().getAttribute("email") != null){

		int valutazione;

		String jsonReturn;
		response.setContentType("application/json");
		
		try{
			valutazione = Integer.parseInt(request.getParameter("valutazione"));
			String descrizione = request.getParameter("descrizione");

			if(valutazione > 0 && valutazione < 6 && descrizione != null){
				//prendo l'email di chi sta pubblicando e di chi riceve
				//uno dalla sessione (chi pubblica) e l'altro dal profilo dell'utente (un input hidden?) inviato
				String emailP = "marco@unisa.it";
				String emailR = "prova@unisa.it";

				Feedback newFeedback = new Feedback(valutazione, descrizione, emailP, emailR);

				if(inserisciFeedback(newFeedback)){
					jsonReturn = "{\"state\":\"ok\"}";
				}else{
					//sql exception .. 
					jsonReturn = "{\"state\":\"feedbackerror\"}";

				}
			}else jsonReturn = "{\"state\":\"inputerror\"}";

		}catch(NumberFormatException e){
			jsonReturn = "{\"state\":\"valerror\"}";
		}


		/*DA USARE-----------------------------
				}else{
					jsonReturn = "{\"state\":\"nosession\"}";
				}
		 */
		response.getWriter().write(jsonReturn);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Metodo che smista la richiesta di inserimento feedback al {@link FeedbackManager}
	 * @param toInsert Il feedback da inserire
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre toInsert != null
	 * @post VisualizzaFeedbackServlet.visualizzaFeedbacks(toInsert.emailR) != null
	 */

	private boolean inserisciFeedback(Feedback toInsert){
		AbstractFactory factory = new ManagerFactory();
		FeedbackInterface model = factory.createFeedbackManager();
		return model.queryInserisciFeedback(toInsert);
	}
}
