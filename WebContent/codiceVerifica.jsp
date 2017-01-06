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
	<script type="text/javascript" src="javascript/email/empty_pin.js"></script>
	<script type="text/javascript" src="javascript/email/check.js"></script>

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
       <%String codiceInviato=(String) request.getAttribute("codice");
      	 String email=(String) request.getAttribute("email");
      	 String password=(String) request.getAttribute("password");%>
      	 
        <form  action="javascript:confrontoCodice()">
       		 <input type="text" name="codiceInserito" id="codiceInserito" required="required" pattern="<%=codiceInviato%>" title="INSERISCI IL CODICE ESATTO."/>  
       		  <input type="hidden" name="email" id="email" value="<%=email%>"/> 
       		   <input type="hidden" name="password" id="password" value="<%=password%>"/> 
       		  <input type="submit" class="btn btn-primary" value="invia"/>
		</form>           
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>

  </body>
</html>