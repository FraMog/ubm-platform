package it.ubmplatform.annunci;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire l'inserimento di un annuncio
 */
@WebServlet("/InserisciAnnuncioServlet")
public class InserisciAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InserisciAnnuncioServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}
	
	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} per l'inserimento dell'annuncio al database
	 * @param toInsert L'annuncio da inserire
	 * @return 1 se l'operazione di inserimento è andata a buon fine, un numero negativo che indica la tipologia di errore altrimenti
	 * @pre toInsert != null
	 */
	private int inserisciAnnuncio(Annuncio toInsert){
		return 1;
	}
}
