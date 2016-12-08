package it.ubmplatform.annunci;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la ricerca di un annuncio
 */

@WebServlet("/RicercaAnnuncioServlet")
public class RicercaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RicercaAnnuncioServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}


	/**
	 * Il metodo che si occupa di smistare la richiesta all'{@link AnnuncioManager} per la query di ricerca annuncio
	 * Se il nome è null, viene cercato per facolta, viceversa se la facolta è null viene ricercato per nome
	 * @param nome Il nome dell'annuncio
	 * @param facolta La facolta in cui ricercare l'annuncio
	 * @param orderBy L'ordine in cui visualizzare gli annunci(prezzo, data)
	 * @return La lista degli annunci se presenti, null altrimenti
	 * @pre (nome != null OR facolta != null) AND orderBy != null
	 */
	private ArrayList<Annuncio> ricercaAnnunci(String nome, String facolta, String orderBy){
		return null;
	}
}
