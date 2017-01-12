package it.ubmplatform.autenticazione;

import java.io.IOException;
import java.util.regex.Pattern;

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
		Pattern p = Pattern.compile("^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it)|^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@unisa.it)|ubmplatform@gmail.com");
		Pattern q = Pattern.compile("((?=.*[0-9])(?=.*[a-zA-Z]).{8,20})");
		
		if(!p.matcher(email).find() || !q.matcher(password).find())
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "I campi non sono stati compilati correttamente.");
		}

		else
		try{
			if (email.equals("") && password.equals(""))
			{
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}

			else
			{
				int login=login(email, password);
				if (login==0)	//cerca prima nella tabella dell'amministratore
				{
					session.setAttribute("user", "admin");
					RequestDispatcher rd = request.getRequestDispatcher("homePageAdmin.jsp");
					rd.forward(request, response);
				}
				else if (login==1)	//cerca nella tabella degli account utente e trova account
				{
					session.setAttribute("user", "utente");

					String nome = estraiNome(email);
					if (nome!=null)
					{
						session.setAttribute("name", nome);
					}
					else 		//se non ha completato il profilo inserendo i propri dati, visualizza l'e-mail al posto del nome
					{
						session.removeAttribute("user");
						session.setAttribute("password", password);
						request.setAttribute("email", email);
						request.getRequestDispatcher("creaProfilo.jsp").forward(request,response);
					}

					session.setAttribute("emailLoggato", email);
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);

				}
				else if (login==2)	//trova account invalidato. compare avviso e verifica se puo accedere
				{
					long dataAttuale = System.currentTimeMillis();

					if (controllaData(dataAttuale, email)==0)
					{
						session.setAttribute("user", "utente");

						String nome = estraiNome(email);
						if (nome!=null)
						{
							session.setAttribute("name", nome);
						}
						else 
						{
							session.setAttribute("name", email);
						}

						session.setAttribute("emailLoggato", email);
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
					else if (controllaData(dataAttuale, email)==-1)
					{
						request.setAttribute("dataInvalidazioneNonTrovata", "true");
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
					else
					{
						int giorni = controllaData(dataAttuale, email);
						String giorniAttesa = ""+giorni;
						request.setAttribute("giorniAttesa", giorniAttesa);
						request.setAttribute("accountInvalidato", "true");
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);
					}
				}
				else if (login==3)	//trova account ma è stato bannato. avvisa l'utente
				{
					request.setAttribute("accountBannato", "true");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
				else if (login==-1)
				{
					request.setAttribute("accountNonTrovato", "true");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
				else
				{
					request.setAttribute("accountNonTrovato", "erroreConnessione");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
 			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
 		}
	}

	/**
	 * Il metodo che si occupa di smistare la richiesta di ricerca all'{@link AutenticazioneManager}
	 * @param emailToSearch L'email da cercare
	 * @param passwordToSearch La password da cercare
	 * @return 0 se l'utente loggato è l'admin, 1 se l'account è stato trovato ed è Regolare, 2 se l'account è stato trovato ed è Invalidato, 3 se l'account è stato trovato ed è Bannato, -1 se l'account non è presente, -2 in caso di errore 
	 * @pre toSearch != null
	 */
	private int login(String emailToSearch, String passwordToSearch)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryLogin(emailToSearch, passwordToSearch);
	}

	/**
	 * Il metodo che si occupa di recuperare il nome dell'utente loggato.
	 * @param emailTrovata L'e-mail dell'utente di cui bisogna trovare il nome.
	 * @return Una stringa che indica il nome se la richiesta è stata effettuata con successo, null altrimenti.
	 * @pre loggato != null
	 */

	private String estraiNome (String emailTrovata)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryEstraiNome(emailTrovata);
	}

	/**
	 * Il metodo che si occupa di controllare che un utente invalidato possa accedere al sistema.
	 * @param dataAttuale La data attuale (in millisecondi) per verificare se la settimana di invalidazione è terminata.
	 * @param emailTrovata L'email dell'account di cui verificare la data di invalidazione.
	 * @return 0 se l'utente può accedere al sistema, giorni Un intero che indica quanto tempo aspettare prima di poter effettuare l'accesso, -1 in caso di errore nel recupero della data.
	 * @pre dataAttuale != null
	 */

	private int controllaData (long dataAttuale, String emailTrovata)
	{
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		return model.queryControllaData(dataAttuale, emailTrovata);
	}
}