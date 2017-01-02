<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="it.ubmplatform.annunci.Annuncio" %>

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
    <script  src="http://code.jquery.com/ui/1.12.0-rc.2/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="javascript/amministrazione/rimuoviAnnuncio.js"></script>
  </head>
  <body>
    <%if(session.getAttribute("user")!=null) {%>
    <%@ include file="includes/navbarLoggato.jsp" %>
    <%} else if(session.getAttribute("admin")!=null){%>
    	<%@ include file="includes/navbarAdmin.jsp" %>
    <%} else {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} %>
    
    <%@ include file="includes/sideBar.jsp" %>
    
    <%Annuncio annuncioDettagliato =(Annuncio) request.getAttribute("annuncioDettagliato"); %>
    <section class="col-sm-10" id="section">
      <div class="col-sm-8 panel panel-default">
      	<div class="panel-body">
			<h3 style="margin-bottom: 20px"><%=annuncioDettagliato.getTitolo()%></h3>
			<div class="row"><h4 class="col-xs-4">Categoria:</h4><h4 class="col-xs-3"><%=annuncioDettagliato.getCategoria()%></h4></div>
			<div class="row"><h4 class="col-xs-4">Facoltà:</h4><h4 class="col-xs-3"><%=annuncioDettagliato.getFacolta()%></h4></div>
			<div class="row"><h4 class="col-xs-4">Prezzo:</h4><h4 class="col-xs-3"><%=annuncioDettagliato.getPrezzo()%>€</h4></div>
			
			<%if (annuncioDettagliato.getCondizioni()!=null){ %>
			<div class="row"><h4 class="col-xs-4">Condizioni prodotto:</h4><h4 class="col-xs-3">annuncioDettagliato.getCondizioni()</h4></div>
			<% }%>
			
			<!--Solo libro -->
			<%if (annuncioDettagliato.getIsbn()!=null){ %>
			<div class="row"><h4 class="col-xs-4">ISBN:</h4><h4 class="col-xs-3">annuncioDettagliato.getIsbn()</h4></div>
			<% }%>
			
			<%if (annuncioDettagliato.getAutoreLibro()!=null){ %>
			<div class="row"><h4 class="col-xs-4">Autore:</h4><h4 class="col-xs-3">annuncioDettagliato.getAutoreLibro()</h4></div>
			<% }%>
			
			<%if (annuncioDettagliato.getEdizione()!=0){ %>
			<div class="row"><h4 class="col-xs-4">Edizione:</h4><h4 class="col-xs-3">annuncioDettagliato.getEdizione()</h4></div>
			<% }%>
			
            <!--  solo appunti -->
			<%if (annuncioDettagliato.getMateria()!=null){ %>
			<div class="row"><h4 class="col-xs-4">Materia:</h4><h4 class="col-xs-3">annuncioDettagliato.getMateria()</h4></div>
			<% }%>
			
				
		</div>
      </div>
      <% if (session.getAttribute("admin")!=null || session.getAttribute("user")!=null){ %>
      <div class="col-sm-4">
	      <img id="logo_ubm" class="img-responsive" src="<%=annuncioDettagliato.getFoto()%>" alt="<%=annuncioDettagliato.getTitolo()%>" style="max-width:300px"/>
	      <h3 class="">Pubblicato da: <a href="#" class="btn btn-info"><%=annuncioDettagliato.getEmail()%></a></h3>
	      <h4>Il: <b><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(annuncioDettagliato.getDataPubblicazione()).substring(0,10)%></b></h4>
      </div>
      <%} else {%>
      <div class="col-sm-4">
      <h4>Pubblicato Il: <b><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(annuncioDettagliato.getDataPubblicazione()).substring(0,10)%></b></h4>    
       </div>
      <%} %>
        <% if (session.getAttribute("admin")!=null || (session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail()))) {%>
		<button class="btn btn-danger btn-lg" type="button"  data-toggle="modal" data-target="#rimuoviModal">Rimuovi</button>
		<!-- Popup in caso di tentata rimozione -->
		<div id="rimuoviModal" class="modal fade" role="dialog">
			<div class="modal-dialog">	
		     	<!-- Modal content-->
		     	<div class="modal-content">
		       		<div class="modal-header">
		         		<button type="button" class="close" data-dismiss="modal">&times;</button>
		         		<h4 class="modal-title">Sei sicuro?</h4>
		      		</div>
			       	<div class="modal-footer">
			        	<button type="button" class="btn btn-success" data-dismiss="modal">Annulla</button>
			         	<button onclick="rimuovi(<%= annuncioDettagliato.getId()%>)" class="btn btn-danger">Rimuovi</button>
			       	</div>
		    	</div>	
			</div>
		</div>
		<div id="esitoModal" class="modal fade" role="dialog">
			<div class="modal-dialog">	
		     	<!-- Modal content-->
		     	<div class="modal-content">
		       		<div class="modal-header">
		         		<h4 id="title" class="modal-title"></h4>
		      		</div>
		      		<div class="modal-body">
	          			<p id="text"></p>
        			</div>
			       	<div class="modal-footer">
			         	<button id="ok" class="btn btn-info">OK</button>
			       	</div>
		    	</div>	
			</div>
		</div>
	<%} %>	

    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
