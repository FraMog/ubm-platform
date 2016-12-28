package it.ubmplatform.profilo;

import java.io.IOException;
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
    	if(disattivaProfilo((String)session.getAttribute("email"))){ //verifico se la disattivazione � riuscita
    		//rimuovo l'accesso e effettuo redirect alla home
    		session.removeAttribute("email");
    		session.removeAttribute("name");
    		response.sendRedirect("index.jsp");
    	}
    	else{
    		throw new OperationFailedException("La disattivazione del profilo non ha avuto successo, riprova pi� tardi");
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta al {@link ProfiloManager} di disattivazione del profilo
	 * @param email L'email del profilo da disattivare
	 * @return Un booleano che indica se l'operazione � andata a buon fine
	 * @pre RicercaProfiloServlet.ricercaProfilo(email) != null
	 * @post RicercaProfiloServlet.ricercaProfilo(email) == null
	 */
	private boolean disattivaProfilo(String email){
		AbstractFactory factory = new ManagerFactory();
		ProfiloInterface managerProfilo = factory.createProfiloManager();
		return managerProfilo.queryDisattivaProfilo(email);
	}
}
