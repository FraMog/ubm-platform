package it.ubmplatform.annunci;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.management.InvalidAttributeValueException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.ubmplatform.eccezioni.BadInputAnnuncioException;
import it.ubmplatform.eccezioni.FileUploadException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che si occupa della gestione della modifica di un annuncio
 */
@WebServlet("/ModificaAnnuncioServlet")
@MultipartConfig
public class ModificaAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path, fileName;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     throw new ServletException("Metodo get non supportato");
		
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("annuncioID"));
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String foto= null;
		String email = request.getParameter("emailAutoreAnnuncio");
		String titolo = request.getParameter("titolo");
		String categoria = request.getParameter("categoria");
		if ("libro".equals(categoria.toLowerCase())) {
			categoria = "L";
		} else if ("appunti".equals(categoria.toLowerCase())){
			categoria = "A";
		}
		String facolta = request.getParameter("facolta");
		foto = verificaFile(request); //controlli sull'immagine
		String isbn = request.getParameter("isbn");
		String autoreLibro = request.getParameter("autoreLibro");
		int edizione=0;
		if(request.getParameter("edizione")!=null &&!request.getParameter("edizione").trim().equals(""))
			edizione=Integer.parseInt(request.getParameter("edizione"));
		String materia = request.getParameter("materia");
		String condizioni = request.getParameter("condizioni");
		String descrizione = request.getParameter("descrizione");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		Date dataPubblicazione = new Date(0);
		
		Annuncio toUpdate = new Annuncio(id, titolo, categoria, facolta, foto, isbn, autoreLibro, edizione, materia, condizioni, descrizione, prezzo, email, dataPubblicazione);
		try {
			Annuncio oldAnnuncio = OttieniAnnuncioDaModificare(toUpdate.getId());
			if(oldAnnuncio==null)
				response.sendError(500, "annuncio non trovato");
			   if(toUpdate.getTitolo()==null || toUpdate.getTitolo().trim().equals("")){
			    toUpdate.setTitolo(oldAnnuncio.getTitolo());
			   }
			   else if(toUpdate.getCategoria()==null || toUpdate.getCategoria().trim().equals("")) {
				   toUpdate.setCategoria(oldAnnuncio.getCategoria());
			   }
			   else if(toUpdate.getFacolta()==null || toUpdate.getFacolta().trim().equals("")){
				   toUpdate.setFacolta(oldAnnuncio.getFacolta());
			   }
			   else if(toUpdate.getFoto()==null || toUpdate.getFoto().equals("")){
				   toUpdate.setFoto(oldAnnuncio.getFoto());
			   }
			   else if(toUpdate.getDescrizione()== null|| toUpdate.getDescrizione().trim().equals("")){
				   toUpdate.setDescrizione(oldAnnuncio.getDescrizione());
			   }
			   else if(toUpdate.getPrezzo() == 0) {
				   toUpdate.setPrezzo(oldAnnuncio.getPrezzo());
			   }
			   modificaAnnuncio(toUpdate);
			   if(!toUpdate.getFoto().equals(oldAnnuncio.getFoto())){
				   removeOld(oldAnnuncio.getFoto()); //rimuovo la vecchia immagine
				   saveFile(request); //carico la nuova
			   }
			   response.sendRedirect("VisualizzaDettagliAnnuncio?annuncioID="+id);
			   
		} catch (InvalidAttributeValueException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Metodo per la verifica dell'immagine
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	private String verificaFile(HttpServletRequest request) throws IOException, ServletException{
		final Part filePart = request.getPart("foto");
		if(filePart==null || "".equals(filePart.getSubmittedFileName().trim())) //verifica se l'immagine è stata inserita
			return null; //se non è stata inserita restituisco null
		String ext=filePart.getContentType();
		if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")||ext.equals("image/jpg"))) //verifica sull'estensione del file
			throw new FileUploadException("L'estensione del file non è riconosciuta dal server");
		if(filePart.getSize()>10*1024*1024) //verifica dimensione
			throw new FileUploadException("Le dimensioni del file superano i 10MB");
		path = this.getServletContext().getRealPath("")+"img"+File.separator+"annunci"; //path in cui salvare l'immagine
		fileName = request.getSession().getAttribute("emailLoggato")+"_"+filePart.getSubmittedFileName(); //nome file da salvare
		return fileName; //restituisco il nome del file
	}
	
	/**
	 * Metodo per il salvataggio del file caricato in fase di inserimento
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void saveFile(HttpServletRequest request) throws ServletException, IOException, FileNotFoundException {
		final Part filePart = request.getPart("foto");
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
	
	private void removeOld(String toRemove){ //funzione che rimuove l'immagine vecchia 
		File deleteFile = new File(path + File.separator + toRemove) ;
		// check if the file is present or not
		if( deleteFile.exists())
			deleteFile.delete();
	}
	
	/**
	 * Metodo che si occupa di prendere l'annuncio da modificare
	 * @param idAnnuncio Annuncio esistente da modificare
	 * @return idAnnuncio Annuncio da modificare
	 * @throws BadInputAnnuncioException 
	 */
	private Annuncio OttieniAnnuncioDaModificare(int idAnnuncio) throws BadInputAnnuncioException{
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryOttieniAnnuncioDaModificare(idAnnuncio);
	}
	
	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} di modifica dell' annuncio
	 * @param changed Il nuovo annuncio da inserire
	 * @return 1 se l'operazione di modifica è andata a buon fine, un numero negativo che indica la tipologia di errore altrimenti
	 * @throws InvalidAttributeValueException 
	 * @throws SQLException 
	 * @pre changed != null
	 * @invariant VisualizzaDettagliAnnuncioServlet.visualizzaDettagliAnnuncio(changed.getId()) != null
	 */
	private boolean modificaAnnuncio(Annuncio toUpdate) throws InvalidAttributeValueException, SQLException{
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryModificaAnnuncio(toUpdate);
	}
}
