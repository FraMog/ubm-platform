package it.ubmplatform.profilo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che si occupa di gestire la ricerca di un profilo
 */
@WebServlet("/RicercaProfiloServlet")
public class RicercaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RicercaProfiloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = null, cognome = null, email = null;
		try{
			nome = request.getParameter("nameToSearch");
			cognome = request.getParameter("surnameToSearch");
			email = request.getParameter("emailToSearch");
			
		} catch (Exception e ){
			request.getSession().setAttribute("listaProfili", null);
		}
		
		if( (nome == null || nome.equals("")) && (cognome == null || cognome.equals("")) && (email == null || email.equals("")) ) {
			request.getSession().setAttribute("listaProfili", null);
		}

		
		try {
			ArrayList<Profilo> r = ricercaProfilo(nome, cognome, email);
			request.getSession().setAttribute("listaProfili",r );
		} catch (Exception e) {
			request.getSession().setAttribute("listaProfili", null);
		}
		
		
		response.sendRedirect("ricercaProfilo.jsp");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta di ricerca al {@link ProfiloManager} in base ad uno o tutti i parametri richiesti
	 * @param nome L'eventuale nome da ricercare
	 * @param cognome L'eventuale cognome da ricercare
	 * @param email L'eventuale email da ricercare
	 * @return Il/i profilo/i dell'utente ricercato, null altrimenti
	 * @pre nome != null OR cognome != null OR email != null
	 */
	private ArrayList<Profilo> ricercaProfilo(String nome, String cognome, String email){
		AbstractFactory f = new ManagerFactory();
		ProfiloInterface manager = f.createProfiloManager();
		return manager.queryRicercaProfilo(nome, cognome, email);
	}

}
