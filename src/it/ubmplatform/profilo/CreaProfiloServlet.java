package it.ubmplatform.profilo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire il salvataggio di un nuovo profilo
 */
@WebServlet("/CreaProfiloServlet")
public class CreaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreaProfiloServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Il metodo che si occupa di richiedere il salvataggio del nuovo profilo al {@link ProfiloManager}
	 * @param toInsert Il profilo da registrare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre toInsert != null
	 * @pre RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) == null
	 * @post RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) != null
	 */
	private boolean creaProfilo(Profilo toInsert){
		return false;
	}

}
