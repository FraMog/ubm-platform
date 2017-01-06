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

		//if(request.getSession().getAttribute("email") != null){

		//se sto richiedendo il feedback da modificare
		if(request.getParameter("tipo").equals("richiedi")){
			//dalla sessione
			String emailP = "m.dellamedaglia@studenti.unisa.it";
			String emailR = request.getParameter("emailR");
			Feedback oldFeedback = ottieniFeedbackDaModificare(emailP, emailR);

			if(oldFeedback != null){
				//converto in json e invio
				Gson gson = new Gson();
				responseJson = gson.toJson(oldFeedback);
			}else{
				responseJson = "{\"state\":\"error\"}";
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
		//la risposta da inviare al client
		String jsonReturn;
			
		//if(request.getSession().getAttribute("email") != null){
		
		int valutazione;
		try{
			valutazione = Integer.parseInt(request.getParameter("changingValutazione"));
			String descrizione = request.getParameter("changingDescrizione");

			if(valutazione > 0 && valutazione < 6 && descrizione != null){
				//prendo l'email di chi sta pubblicando e di chi riceve
				//uno dalla sessione (chi pubblica) e l'altro dal profilo dell'utente (un input hidden?) inviato
				String emailP = "m.dellamedaglia@studenti.unisa.it";
				String emailR = "example@studenti.unisa.it";

				Feedback newFeedback = new Feedback(valutazione, descrizione, emailP, emailR);

				if(modificaFeedback(newFeedback)){
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
			responseJson = "{\"state\":\"nosession\"}";
		}
		 */
		response.getWriter().write(jsonReturn);

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
