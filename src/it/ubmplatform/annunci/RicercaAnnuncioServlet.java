package it.ubmplatform.annunci;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.BadResearchException;
import it.ubmplatform.factory.*;

/**
 * Servlet che si occupa di gestire la ricerca di un annuncio
 */

@WebServlet("/RicercaAnnuncio")
public class RicercaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RicercaAnnuncioServlet() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Metodo GET non supportato");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger= Logger.getLogger("Logger");
		logger.setLevel(Level.INFO);
		
		String titolo= request.getParameter("titolo");
		logger.info("titolo=" + titolo);
		String facolta= request.getParameter("facolta");
		logger.info("facolta=" + facolta);
		String categoria= request.getParameter("categoria");
		logger.info("categoria=" + categoria);
		String ordine= request.getParameter("ordine");
		logger.info("ordine=" + ordine);
		
		
		try {
			ricercaAnnunci(titolo, facolta, categoria, ordine);
		} catch (BadResearchException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Il metodo che si occupa di smistare la richiesta all'{@link AnnuncioManager} per la query di ricerca annuncio
	 * Se il nome è null, viene cercato per facolta, viceversa se la facolta è null viene ricercato per nome
	 * @param nome Il nome dell'annuncio
	 * @param facolta La facolta in cui ricercare l'annuncio
	 * @param orderBy L'ordine in cui visualizzare gli annunci(prezzo, data)
	 * @return La lista degli annunci se presenti, null altrimenti
	 * @throws BadResearchException 
	 * @pre (nome != null OR facolta != null) AND orderBy != null
	 */
	private ArrayList<Annuncio> ricercaAnnunci(String titolo, String facolta, String categoria, String orderBy) throws BadResearchException{
		
		AbstractFactory factory= new ManagerFactory();
		AnnuncioInterface annuncioManager= factory.createAnnuncioManager();
		annuncioManager.queryRicercaAnnuncio(titolo, facolta, categoria, orderBy);
		
		
		
		
		return null;//da cambiare
	}
}
