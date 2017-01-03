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
    	
    	//se sto richiedendo il feedback da visualizzare
		if(request.getParameter("tipo").equals("richiedi")){
			String responseJson = "{\"state\":\"error\"}";
			//dalla sessione
			String emailP = "m.dellamedaglia@studenti.unisa.it";
			String emailR = request.getParameter("emailR");
			Feedback oldFeedback = ottieniFeedbackDaModificare(emailP, emailR);
			
			if(oldFeedback != null){
				//converto in json e invio
    			Gson gson = new Gson();
    			responseJson = gson.toJson(oldFeedback);
			}
			
			response.getWriter().write(responseJson);
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//con la dopost effettuo l'update
		//inizialmente la valutazione sarà uguale a 0 per effettuarne controlli 
		//in caso di mancata modifica
		int valutazione = 0;
				
				
		try{
			valutazione = Integer.parseInt(request.getParameter("changingValutazione"));
		}catch(Exception e){
			//QUALCHE PROBLEMA SULLA VALUTAZIONE
		}
				
		String descrizione = request.getParameter("changingDescrizione");
				
		if(valutazione > 0 && valutazione < 6 && descrizione != null){
			//prendo l'email di chi sta pubblicando e di chi riceve
			//uno dalla sessione (chi pubblica) e l'altro dal profilo dell'utente (un input hidden?) inviato
			String emailP = "m.dellamedaglia@studenti.unisa.it";
			String emailR = "example@studenti.unisa.it";
					
			Feedback newFeedback = new Feedback(valutazione, descrizione, emailP, emailR);
					
			if(modificaFeedback(newFeedback)){
				request.getRequestDispatcher("visualizzaProfiloAltro.jsp").forward(request, response);
			}else{
				//NON E' STATO POSSIBILE COMPLETARE LA RICHIESTA
						
			}
		}else{
			//NON E' STATO POSSIBILE ACCEDERE ALLA RICHIESTA
					
		}
				
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
