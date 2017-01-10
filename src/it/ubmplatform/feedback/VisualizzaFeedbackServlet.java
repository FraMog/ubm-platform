package it.ubmplatform.feedback;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce la visualizzazione dei feedback di un utente
 */

@WebServlet(name = "VisualizzaFeedbackServlet", urlPatterns={"/VisualizzaFeedbackServlet"})
public class VisualizzaFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public VisualizzaFeedbackServlet() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseJson;

		if(request.getSession().getAttribute("emailLoggato") != null){

			String emailR = request.getParameter("emailR");

			if(emailR != null){
				ArrayList<Feedback> feedbacks = visualizzaFeedbacks(emailR);

				if(feedbacks != null){
					//converto in json e invio
					//se l'arraylist è vuoto (no feedback) il controllo verrà fatto in javascript

					Gson gson = new Gson();
					responseJson = gson.toJson(feedbacks);


				}else{
					responseJson = "{\"state\":\"feedbackerror\"}";
				}
			}else{
				responseJson = "{\"state\":\"emailerror\"}";
			}


		}else{
			responseJson = "{\"state\":\"nosession\"}";
		}

		response.getWriter().write(responseJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Metodo che si occupa di smistare la richiesta al {@link FeedbackManager} che contiene la query adatta
	 * @param emailR L'email di chi ha ricevuto i feedback da visualizzare
	 * @return Un arraylist contenente i feedbacks dell'utente, null se non ci sono
	 * @pre emailR != null
	 */
	private ArrayList<Feedback> visualizzaFeedbacks(String emailR){
		//istanzio un manager factory, che mi fornirà il metodo per la creazione del feedback manager
		AbstractFactory factory = new ManagerFactory();
		FeedbackInterface model = factory.createFeedbackManager();
		return model.queryVisualizzaFeedbacks(emailR);
	}

}
