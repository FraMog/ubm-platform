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
    <script src="javascript/home/caricaAnnunci.js"></script>
    <script type="text/javascript">
    onload = function()
    {
    	var request_account_obj= '<%=request_account%>';
    	var request_invalidato_obj= '<%=request_invalidato%>';
    	var request_bannato_obj= '<%=request_bannato%>';
    	var request_data_obj= '<%=request_data%>';
     	var request_giorni_obj= '<%=request_giorni%>';
     	var request_email_obj= '<%=request_email%>';
    	
		if (request_account_obj == "true")
		{
			$('#nonTrovatoModal').modal('show'); //mostro il modal
			request.removeAttribute("accountNonTrovato");
			return false;
		}

		if (request_invalidato_obj == "true")
		{
			$('#invalidatoModal').modal('show'); //mostro il modal
			request.removeAttribute("accountInvalidato");
			return false;
		}

		if (request_bannato_obj == "true")
		{
			$('#bannatoModal').modal('show'); //mostro il modal
			request.removeAttribute("accountBannato");
			return false;
		}

		if (request_data_obj == "true")
		{
			$('#dataInvalidazioneNonTrovata').modal('show'); //mostro il modal
			request.removeAttribute("dataInvalidazioneNonTrovata");
			return false;
		}

		if (request_email_obj == "true")
		{
			$('#emailInviata').modal('show'); //mostro il modal
			request.removeAttribute("emailInviata");
			return false;
		}
    	
    }
    $(document).ready(caricaAnnunci());
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
        <%if(session.getAttribute("user")!=null && ((String)session.getAttribute("user")).equalsIgnoreCase("utente")){%>
        <a href="inserisciAnnuncio.jsp" class="btn btn-info pull-right">Pubblica ora il tuo annuncio</a>
        <%}%>
      </div>
      <div id="content" class="panel panel-default">
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>
   	<!-- MODAL NON TROVATO -->
	<div id="nonTrovatoModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Errore</h4>
				</div>
				<div class="modal-body">
					<p>Account non trovato. E-mail e/o password errate.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL INVALIDATO -->
	<div id="invalidatoModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Errore</h4>
				</div>
				<div class="modal-body">
					<p>Il tuo account è stato invalidato per una settimana. Riprova ad accedere tra <%=request_giorni%> giorno/i.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL BANNATO -->
	<div id="bannatoModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Errore</h4>
				</div>
				<div class="modal-body">
					<p>Il tuo account è stato bannato dall'ammministratore: non puoi accedere alla piattaforma.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL DATA INVALIDAZIONE NON TROVATA -->
	<div id="dataInvalidazioneNonTrovata" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Errore</h4>
				</div>
				<div class="modal-body">
					<p>Il tuo account è stato invalidato dall'ammministratore, ma c'è un problema nel recupero della data. Riprova ad accedere alla piattaforma.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>

	<!-- MODAL EMAIL INVIATA -->
	<div id="emailInviata" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Avviso</h4>
				</div>
				<div class="modal-body">
					<p>Ti abbiamo inviato una e-mail contenente la password per effettuare l'accesso alla piattaforma. Riprova ad autenticarti.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-success" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>
	<% request.removeAttribute("giorniAttesa"); %>
  </body>
</html>
