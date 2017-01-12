package it.ubmplatform.profilo;

import java.io.*;
import java.sql.SQLException;
import java.text.*;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import it.ubmplatform.eccezioni.FileUploadException;
import it.ubmplatform.factory.*;



/**
 * Servlet che si occupa di gestire il salvataggio di un nuovo profilo
 */
@WebServlet("/CreaProfilo")
@MultipartConfig
public class CreaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String path, fileName;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("GET request not accepted");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String img=null;
		try{
			img=verificaFile(request); //controlli sull'immagine
		} catch(FileUploadException e){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		}
		//recupero tutti i parametri
		String name=request.getParameter("nome");
		Pattern p=Pattern.compile("^[a-zA-Z ]{1,20}$");
		if(!p.matcher(name).find())
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Il campo nome non è stato compilato correttamente");
		String surname=request.getParameter("cognome");
		if(!p.matcher(surname).find())
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Il campo cognome non è stato compilato correttamente");
		String email=request.getParameter("email");
		if(!email.equals(request.getSession().getAttribute("email")))
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'email inserita non è valida");
		String phone=request.getParameter("tel").trim();
		if(!Pattern.compile("^[0-9]{0}$|^[0-9]{10}$").matcher(phone).find())
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Il campo telefono non è stato compilato correttamente");
		Date date;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data")); //recupero e cast data
		} catch (ParseException e) {
			date=null;
		}
		String interest=request.getParameter("interessi");
		String residence=request.getParameter("residenza");
		try{
			creaProfilo(new Profilo(email,name,surname,residence, phone,interest, img, date)); //eseguo il salvataggio
			if(img!=null)
				saveFile(request); //se il file è stato inserito lo carico
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/LoginServlet?username="+email+"&password="+request.getSession().getAttribute("password"));
			request.getSession().removeAttribute("password");
			dispatcher.forward(request, response);
		}
		catch(Exception e){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}
	
	private String verificaFile(HttpServletRequest request) throws IOException, ServletException{
		final Part filePart = request.getPart("img");
		if(filePart==null || "".equals(filePart.getSubmittedFileName().trim())) //verifica se l'immagine è stata inserita
			return null; //se non è stata inserita restituisco null
		String ext=filePart.getContentType();
		if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")||ext.equals("image/jpg"))) //verifica sull'estensione del file
			throw new FileUploadException("L'estensione del file non è riconosciuta dal server");
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
	 * Il metodo che si occupa di richiedere il salvataggio del nuovo profilo al {@link ProfiloManager}
	 * @param toInsert Il profilo da registrare
	 * @throws SQLException 
	 * @pre toInsert != null
	 * @pre RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) == null
	 * @post RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) != null
	 */
	private void creaProfilo(Profilo toInsert) throws SQLException{
		
		AbstractFactory factory = new ManagerFactory();
		ProfiloInterface managerProfilo = factory.createProfiloManager();
		managerProfilo.queryCreaProfilo(toInsert);
		
	}

}