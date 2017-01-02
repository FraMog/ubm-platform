package it.ubmplatform.amministrazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * La servlet che si occupa di gestire l'invalidazione di un utente dal sistema per un periodo di tempo stabilito dall'amministratore
 */
@WebServlet("/InvalidaAccountServlet")
public class InvalidaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(invalidaAccount(request.getParameter("email"))){ 
			//invalido l'account e effettuo redirect alla home
			request.removeAttribute("email");
			response.sendRedirect("index.jsp");
		} 
		else{
			throw new OperationFailedException("L'invalidazione dell'account non ha avuto successo, riprova più tardi");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	/**
	 * Metodo che si occupa di invalidare un account, smistando la richiesta all'{@link AmministrazioneManager} per la query adatta
	 * @param email L'email dell'account da invalidare
	 * @return Un booleano che indica se il metodo è andato a buon fine
	 * @pre email != null
	 */
	private boolean invalidaAccount(String email){
		AbstractFactory factory = new ManagerFactory();
		AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryInvalidaAccount(email);
	}
}
