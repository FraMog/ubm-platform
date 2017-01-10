package it.ubmplatform.registrazione;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.ubmplatform.account.Account;
import it.ubmplatform.eccezioni.OperationFailedException;
import it.ubmplatform.factory.AbstractFactory;
import it.ubmplatform.factory.ManagerFactory;
import it.ubmplatform.registrazione.RegistrazioneInterface;

@WebServlet("/controlloCodiceServlet")
public class ControlloCodiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger logger= Logger.getLogger("global");
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password =request.getParameter("password");
		Account acc =new Account(email, password);
		System.out.println("l'email è "+email);
		boolean valore = registraAccount(acc);
		if(valore==true){
			request.getSession().setAttribute("password", password);
    		response.setContentType("text/html;charset=UTF-8");
    		response.getWriter().write("true");
		} 
		else{
			throw new OperationFailedException("La registrazione dell'account non ha avuto successo, riprova più tardi");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	
	private boolean registraAccount(Account toWrite){
		try {
			AbstractFactory factory = new ManagerFactory();
			RegistrazioneInterface managerRegistrazione = factory.createRegistrazioneManager();
		return managerRegistrazione.queryRegistraAccount(toWrite);
		} catch (Exception e) {
			logger.info(" service error " + e);
			return true;
		}
	}
	
	
}