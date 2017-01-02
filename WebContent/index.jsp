<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String session_account = (String)session.getAttribute("accountNonTrovato"); 
	String session_data = (String)session.getAttribute("dataInvalidazioneNonTrovata");
 	String session_bannato = (String)session.getAttribute("accountBannato"); 
 	String session_logout = (String)session.getAttribute("logoutErrato"); 
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
    	var session_account_obj= '<%=session_account%>';
    	var session_data_obj= '<%=session_data%>';
    	var session_bannato_obj= '<%=session_bannato%>';
    	var session_logout_obj= '<%=session_logout%>';
    	
    	if (session_account_obj.equals("true"))
    	{
    		alert ("Account non trovato. E-mail e/o password errate.");
    		session.setAttribute("accountNonTrovato", null);
    	}
    	
    	if (session_data_obj).equals("true"))
    	{
    		alert ("Il tuo account è stato invalidato, ma c'è un problema nel recupero della data di invalidazione. Riprova per verfiicare se è possibile l'accesso alla piattaforma.");
    		session.setAttribute("dataInvalidazioneNonTrovata", null);
    	}
    	
    	if (session_bannato_obj.equals("true"))
    	{
    		alert ("Il tuo account è stato bannato dall'ammministratore: non puoi accedere alla piattaforma.");
    		session.setAttribute("accountBannato", null);
    	}
    	
    	if (session_logout_obj.equals("true"))
    	{
    		alert ("Impossibile effettuare il logout. Riprovare tra qualche minuto.");
    		session.setAttribute("logoutErrato", null);
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
        <a href="inserisciAnnuncio.jsp" class="btn btn-info col-sm-4">Pubblica ora il tuo annuncio</a>
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
