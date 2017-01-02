package it.ubmplatform.autenticazione;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce il recupero di password di un utente, a cui verrà inviata tramite e-mail
 */
@WebServlet("/RecuperaPasswordServlet")
public class RecuperaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session; 
    
    public RecuperaPasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		String email = request.getParameter("email");
		
		if (email.equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("recuperaPassword.jsp");
			rd.forward(request, response);
		}
		else
		{
			if (recuperaPassword(email))
			{
				//invia mail (??) contenente pass + alert invio con successo
			}
			else
			{
				//errore nell'invio
			}
		}
	}
	
	/**
	 * Il metodo che si occupa di inviare l'email all'utente
	 * @param email L'email dell'utente a cui recuperare la password, passato come parametro dall'utente
	 * @return Un booleano che indica se l'operazione di invio email è andata a buon fine
	 * @pre email != null
	 */
	private boolean recuperaPassword(String email){
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		String password = model.queryRecuperaPassword(email);
		if (password==null)
			return false;
		else
			return true;
	}
	

}
