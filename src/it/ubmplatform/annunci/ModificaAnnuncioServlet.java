package it.ubmplatform.annunci;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che si occupa della gestione della modifica di un annuncio
 */
@WebServlet("/ModificaAnnuncioServlet")
public class ModificaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ModificaAnnuncioServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	throw new ServletException("GET request not accepted");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} di modifica dell' annuncio
	 * @param changed Il nuovo annuncio da inserire
	 * @return 1 se l'operazione di modifica è andata a buon fine, un numero negativo che indica la tipologia di errore altrimenti
	 * @pre changed != null
	 * @invariant VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(changed.getId()) != null
	 */
	private boolean modificaAnnuncio(Annuncio changed){
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryModificaAnnuncio(changed);
	}
}
