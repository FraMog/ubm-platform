package it.ubmplatform.annunci;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa della gestione di visualizzazione dei dettagli di un annuncio
 */
@WebServlet("/VisualizzaDettagliAnnuncioServlet")
public class VisualizzaDettagliAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public VisualizzaDettagliAnnuncioServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di richiedere i dettagli di un annuncio all'{@link AnnuncioManager}
	 * @param idAnnuncio L'id dell'annuncio di cui visualizzare i dettagli
	 * @return L'annuncio richiesto se l'operazione è andata a buon fine, null altrimenti
	 * @pre idAnnuncio &gt; 0
	 */
	
	private Annuncio visualizzaDettagliAnnuncio(int idAnnuncio){
		return null;
	}
}
