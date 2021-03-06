package it.ubmplatform.annunci;

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
 * Servlet che si occupa di gestire la cancellazione di un annuncio dal sistema
 */
@WebServlet("/CancellaAnnuncioServlet")
public class CancellaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/plain;charset=UTF-8");
    	try {
			if(cancellaAnnuncio(Integer.parseInt(request.getParameter("annuncioID")))) { 
				//rimuovo l'annuncio e effettuo redirect alla home
				response.setStatus(HttpServletResponse.SC_OK);
			}
			else{
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //se sono state lanciate eccezioni imposto lo stato di errore
				response.getWriter().write("Errore nella cancellazione dell'annuncio, riprovare pi� tardi");
			}
		} catch (NumberFormatException | SQLException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //se sono state lanciate eccezioni imposto lo stato di errore
			response.getWriter().write("Errore nella cancellazione dell'annuncio, riprovare pi� tardi");
		}
    }
  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} di cancellazione annuncio dal database
	 * @param idAnnuncio L'id dell'annuncio da cancellare
	 * @return Un booleano che indica se l'operazione di cancellazione � andata a buon fine
	 * @throws SQLException 
	 * @pre idAnnuncio &gt; 0
	 * @pre VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) != null
	 * @post VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) == null
	 */
	private boolean cancellaAnnuncio(int idAnnuncio) throws SQLException{
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryCancellaAnnuncio(idAnnuncio);
	}
}

