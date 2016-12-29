package it.ubmplatform.annunci;

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
 * Servlet che si occupa di gestire la cancellazione di un annuncio dal sistema
 */
@WebServlet("/CancellaAnnuncioServlet")
public class CancellaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session=request.getSession();
    	if(cancellaAnnuncio((int)session.getAttribute("id"))){ //verifico se la disattivazione è riuscita
    		//rimuovo l'accesso e effettuo redirect alla home
    		session.removeAttribute("id");
    		response.sendRedirect("index.jsp");
    	}
    	else{
    		throw new OperationFailedException("La cancellazione dell'annuncio non ha avuto successo, riprova più tardi");
    	}
    }
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} di cancellazione annuncio dal database
	 * @param idAnnuncio L'id dell'annuncio da cancellare
	 * @return Un booleano che indica se l'operazione di cancellazione è andata a buon fine
	 * @pre idAnnuncio &gt; 0
	 * @pre VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) != null
	 * @post VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) == null
	 */
	private boolean cancellaAnnuncio(int idAnnuncio){
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryCancellaAnnuncio(idAnnuncio);
	}
}
