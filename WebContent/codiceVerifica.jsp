<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="it">
  <head>
    <link rel="icon" href="img/favicon.ico"/>
    <title>UBM Platform</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1"/>
    <link rel="stylesheet" href="css/stile.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@ include file="includes/navbarNonLoggato.jsp" %>
    <%@ include file="includes/sideBar.jsp" %>
    <section class="col-sm-10" id="section">
      <div class="row">
        <img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG" alt="UBM Platform"/>
        <div class="col-sm-10">
          <h1>Conferma Registrazione</h1>
        </div>
      </div>
      <div id="confermareg" class="panel panel-default">
        <h4>Abbiamo inviato una e-mail al tuo indirizzo.</h4>
        <h4>Inserisci il codice di verifica</h4>
	<input type="text" name="cod" id="cod" onblur="empty_pin()" />  
	
      <button onclick="prova()">Invia</button>
           <script type="text/javascript" src="javascript/email/check.js"></script>
                      <script type="text/javascript" src="javascript/email/empty_pin.js"></script>
           
       	<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>	
        <%	int codice_locale;
        	int cod_sessione;
        	//if(codice_locale==cod_sessione)
        		//	out.print("CODICE RICEVUTO");
        	//else
        		//out.print("CODICE NON RICEVUTO");
        	try{
        	codice_locale=Integer.parseInt(request.getParameter("codice_locale")); 
        	cod_sessione=(int)session.getAttribute("codice");
        //DEBUG	out.print("codice-->"+codice_locale+"\t cod sess= "+cod_sessione); 
    		String email=(String) session.getAttribute("indirizzo_mail");
         	//DEBUG out.print("EMAIL = "+email);

        	 if (codice_locale==cod_sessione){
             	//out.print("CODICE OK\n");
             	//Query per cambiare stat
             	Class.forName("com.mysql.jdbc.Driver"); 
             	java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3060/ubmplatform",
             	"root","root"); 
             	Statement st= con.createStatement(); 
             	ResultSet rs; 
             	//out.println("EMAI PER QUERY: "+email);
             	int i=st.executeUpdate("UPDATE account SET Tipo='r' WHERE Email='"+email+"'");
				
             	//Redirect alla pagina di completamento	
             	response.sendRedirect("registrazioneEffettuata.jsp");
        	 }
        	}
        	finally{
        		
        	}
       
        %>
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>

  </body>
</html>