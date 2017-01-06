package it.ubmplatform.autenticazione;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.ubmplatform.autenticazione.AutenticazioneInterface;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce il recupero di password di un utente, a cui verrà inviata tramite e-mail
 */
@WebServlet("/RecuperaPasswordServlet")
public class RecuperaPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session; 
    
    public RecuperaPasswordServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		String email = request.getParameter("email");
		if (email.equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("recuperaPassword.jsp");
			rd.forward(request, response);
		}
		else
		{
			if (recuperaPassword(email)!=null)
			{
				String passwordRecuperata = recuperaPassword(email);
				String host = "smtp.gmail.com";
				String to = email;
				String from = "ubmplatform@gmail.com";
				String subject = "Recupero password per UBM-Platform"; 
				String messageText = "La password per accedere alla piattaforma di UBM-Platform è:\n"+ passwordRecuperata +"\n";
				final String username="ubmplatform@gmail.com";
				final String password="UbmPlatform2016";
				// Create some properties and get the default Session
					Properties props = new Properties();
					
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");
					Session sessionMail = Session.getInstance(props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });
			 
					try {
						Message message = new MimeMessage(sessionMail);
						sessionMail.setDebug(true);
						message.setFrom(new InternetAddress(username));
						message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(to));
						message.setSubject(subject);
						message.setText(messageText);
			 
						Transport.send(message);
						
						request.setAttribute("emailInviata", "true");
						RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
						rd.forward(request, response);

			 
			 
					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}
			}
			else
			{
				request.setAttribute("emailInviata", "false");
				RequestDispatcher rd = request.getRequestDispatcher("recuperaPassword.jsp");
				rd.forward(request, response);
			}
		}
	}
	
	/**
	 * Il metodo che si occupa di recuperare la password associata all'e-mail passata come parametro.
	 * @param email L'email dell'utente a cui recuperare la password, passato come parametro dall'utente
	 * @return password La password recuperata dal database.
	 * @pre email != null
	 */
	private String recuperaPassword(String email){
		AbstractFactory factory = new ManagerFactory();
		AutenticazioneInterface model = factory.createAutenticazioneManager();
		String password = model.queryRecuperaPassword(email);
		return password;
	}
	

}
