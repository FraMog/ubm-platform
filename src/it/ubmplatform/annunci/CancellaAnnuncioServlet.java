package it.ubmplatform.annunci;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la cancellazione di un annuncio dal sistema
 */
@WebServlet("/CancellaAnnuncioServlet")
public class CancellaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CancellaAnnuncioServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}

	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} di cancellazione annuncio dal database
	 * @param idAnnuncio L'id dell'annuncio da cancellare
	 * @return Un booleano che indica se l'operazione di cancellazione è andata a buon fine
	 * @pre idAnnuncio &gt; 0
	 * @pre VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) != null
	 * @post VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) == null
	 */
	private boolean cancellaAnnuncio(int idAnnuncio){
		return false;
	}
}
