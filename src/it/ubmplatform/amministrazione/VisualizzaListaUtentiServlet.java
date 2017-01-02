package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.account.Account;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;
import it.ubmplatform.profilo.Profilo;

/**
 * Servlet che si occupa di ritornare la lista completa degli utenti del sistema
 */
@WebServlet("/VisualizzaListaUtentiServlet")
public class VisualizzaListaUtentiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log= Logger.getLogger("global");


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ArrayList<Account> lista=new ArrayList<Account>();
    	RequestDispatcher dispatcher= request.getRequestDispatcher("/listaIscrittiAdmin.jsp");
    	try{
    	lista=visualizzaListaUtenti();
    	request.setAttribute("lista",lista);
    	log.info("sono prima del forward!!!!!");
    	dispatcher.forward(request,response);
    	}
    	catch(Exception e){
    		log.info("doGet"+e);
        	request.setAttribute("lista",lista);
        	dispatcher.forward(request,response);
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AmministrazioneManager} per l'interrogazione al database
	 * @return La lista dei profili degli utenti registrati
	 */
	private ArrayList<Account> visualizzaListaUtenti(){
		ArrayList<Account> lista=new ArrayList<Account>();
		try {
			AbstractFactory factory = new ManagerFactory();
			AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		
			return managerAmministrazione.queryVisualizzaListaUtenti();
		} catch (Exception e) {
			log.info(" service error " + e);
			return lista;
		}
	}
}
