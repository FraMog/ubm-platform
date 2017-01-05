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
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.ubmplatform.eccezioni.FileUploadException;
import it.ubmplatform.eccezioni.InsertFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;


/**
 * Servlet che si occupa di gestire l'inserimento di un annuncio
 */
@WebServlet("/InserisciAnnuncioServlet")
@MultipartConfig
public class InserisciAnnuncioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path, fileName;

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 1;
		String foto, email = null;
		email="prova@ubm.it";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
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
		int edizione = Integer.parseInt(request.getParameter("edizione"));
		String materia = request.getParameter("materia");
		String condizioni = request.getParameter("condizioni");
		String descrizione = request.getParameter("descrizione");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		Date dataPubblicazione = new Date(0);
		System.out.println(dateFormat.format(dataPubblicazione));
		try {
			System.out.println(id);
			if(inserisciAnnuncio(new Annuncio(id, titolo, categoria, facolta, foto, isbn, autoreLibro, edizione, materia, condizioni, descrizione, prezzo, email, dataPubblicazione))){ //controllo se l'operazione è riuscita
				if(foto != null)
					saveFile(request); //se il file è stato inserito lo carico
				response.sendRedirect("VisualizzaDettagliAnnuncio?annuncioID="+id);
			}
			else{
				throw new InsertFailedException("L'inserimento dell'annuncio ha riscontrato dei problemi, riprova più tardi");
			}
		} catch (InvalidAttributeValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		fileName = request.getParameter("email")+"_"+filePart.getSubmittedFileName(); //nome file da salvare
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
	/**
	 * Metodo che si occupa di inoltrare la richiesta all'{@link AnnuncioManager} per l'inserimento dell'annuncio al database
	 * @param toInsert L'annuncio da inserire
	 * @return 1 se l'operazione di inserimento è andata a buon fine, un numero negativo che indica la tipologia di errore altrimenti
	 * @throws InvalidAttributeValueException 
	 * @throws SQLException 
	 * @pre toInsert != null
	 */
	private boolean inserisciAnnuncio(Annuncio toInsert) throws InvalidAttributeValueException, SQLException{
		AbstractFactory factory = new ManagerFactory();
		AnnuncioInterface managerAnnuncio = factory.createAnnuncioManager();
		return managerAnnuncio.queryInserisciAnnuncio(toInsert);
	}
}

