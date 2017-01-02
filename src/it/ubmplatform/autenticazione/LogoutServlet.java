package it.ubmplatform.autenticazione;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che permette ad un utente di uscire dal sistema
 */

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	
    
    public LogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		session.invalidate();
		if (logout())
		{
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
		}
		else
		{
			session.setAttribute("logoutErrato", "true");
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.forward(request, response);
		}
	}
	
	/**
	 * Metodo che si occupa della gestione del logout, togliendo dalla sessione l'utente loggato
	 * @return Un booleano che indica se il logout è stato effettuato correttamente
	 */
	private boolean logout()
	{
		if ((session.getAttribute("user")==null)&&(session.getAttribute("mail")==null))
			return true;
		else 
			return false;
	}

}
