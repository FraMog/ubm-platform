package it.ubmplatform.amministrazione;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;

/**
 * La servlet che si occupa di gestire l'invalidazione di un utente dal sistema per un periodo di tempo stabilito dall'amministratore
 */
@WebServlet("/InvalidaAccountServlet")
public class InvalidaAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean valoreFeedback=true;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("trovata"+ request.getParameter("email"));
		System.out.println("trovata"+ request.getParameter("eliminaFeedback"));
		String email=request.getParameter("email");
		String decisione=request.getParameter("eliminaFeedback");
		boolean invio=sendMail(email);
		if(invio){
			boolean valoreInvalida=invalidaAccount(email);
			
		   	if(decisione.equals("true")){
				boolean valoreFeedback=cancellaFeedback(email);
			}
		   	if(valoreInvalida && valoreFeedback){
	    		request.removeAttribute("email");
	    		
			} 
			else{
				throw new OperationFailedException("L'invalidazione dell'account non ha avuto successo, riprova più tardi");
			}
		}
		else{
			response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("false");  
		}
		
	   	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	/**
	 * Metodo che si occupa di invalidare un account, smistando la richiesta all'{@link AmministrazioneManager} per la query adatta
	 * @param email L'email dell'account da invalidare
	 * @return Un booleano che indica se il metodo è andato a buon fine
	 * @pre email != null
	 */
	private boolean invalidaAccount(String email){
		AbstractFactory factory = new ManagerFactory();
		AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryInvalidaAccount(email);
	}
	
	private boolean cancellaFeedback(String email){
		AbstractFactory factory = new ManagerFactory();
		AmministrazioneInterface managerAmministrazione = factory.createAmministrazioneManager();
		return managerAmministrazione.queryCancellaFeedback(email);
	}
	
	private boolean sendMail(String email){
		String to = email;
		System.out.println(to);
		String from = "ubmplatform@gmail.com";
		String host = "smtp.gmail.com";
		String subject ="Sei stato Invalidato da UBM-Platform";
		String messageText ="Sei stato invalidato dalla piattaforma UBM-Platform in seguito al tuo comportamento inadatto riguardo le tue publicazioni sulla piattaforma. "+
		"Potrai riaccedere ad essa soltanto tra 7 giorni.";
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
				return true;
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
