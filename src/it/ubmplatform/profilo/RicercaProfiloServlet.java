package it.ubmplatform.profilo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la ricerca di un profilo
 */
@WebServlet("/RicercaProfiloServlet")
public class RicercaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RicercaProfiloServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ---
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta di ricerca al {@link ProfiloManager} in base ad uno o tutti i parametri richiesti
	 * @param nome L'eventuale nome da ricercare
	 * @param cognome L'eventuale cognome da ricercare
	 * @param email L'eventuale email da ricercare
	 * @return Il/i profilo/i dell'utente ricercato, null altrimenti
	 * @pre nome != null || cognome != null || email != null
	 */
	private ArrayList<Profilo> ricercaProfilo(String nome, String cognome, String email){
		return null;
	}

}
