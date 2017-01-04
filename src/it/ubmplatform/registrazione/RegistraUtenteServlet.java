package it.ubmplatform.registrazione;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;
import com.oracle.jrockit.jfr.RequestableEvent;

import it.ubmplatform.account.Account;
import it.ubmplatform.registrazione.*;

/**
 * La servlet che gestisce la registrazione al sistema. 
 * In particolare, preleva i dati in input, ne effettua la verifica e,
 * se i dati sono corretti, richiede l'inserimento dei dati nel database al RegistrazioneManager.
 */
@WebServlet("/RegistraUtenteServlet")
public class RegistraUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RegistraUtenteServlet() {
        super();
        
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--------
		
		String password=request.getParameter("pass");
		String email=request.getParameter("name");
		System.out.println(email+"\t"+password);
		Account acc=new Account(email,password);
		System.out.println("OBJ : "+acc.getEmail()+"\t"+acc.getPassword());

		
		//Regione di codice per eseguire la query di inserimento nuovo utente
		RegistrazioneManager rm=new RegistrazioneManager();
		
		boolean query_flag=rm.queryRegistraAccount(acc);
		if(query_flag==true){
			System.out.println("query ok");
			ServletContext context=getServletContext();
			//context.setAttribute("indirizzo", email);
			request.setAttribute("indirizzo", email);

			RequestDispatcher rd=context.getRequestDispatcher("/sendcode.jsp");
			rd.forward(request, response);
			//response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+"/sendcode.jsp?dest="+email));
		}
		else System.out.println("FAILED ");
	}

	/**
	 * Il metodo che si occupa di smistare le richieste di inserimento nel database al {@link RegistrazioneManager}
	 * @param toWrite L'account da inserire nel database
	 * @return 1 se l'operazione di registrazione è stata effettuata correttamente, un numero negativo che indica la tipologia di errore altrimenti
	 * @pre toWrite != null
	 */
	private int registraAccount(Account toWrite){
		return 0;
	}
}
