package it.ubmplatform.profilo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la disattivazione di un profilo da parte dell'utente fino al prossimo login
 */
@WebServlet("/DisattivaProfiloServlet")
public class DisattivaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DisattivaProfiloServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta al {@link ProfiloManager} di disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre RicercaProfiloServlet.ricercaProfilo(email) != null
	 * @post RicercaProfiloServlet.ricercaProfilo(email) == null
	 */
	private boolean disattivaProfilo(String email){
		return false;
	}
}
