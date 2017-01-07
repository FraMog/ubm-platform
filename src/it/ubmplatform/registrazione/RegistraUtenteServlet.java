package it.ubmplatform.registrazione;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;
import com.oracle.jrockit.jfr.RequestableEvent;

import it.ubmplatform.account.Account;
import it.ubmplatform.amministrazione.AmministrazioneInterface;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;
import it.ubmplatform.registrazione.*;

/**
 * La servlet che gestisce la registrazione al sistema. 
 * In particolare, preleva i dati in input, ne effettua la verifica e,
 * se i dati sono corretti, richiede l'inserimento dei dati nel database al RegistrazioneManager.
 */
@WebServlet("/RegistraUtenteServlet")
public class RegistraUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger= Logger.getLogger("global");
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//--------
		
		String password=request.getParameter("pass");
		String email=request.getParameter("email");
		System.out.println(email+"\t"+password);
		boolean query_flag=accountEsistente(email);
			if(query_flag==false){
				System.out.println("query ok");
				int codice=(int)(Math.random()* 999999);
	
				sendMailCodiceVerifica(email,codice);
				request.setAttribute("codice", String.valueOf(codice));
				request.setAttribute("email", email);
				request.setAttribute("password", password);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("codiceVerifica.jsp");
				dispatcher.forward(request, response);
				
			}
			else {
				System.out.println("FAILED ");
				RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
				request.setAttribute("esiste", true);
				dispatcher.forward(request, response);
			}
		
			
			
			
	}

	/**
	 * Il metodo che si occupa di smistare le richieste di inserimento nel database al {@link RegistrazioneManager}
	 * @param toWrite L'account da inserire nel database
	 * @return 1 se l'operazione di registrazione è stata effettuata correttamente, un numero negativo che indica la tipologia di errore altrimenti
	 * @pre toWrite != null
	 */
	private boolean accountEsistente(String email){
		try {
			AbstractFactory factory = new ManagerFactory();
			RegistrazioneInterface managerRegistrazione = factory.createRegistrazioneManager();
		return managerRegistrazione.queryAccountEsistente(email);
		} catch (Exception e) {
			logger.info(" service error " + e);
			return true;
		}
	}
	

	private void sendMailCodiceVerifica(String email, int codice){

		
		String to = email;
		System.out.println(to);
		String from = "ubmplatform@gmail.com";
		String host = "smtp.gmail.com";
		String subject ="Codice di verifica registrazione";
		String messageText ="Codice di verifica registrazione:"+ codice;
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
