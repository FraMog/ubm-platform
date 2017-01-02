package it.ubmplatform.autenticazione;

import java.io.IOException;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.ubmplatform.account.Account;
import it.ubmplatform.annunci.AnnuncioInterface;
import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * La servlet che si occupa di loggare l'utente al sistema, interrogando l'AutenticazioneManager
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
    
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (email.equals("") && password.equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}

		else
		{
			Account myAccount = new Account(email, password);
			if (login(myAccount)==0)	//cerca prima nella tabella dell'amministratore
			{
				session.setAttribute("user", "admin");
				RequestDispatcher rd = request.getRequestDispatcher("homePageAdmin.jsp");
				rd.forward(request, response);
			}
			else if (login(myAccount)==1)	//cerca nella tabella degli account utente e trova account
			{
				session.setAttribute("user", "utente");
				String nome = estraiNome(myAccount);
				if (nome!=null)
				{
					session.setAttribute("name", nome);
				}
				else 
				{
					session.setAttribute("name", "utente");
				}
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				
			}
			else if (login(myAccount)==2)	//trova account invalidato. compare avviso e verifica se puo accedere
			{
				GregorianCalendar dataAttuale = new GregorianCalendar();
				if (controllaData(dataAttuale, myAccount))
				{
					session.setAttribute("user", "utente");
					String nome = estraiNome(myAccount);
					if (nome!=null)
					{
						session.setAttribute("name", nome);
					}
					else 
					{
						session.setAttribute("name", "utente");
					}
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
				else
				{
					session.setAttribute("dataInvalidazioneNonTrovata", "true");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}
			else if (login(myAccount)==3)	//trova account ma è stato bannato. avvisa l'utente
			{
				session.setAttribute("accountBannato", "true");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			else
			{
				session.setAttribute("accountNonTrovato", "true");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * Il metodo che si occupa di smistare la richiesta di ricerca all'{@link AutenticazioneManager}
	 * @param toSearch L'account da cercare
	 * @return 0 se l'utente loggato è l'admin, 1 se l'account è stato trovato ed è Regolare, 2 se l'account è stato trovato ed è Invalidato, 3 se l'account è stato trovato ed è Bannato, -1 in caso di errore 
	 * @pre toSearch != null
	 */
	private int login(Account toSearch)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryLogin(toSearch);
	}
	
	/**
	 * Il metodo che si occupa di recuperare il nome dell'utente loggato.
	 * @param loggato L'account di cui trovare il nome.
	 * @return Una stringa che indica il nome se la richiesta è stata effettuata con successo, null altrimenti.
	 * @pre loggato != null
	 */
	
	private String estraiNome (Account trovato)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryEstraiNome(trovato);
	}
	
	/**
	 * Il metodo che si occupa di controllare che un utente invalidato possa accedere al sistema.
	 * @param dataAttuale La data attuale a partire dalla quale bisogna effettuare il controllo.
	 * @param trovato L'account di cui verificare la data di invalidazione.
	 * @return true se l'utente può accedere al sistema, false altrimenti.
	 * @pre dataAttuale != null
	 */
	
	private boolean controllaData (GregorianCalendar dataAttuale, Account trovato)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryControllaData(dataAttuale, trovato);
	}
}



