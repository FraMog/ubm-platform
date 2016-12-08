package it.ubmplatform.amministrazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la rimozione di un annuncio non adatto alle regole del sistema, da parte dell'amministratore
 */
@WebServlet("/RimuoviAnnuncioNonInerenteServlet")
public class RimuoviAnnuncioNonInerenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public RimuoviAnnuncioNonInerenteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//---
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta all'{@link AmministrazioneManager} per la query adatta
	 * @param idAnnuncio L'id dell'annuncio da rimuovere
	 * @return Un booleano che indica se l'operazione è andata a buon fine o meno
	 * @pre idAnnuncio &gt; 0
	 * @pre VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) != null
	 * @post VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) == null
	 */
	private boolean rimuoviAnnuncioNonInerente(int idAnnuncio){
		return false;
	}

}
