package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che si occupa di gestire la rimozione di un annuncio non adatto alle regole del sistema, da parte dell'amministratore
 */
@WebServlet("/RimuoviAnnuncioNonInerente")
public class RimuoviAnnuncioNonInerenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=-1;
		response.setContentType("text/plain;charset=UTF-8");
		if(!request.getSession().getAttribute("user").equals("admin")){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write("Non sei autorizzato ad eseguire questa operazione");
			return;
		}
		try{
			id = Integer.parseInt(request.getParameter("id")); //prendo l'id dell'annuncio dalla request
			try{
				rimuoviAnnuncioNonInerente(id); //effettuo la rimozione
				response.setStatus(HttpServletResponse.SC_OK); //se non sono state lanciate eccezioni restituisco stato ok
				
			} catch(Exception e){
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //se sono state lanciate eccezioni imposto lo stato di errore
				response.getWriter().write(e.getMessage()); //scrivo il messaggio di errore
			}
		} catch(Exception e){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //se il parametro non è un numero imposto lo stato di errore
			response.getWriter().write("Il parametro richiesto manca o ha un formato non atteso"); //invio il messaggio di errore
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	/**
	 * Metodo che si occupa di smistare la richiesta all'{@link AmministrazioneManager} per la query adatta
	 * @param idAnnuncio L'id dell'annuncio da rimuovere
	 * @throws SQLException 
	 * @pre idAnnuncio &gt; 0
	 * @pre VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) != null
	 * @post VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(idAnnuncio) == null
	 */
	
	private void rimuoviAnnuncioNonInerente(int idAnnuncio) throws Exception{
		
		AbstractFactory factory = new ManagerFactory();
		AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		if(!managerAmministrazione.queryRimuoviAnnuncioNonInerente(idAnnuncio)) //se la rimozione non lancia eccezioni ma restituisce false
			throw new OperationFailedException("Cancellazione non riuscita, riprova più tardi"); //lancio un eccezione
	}

}
