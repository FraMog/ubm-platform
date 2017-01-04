package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * Servlet che gestisce la cancellazione di utente da parte di un amministratore
 */
@WebServlet("/CancellaAccountServlet") 
public class CancellaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean valoreFeedback=true;
	Logger logger= Logger.getLogger("global");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("trovata"+ request.getParameter("email"));
		System.out.println("trovata"+ request.getParameter("eliminaFeedback"));
		String email=request.getParameter("email");
		String decisione=request.getParameter("eliminaFeedback");
		
		boolean valoreCancella=cancellaAccount(email);
		if(decisione.equals("true")){
			boolean valoreFeedback=cancellaFeedback(email);
		}
	   	if(valoreCancella && valoreFeedback){
	   		sendMail(email);
    		request.removeAttribute("email");
    		response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("Success Data");  
		} 
		else{
			throw new OperationFailedException("La cancellazione dell'account non ha avuto successo, riprova più tardi");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Il metodo che si occupa di effettuare la cancellazione dell'utente, smistandolo all'{@link AmministrazioneManager}
	 * @param email L'email dell'utente da cancellare
	 * @return Un booleano che indica se l'operazione è andata a buon fine
	 * @pre email != null
	 */
	private boolean cancellaAccount(String email){
		try {
			AbstractFactory factory = new ManagerFactory();
			AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryCancellaAccount(email);
		} catch (Exception e) {
			logger.info(" service error " + e);
			return true;
		}
	}
	
	private boolean cancellaFeedback(String email){
		AbstractFactory factory = new ManagerFactory();
		AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryCancellaFeedback(email);
	}

	private void sendMail(String email){
		String to = email;
		System.out.println(to);
		String from = "ubmplatform@gmail.com";
		String host = "smtp.gmail.com";
		String subject ="Sei stato Bannato da UBM-Platform";
		String messageText ="Sei stato bannato definitivamente dalla piattaforma UBM-Platform in seguito al tuo comportamento inadatto riguardo le tue publicazioni sulla piattaforma. "+
		"Non ti è più possibile accedere alla suddetta piattaforma.";
		final String username="ubmplatform@gmail.com";
		final String password="UbmPlatform2016";
		
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
 
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
