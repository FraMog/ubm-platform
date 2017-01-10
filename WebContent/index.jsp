<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String request_account = (String)request.getAttribute("accountNonTrovato");
	String request_invalidato = (String)request.getAttribute("accountInvalidato");
 	String request_bannato = (String)request.getAttribute("accountBannato");
 	String request_data = (String)request.getAttribute("dataInvalidazioneNonTrovata");
 	String request_giorni = (String)request.getAttribute("giorniAttesa");
 	String request_email = (String)request.getAttribute("emailInviata");
%>
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
    <script type="text/javascript">
    onload = function()
    {
    	var request_account_obj= '<%=request_account%>';
    	var request_invalidato_obj= '<%=request_invalidato%>';
    	var request_bannato_obj= '<%=request_bannato%>';
    	var request_data_obj= '<%=request_data%>';
     	var request_giorni_obj= '<%=request_giorni%>';
     	var request_email_obj= '<%=request_email%>';
    	
    	if (request_account_obj=="true")
    	{
    		alert ("Account non trovato. E-mail e/o password errate.");
    		request.removeAttribute("accountNonTrovato");
    	}
    	
    	if (request_invalidato_obj=="true")
    	{
    		alert ("Il tuo account è stato invalidato per una settimana. Riprova ad accedere tra "+ request_giorni_obj +" giorno/i.");
    		request.removeAttribute("accountInvalidato");
    		request.removeAttribute("giorniAttesa");
    	}
    	
    	if (request_bannato_obj=="true")
    	{
    		alert ("Il tuo account è stato bannato dall'ammministratore: non puoi accedere alla piattaforma.");
    		request.removeAttribute("accountBannato");
    	}
    	
    	if (request_data_obj=="true")
    	{
    		alert ("Il tuo account è stato invalidato dall'ammministratore, ma c'è un problema nel recupero della data. Riprova ad accedere alla piattaforma.");
    		request.removeAttribute("dataInvalidazioneNonTrovata");
    	}
    	
    	if (request_email_obj=="true")
    	{
    		alert ("Ti abbiamo inviato una e-mail contenente la password per effettuare l'accesso alla piattaforma. Riprova ad autenticarti.");
    		request.removeAttribute("emailInviata");
    	}  	
    	
    }
    </script>
  </head>
  <body>
  	<%if(session.getAttribute("user")==null) {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} else if (session.getAttribute("user").equals("admin")) {%>
	<%@ include file="includes/navbarAdmin.jsp" %>
	<%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
    <%@ include file="includes/sideBar.jsp" %>
    <section class="col-sm-10" id="section">
      <div class="row">
        <img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG" alt="UBM Platform"/>
        <div class="col-sm-10">
          <h1>Benvenuto <%if (session.getAttribute("name")!=null)%><%=session.getAttribute("name")%></h1>
          <%if (session.getAttribute("user")==null)%><h3>Iscriviti subito per poter acquistare e vendere materiale universitario</h3>
        </div>
      </div>
      <div id="cont_ordine" class="container-fluid">
        <form class="col-sm-8 form-inline" action="#" method="get">
          <div class="form-group">
            <label class="control-label" for="ordine">Ordina per </label>
            <select id="ordine" class="form-control" name="ordine">
            	<option value="data">Più recenti</option>
              <option value="prezzo">Prezzo migliore</option>
            </select>
          </div>
        </form>
        <%if(session.getAttribute("user")!=null && ((String)session.getAttribute("user")).equalsIgnoreCase("utente")){%>
        <a href="inserisciAnnuncio.jsp" class="btn btn-info col-sm-4">Pubblica ora il tuo annuncio</a>
        <%}%>
      </div>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
          <img src="img/annunci/libro.jpg" alt="libro" class="img-responsive col-sm-2">
          <div class="col-sm-8">
            <h4><a href="#">Object oriented software engineering</a></h4>
            <p>Libro utilizzato per il corso di ingegneria del software tenuto dalla professoresa Ferrucci. Tenuto in ottimo stato e non sottolineato</p>
          </div>
          <div class="col-sm-2 pull-right">
            <h4>Prezzo: 15€</h4>
            <p>Data pubblicazione: 02/12/2016</p>
          </div>
        </div>
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
