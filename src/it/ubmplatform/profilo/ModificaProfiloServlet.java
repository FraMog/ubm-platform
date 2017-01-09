package it.ubmplatform.profilo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.ubmplatform.eccezioni.BadModificaException;
import it.ubmplatform.eccezioni.BadOldPasswordException;
import it.ubmplatform.eccezioni.FileUploadException;
import it.ubmplatform.eccezioni.ModificaProfiloException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;
import it.ubmplatform.profilo.Profilo;
import it.ubmplatform.profilo.ProfiloInterface;

/**
 * Servlet che si occupa di gestire la modifica di un profilo
 */
@WebServlet("/ModificaProfiloServlet")
public class ModificaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ModificaProfiloServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	throw new ServletException("GET request not accepted");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String img=null;
		try{
			img=verificaFile(request);
		} catch(FileUploadException e){
			img = "img/default_profile.PNG";
		}
		String name=request.getParameter("nome");
		String surname=request.getParameter("cognome");
		String email=request.getParameter("emailToShow");
		String phone=request.getParameter("telefono");
		java.util.Date date;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")); //recupero e cast data
		} catch (ParseException e) {
			date=null;
		}
		String interest=request.getParameter("interessi");
		String residence=request.getParameter("residenza");
		System.out.println("campi: " + name + " " + surname + " " + phone +  " " + interest );
		
		String oldP = request.getParameter("oldPassword");
		String newP = request.getParameter("newPassword");
		String checkP = request.getParameter("checkPassword");
		
		boolean updatePassword = false;
		
		if(oldP != null && oldP != "" && newP != null && newP != "" && checkP != null && checkP.equals(newP))
			updatePassword = true;
		
		
		try{
			Profilo toUpdate = new Profilo(email, name, surname, residence, phone, interest, img, date); //eseguo il salvataggio
			if(updatePassword){
				try{
					modificaProfiloPassword(toUpdate, oldP, newP);
				} catch(Throwable e){
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "impossibile completare la modifica");
					return;
				}
				
			}
			else{
				try{
					modificaProfilo(toUpdate);
				}catch (Throwable e){
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "impossibile completare la modifica");
					return;
				}
			}
			if(img!=null)
				saveFile(request); //se il file � stato inserito lo carico
			request.getSession().setAttribute("user", email);
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("emailToShow", email);
			
			
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			return;
		}
		response.sendRedirect("VisualizzaProfiloServlet?emailToShow="+email);
	}
	
	private String verificaFile(HttpServletRequest request) throws IOException, ServletException{
		
		 Part filePart;
		try {
			filePart = request.getPart("img");
		} catch (Exception e){
			filePart = null;
		}
		if(filePart==null || "".equals(filePart.getSubmittedFileName().trim())) //verifica se l'immagine � stata inserita
			return null; //se non � stata inserita restituisco null
		String ext=filePart.getContentType();
		if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")||ext.equals("image/jpg"))) //verifica sull'estensione del file
			throw new FileUploadException("L'estensione del file non � riconosciuta dal server");
		if(filePart.getSize()>10*1024*1024) //verifica dimensione
			throw new FileUploadException("Le dimensioni del file superano i 10MB");
		path = this.getServletContext().getRealPath("")+"img"+File.separator+"profilo"; //path in cui salvare l'immagine
		fileName = request.getParameter("email")+"_"+filePart.getSubmittedFileName(); //nome file da salvare
		return fileName; //restituisco il nome del file
	}

	private void saveFile(HttpServletRequest request) throws ServletException, IOException, FileNotFoundException {
		final Part filePart = request.getPart("img");
		OutputStream out = null;
		InputStream filecontent = null;

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName)); //apro lo stream sul file al percorso stabilito
			filecontent = filePart.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) { //copio il file inserito nel file creato
				out.write(bytes, 0, read);
			}
		} 
		finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
		}
	}
	/**
	 * Metodo che si occupa di richiedere la modifica del profilo al {@link ProfiloManager}
	 * @param toUpdate Il profilo modificato
	 * @return 1 se l'operazione di modifica � andata a buon fine, un numero negativo che indica l'errore altrimenti
	 * @throws SQLException 
	 * @throws BadModificaException 
	 * @pre changed != null
	 * @post {@literal @}pre.RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != RicercaProfiloServlet.ricercaProfilo(changed.getEmail())
	 * @invariant RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != null
	 */
	private boolean modificaProfilo(Profilo toUpdate) throws SQLException, BadModificaException{
		AbstractFactory f = new ManagerFactory();
		ProfiloInterface manager = f.createProfiloManager();
		return manager.queryModificaProfilo(toUpdate);
	}
	/**
	 * Metodo che si occupa di richiedere la modifica del profilo (compresa la password) al {@link ProfiloManager}
	 * @param toUpdate Il profilo modificato 
	 * @param oldPassword la vecchia password, per effettuare un controllo con quella presente nel database
	 * @param newPassword la nuova password da inserire
	 * @return 1 se l'operazione di modifica � andata a buon fine, un numero negativo che indica l'errore altrimenti
	 * @throws BadOldPasswordException 
	 * @throws SQLException 
	 * @throws BadModificaException 
	 * @pre changed != null 
	 * @post {@literal @}pre.RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != RicercaProfiloServlet.ricercaProfilo(changed.getEmail())
	 * @invariant RicercaProfiloServlet.ricercaProfilo(changed.getEmail) != null
	 */
	private boolean modificaProfiloPassword(Profilo toUpdate, String oldPassword, String newPassword) throws BadOldPasswordException, SQLException, BadModificaException{
		AbstractFactory f = new ManagerFactory();
		ProfiloInterface manager = f.createProfiloManager();
		return manager.queryModificaProfiloPassword(toUpdate, oldPassword, newPassword);
	}
	
	private String path, fileName;



}
