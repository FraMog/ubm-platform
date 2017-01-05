package it.ubmplatform.profilo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che si occupa di gestire la disattivazione di un profilo da parte dell'utente fino al prossimo login
 */
@WebServlet("/DisattivaProfilo")
public class DisattivaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	try{
	    	disattivaProfilo((String)session.getAttribute("emailLoggato")); //eseguo l'operazione
			//forward sul logout
			request.getRequestDispatcher("/LogoutServlet").forward(request, response);
    	}
    	catch(Exception e){
    		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta al {@link ProfiloManager} di disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @throws SQLException 
	 * @throws OperationFailedException 
	 * @pre RicercaProfiloServlet.ricercaProfilo(email) != null
	 * @post RicercaProfiloServlet.ricercaProfilo(email) == null
	 */
	private void disattivaProfilo(String email) throws SQLException, OperationFailedException{
		AbstractFactory factory = new ManagerFactory();
		ProfiloInterface managerProfilo = factory.createProfiloManager();
		if(!managerProfilo.queryDisattivaProfilo(email)){
			throw new OperationFailedException("La disattivazione del profilo non ha avuto successo, riprova più tardi");
		}
	}
}
