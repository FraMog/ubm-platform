package it.ubmplatform.profilo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di ottenere un profilo selezionato
 */
@WebServlet("/VisualizzaProfiloServlet")
public class VisualizzaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public VisualizzaProfiloServlet() {
        super();
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di ottenere il profilo selezionato, smistando la richiesta per la query al {@link ProfiloManager}
	 * @param email L'email del profilo da visualizzare
	 * @return Il profilo selezionato, null se non esiste (errore)
	 * @pre email != null
	 */
	private Profilo visualizzaProfilo(String email){
		return null;
	}
}
