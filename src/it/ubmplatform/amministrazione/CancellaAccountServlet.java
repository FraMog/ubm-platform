package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce la cancellazione di utente da parte di un amministratore
 */
@WebServlet("/CancellaAccountServlet")
public class CancellaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger= Logger.getLogger("global");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("trovata"+ request.getParameter("email"));
		System.out.println("trovata"+ request.getParameter("eliminaFeedback"));
		if(request.getParameter("eliminaFeedback").equals("true")){
			//AGGIUNGERE RIMOZIONE FEEDBACK
		}
		boolean valore=cancellaAccount(request.getParameter("email"));
	   	if(valore){ 
    		//rimuovo l'account 
    		request.removeAttribute("email");
    		response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("Success Data");  
		} 
		else{
			throw new OperationFailedException("La cancellazione dell'account non ha avuto successo, riprova più tardi");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Il metodo che si occupa di effettuare la cancellazione dell'utente, smistandolo all'{@link AmministrazioneManager}
	 * @param email L'email dell'utente da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre email != null
	 */
	private boolean cancellaAccount(String email){
		try {
			AbstractFactory factory = new ManagerFactory();
			AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryCancellaAccount(email);
		} catch (Exception e) {
			logger.info(" service error " + e);
			return true;
		}
	}

}
