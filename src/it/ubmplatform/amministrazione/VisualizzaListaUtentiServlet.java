package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.profilo.Profilo;

/**
 * Servlet che si occupa di ritornare la lista completa degli utenti del sistema
 */
@WebServlet("/VisualizzaListaUtentiServlet")
public class VisualizzaListaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public VisualizzaListaUtentiServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AmministrazioneManager} per l'interrogazione al database
	 * @return La lista dei profili degli utenti registrati
	 */
	private ArrayList<Profilo> visualizzaListaUtenti(){
		return null;
	}
}
