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
    	String emailR = request.getParameter("emailR");
    	ArrayList<Feedback> feedbacks = null;
    	String responseJson = "{\"state\":\"error\"}";
    	System.out.println("..");
    	if(emailR != null){
    		feedbacks = visualizzaFeedbacks(emailR);
    		
    		if(feedbacks != null){
    			//converto in json e invio
    			Gson gson = new Gson();
    			responseJson = gson.toJson(feedbacks);
    			
    			
    		}else{
    			//il metodo ritorna null. perché?
    		}
    	}else{
    		
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
		return model.queryVisualizzaFeedbacks("example@studenti.unisa.it");
	}

}
