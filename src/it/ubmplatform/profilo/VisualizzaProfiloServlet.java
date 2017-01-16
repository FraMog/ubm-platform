package it.ubmplatform.profilo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;
import it.ubmplatform.feedback.Feedback;
import it.ubmplatform.feedback.FeedbackInterface;
import it.ubmplatform.annunci.Annuncio;
import it.ubmplatform.eccezioni.BadVisualizzaProfiloException;

/**
 * Servlet che si occupa di ottenere un profilo selezionato
 */
@WebServlet("/VisualizzaProfiloServlet")
public class VisualizzaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {  
	    super.init(config);
	    //Your code  
	}

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		Profilo profileToShow = null;
		
		HttpSession thisSession = request.getSession();
		
		
		String emailToShow; 
		String loggedEmail = "#";
		boolean isAdmin = false;
	
		if(thisSession.getAttribute("user") != null && thisSession.getAttribute("user") == "admin")
			isAdmin = true;
		
		
		
		try{ 
			emailToShow =  request.getParameter("emailToShow");
			if(!isAdmin)
				loggedEmail = (String) thisSession.getAttribute("emailLoggato");

		} catch(Exception e){
			//rimandare alla home 
			response.sendRedirect("index.jsp");
			return;
		}
	
		

		try {
			ArrayList<Annuncio> lista = this.getListaAnnunci(emailToShow);
			profileToShow = visualizzaProfilo(emailToShow);
			thisSession.setAttribute("profileToShow", profileToShow);
			request.setAttribute("listaAnnunci", lista);
			
			// ottengo i feedback
			
			ArrayList<Feedback> feedback = this.getListaFeedback(emailToShow);
			request.setAttribute("listaFeedback", feedback);

			
		}
		catch (Exception e) {
			response.sendError(404, "Impossibile visualizzare il profilo desiderato");
			return;
		}
		RequestDispatcher rd;
		if (profileToShow == null){
			response.sendError(404, "Il profilo desiderato non esiste");
			return;
		}
		if (!isAdmin && loggedEmail.equals(emailToShow))
			rd = request.getRequestDispatcher("visualizzaProfiloProprio.jsp");
		else
			rd = request.getRequestDispatcher("visualizzaProfiloAltro.jsp");

			
		rd.forward(request, response);
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Metodo che si occupa di ottenere il profilo selezionato, smistando la
	 * richiesta per la query al {@link ProfiloManager}
	 * 
	 * @param email
	 *            L'email del profilo da visualizzare
	 * @return Il profilo selezionato, null se non esiste (errore)
	 * @throws BadVisualizzaProfiloException 
	 * @pre email != null
	 */
	private Profilo visualizzaProfilo(String email) throws BadVisualizzaProfiloException {
		AbstractFactory f = new ManagerFactory();
		ProfiloInterface manager = f.createProfiloManager();
		return manager.queryVisualizzaProfilo(email);
	}

	/**
	 * Metodo che si occupa di ottenere il profilo selezionato, smistando la richiesta per la query al {@link ProfiloManager}
	 * @param email L'email del profilo da visualizzare *****************************************
	 * @return Il profilo selezionato, null se non esiste (errore)********************************
	 * @pre email != null
	 */
	private ArrayList<Annuncio> getListaAnnunci(String email){
		AbstractFactory f = new ManagerFactory();
		ProfiloInterface manager = f.createProfiloManager();
		return manager.queryGetElencoAnnunci(email);
	}
	/**
	 * ************
	 * @param email L'email del profilo da visualizzare *****************************************
	 * @return Il profilo selezionato, null se non esiste (errore)********************************
	 * @pre email != null
	 */
	private ArrayList<Feedback> getListaFeedback(String email){
		AbstractFactory f = new ManagerFactory();
		FeedbackInterface manager = f.createFeedbackManager();
		return manager.queryVisualizzaFeedbacks(email);
	}
}

