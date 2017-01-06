package it.ubmplatform.feedback;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.istack.internal.logging.Logger;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce la modifica di un feedback da parte di un utente
 */
@WebServlet("/ModificaFeedbackServlet")
public class ModificaFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ModificaFeedbackServlet() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//la risposta da inviare al client
		String responseJson;
		response.setContentType("application/json");
		//if(request.getSession().getAttribute("email") != null){
		//dalla sessione
			String emailP = "francesco@unisa.it";
			String emailR = request.getParameter("emailR");
			//se sto richiedendo il feedback da modificare
			if(request.getParameter("tipo").equals("richiedi")){
				
				Feedback oldFeedback = ottieniFeedbackDaModificare(emailP, emailR);

				if(oldFeedback != null){
					//converto in json e invio
					Gson gson = new Gson();
					responseJson = gson.toJson(oldFeedback);
				}else{
					responseJson = "{\"state\":\"error\"}";
				}

			//altrimenti voglio modificarlo
			}else if(request.getParameter("tipo").equals("modifica")){
				int valutazione;
				try{
					valutazione = Integer.parseInt(request.getParameter("valutazione"));
					String descrizione = request.getParameter("descrizione");

					if(valutazione > 0 && valutazione < 6 && descrizione != null){
						
						Feedback newFeedback = new Feedback(valutazione, descrizione, emailP, emailR);

						if(modificaFeedback(newFeedback)){
							responseJson = "{\"state\":\"ok\"}";
						}else{
							//sql exception .. 
							responseJson = "{\"state\":\"feedbackerror\"}";
						}
					}else responseJson = "{\"state\":\"inputerror\"}";

				}catch(NumberFormatException e){
					responseJson = "{\"state\":\"valerror\"}";
				}
			}else responseJson = "{\"state\":\"requesterror\"}";


		/*DA USARE-----------------------------
		}else{
			responseJson = "{\"state\":\"nosession\"}";
		}
		 */
		response.getWriter().write(responseJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	/**
	 * Metodo che si occupa di smistare la richiesta di modifica al {@link FeedbackManager}, che contiene le query adatte
	 * @param newFeedback il nuovo feedback da aggiornare
	 * @return Un booleano che indica se l'operazione di modifica è andata a buon fine
	 * @invariant VisualizzaFeedbackServlet.visualizzaFeedbacks(newFeedback.emailR) != null
	 */
	private boolean modificaFeedback(Feedback newFeedback){
		AbstractFactory factory = new ManagerFactory();
		FeedbackInterface model = factory.createFeedbackManager();
		return model.queryModificaFeedback(newFeedback);
	}

	private Feedback ottieniFeedbackDaModificare(String emailP, String emailR){
		AbstractFactory factory = new ManagerFactory();
		FeedbackInterface model = factory.createFeedbackManager();
		return model.queryOttieniFeedbackDaModificare(emailP, emailR);
	}
}
