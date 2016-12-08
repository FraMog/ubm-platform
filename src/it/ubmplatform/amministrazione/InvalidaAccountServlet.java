package it.ubmplatform.amministrazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * La servlet che si occupa di gestire l'invalidazione di un utente dal sistema per un periodo di tempo stabilito dall'amministratore
 */
@WebServlet("/InvalidaAccountServlet")
public class InvalidaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public InvalidaAccountServlet() {
        super();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--
	}

	
	/**
	 * Metodo che si occupa di invalidare un account, smistando la richiesta all'{@link AmministrazioneManager} per la query adatta
	 * @param email L'email dell'account da invalidare
	 * @return Un booleano che indica se il metodo è andato a buon fine
	 * @pre email != null
	 */
	private boolean invalidaAccount(String email){
		return false;
	}
}
