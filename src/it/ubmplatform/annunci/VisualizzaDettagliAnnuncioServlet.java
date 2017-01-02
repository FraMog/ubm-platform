package it.ubmplatform.annunci;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.BadAnnuncioIdException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

import java.util.logging.Logger;

/**
 * Servlet che si occupa della gestione di visualizzazione dei dettagli di un annuncio
 */
@WebServlet("/VisualizzaDettagliAnnuncio")
public class VisualizzaDettagliAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisualizzaDettagliAnnuncioServlet() {
		super();
	}

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger= Logger.getLogger("Logger");

		int idAnnuncio= Integer.parseInt(request.getParameter("annuncioID"));
		logger.info("annuncioID =" + idAnnuncio);


		Annuncio annuncioDettagliato;
		try {
			annuncioDettagliato = visualizzaDettagliAnnuncio(idAnnuncio);
			request.setAttribute("annuncioDettagliato", annuncioDettagliato);
			RequestDispatcher rd= request.getRequestDispatcher("visualizzaAnnuncio.jsp");
			rd.forward(request, response);
		} catch (BadAnnuncioIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}

	/**
	 * Metodo che si occupa di richiedere i dettagli di un annuncio all'{@link AnnuncioManager}
	 * @param idAnnuncio L'id dell'annuncio di cui visualizzare i dettagli
	 * @return L'annuncio richiesto se l'operazione è andata a buon fine, null altrimenti
	 * @throws BadAnnuncioIdException 
	 * @pre idAnnuncio &gt; 0
	 */

	private Annuncio visualizzaDettagliAnnuncio(int idAnnuncio) throws BadAnnuncioIdException{
		AbstractFactory factory= new ManagerFactory();
		AnnuncioInterface annuncioManager= factory.createAnnuncioManager();
		Annuncio annuncioDettagliato= annuncioManager.queryVisualizzaDettagliAnnuncio(idAnnuncio);
		return annuncioDettagliato;
	}
}
