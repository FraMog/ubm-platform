package it.ubmplatform.profilo;

import java.io.*;
import java.text.*;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import it.ubmplatform.eccezioni.FileUploadException;
import it.ubmplatform.eccezioni.InsertFailedException;
import it.ubmplatform.factory.*;



/**
 * Servlet che si occupa di gestire il salvataggio di un nuovo profilo
 */
@WebServlet("/CreaProfilo")
@MultipartConfig
public class CreaProfiloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String img;
		try{
			img=saveFile(request,response);
		} catch(FileNotFoundException e){
			img=null;
		}
		String name=request.getParameter("nome");
		String surname=request.getParameter("cognome");
		String email=request.getParameter("email");
		String phone=request.getParameter("tel");
		Date date;
		try {
			date=new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("data"));
		} catch (ParseException e) {
			date=null;
		}
		String interest=request.getParameter("interessi");
		String residence=request.getParameter("residenza");
		if(creaProfilo(new Profilo(email,name,surname,residence, phone,interest, img, date))){
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("name", name);
			response.sendRedirect("index.jsp");
		}
		else{
			throw new InsertFailedException("La creazione del profilo ha riscontrato dei problemi, riprova più tardi");
		}

	}

	protected String saveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		// Create path components to save the file
		final Part filePart = request.getPart("img");
		if(filePart==null || "".equals(filePart.getSubmittedFileName().trim()))
			return null;
		String ext=filePart.getContentType();
		if(!(ext.equals("image/jpeg")||ext.equals("image/png")||ext.equals("image/gif")))
			throw new FileUploadException("L'estensione del file non è riconosciuta dal server");
		if(filePart.getSize()>10*1024*1024)
			throw new FileUploadException("Le dimensioni del file superano i 10MB");
		final String path = this.getServletContext().getRealPath("")+"img"+File.separator+"profilo";
		final String fileName = request.getParameter("email")+"_"+filePart.getSubmittedFileName();
		OutputStream out = null;
		InputStream filecontent = null;

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName));
			filecontent = filePart.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = filecontent.read(bytes)) != -1) {
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
		return path + File.separator + fileName;
	}

	/**
	 * Il metodo che si occupa di richiedere il salvataggio del nuovo profilo al {@link ProfiloManager}
	 * @param toInsert Il profilo da registrare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre toInsert != null
	 * @pre RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) == null
	 * @post RicercaProfiloServlet.ricercaProfilo(toInsert.getEmail) != null
	 */
	private boolean creaProfilo(Profilo toInsert){
		
		AbstractFactory factory = new ManagerFactory();
		ProfiloInterface managerProfilo = factory.createProfiloManager();
		return managerProfilo.queryCreaProfilo(toInsert);
		
	}

}