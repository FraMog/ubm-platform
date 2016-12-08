package it.ubmplatform.profilo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la modifica di un profilo
 */
@WebServlet("/ModificaProfiloServlet")
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ModificaProfiloServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di richiedere la modifica del profilo al {@link ProfiloManager}
	 * @param changed Il profilo modificato
	 * @return 1 se l'operazione di modifica è andata a buon fine, un numero negativo che indica l'errore altrimenti
	 * @pre changed != null
	 * @post {@literal @}pre.RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != RicercaProfiloServlet.ricercaProfilo(changed.getEmail())
	 * @invariant RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != null
	 */
	private int modificaProfilo(Profilo changed){
		return 0;
	}

}
