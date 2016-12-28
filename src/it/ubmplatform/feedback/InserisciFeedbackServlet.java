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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//inizialmente la valutazione sarà uguale a 0 per effettuarne controlli 
		//in caso di mancata modifica
		int valutazione = 0;
		
		
		try{
			valutazione = Integer.parseInt(request.getParameter("valutazioneFeedback"));
		}catch(Exception e){
			//la valutazione era a null (valori erano condizionati)
			//porto alla pagina del profilo?
			
		}
		
		String descrizione = request.getParameter("descrizioneFeedback");
		
		if(valutazione > 0 && valutazione < 6 && descrizione != null){
			//prendo l'email di chi sta pubblicando e di chi riceve
			//uno dalla sessione (chi pubblica) e l'altro dal profilo dell'utente (un input hidden?) inviato
			String emailP = "m.dellamedaglia@studenti.unisa.it";
			String emailR = "example@studenti.unisa.it";
			
			Feedback newFeedback = new Feedback(valutazione, descrizione, emailP, emailR);
			
			if(inserisciFeedback(newFeedback)){
				System.out.println("OK");
			}else{
				//il metodo mi ritorna false.. perché
				
			}
		}else{
			//è successo qualcosa, riporto alla pagina del profilo?
			
		}
		
		//response.getWriter().write(valutazione + " " + descrizione);
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
