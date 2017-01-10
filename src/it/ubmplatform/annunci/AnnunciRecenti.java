package it.ubmplatform.annunci;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.ubmplatform.factory.*;

/**
 * Servlet implementation class AnnunciRecenti
 */
@WebServlet("/AnnunciRecenti")
public class AnnunciRecenti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractFactory factory=new ManagerFactory();
		AnnuncioInterface manager=factory.createAnnuncioManager();
		ArrayList <Annuncio> lista = manager.queryAnnunciRecenti();
		if(lista==null || lista.size()==0){
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return;
		}
		request.setAttribute("lista", lista);
		request.getRequestDispatcher("annunciRecenti.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
