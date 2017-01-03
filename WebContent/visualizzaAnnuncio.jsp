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
    <%session.removeAttribute("admin"); %>
    <%session.setAttribute("user","ciao@hotmail.it");%>
    <%Annuncio annuncioDettagliato =(Annuncio) request.getAttribute("annuncioDettagliato"); %>
    
    <%if (session.getAttribute("user")==null || !session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%> <%--Se l'utente che sta naigando non è loggato come l'utente che ha pubblicato l'annuncio può solo visualizzare e non modificare --%>
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
	<%}%>
			
            <!--  solo appunti -->
			<%if (annuncioDettagliato.getMateria()!=null){ %>
			<div class="row"><h4 class="col-xs-4">Materia:</h4><h4 class="col-xs-3">annuncioDettagliato.getMateria()</h4></div>
			<% }%>
			
		  </div>
      </div>
		<%} else if (session.getAttribute("user")!=null || session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%>	<%--Se l'utente che sta navigando è l'utente che ha pubblicato l'annuncio può modificare --%>
		 <div class="col-sm-6">
       	<div class="panel panel-default">
       		<div class="panel-body">
       			<form id="annuncio" class="form-horizontal" action="ModificaAnnuncioServlet" method="get" enctype="multipart/form-data" onsubmit="return validateForm()">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="titolo">Titolo: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="titolo" id="titolo" required="required" value="<%=annuncioDettagliato.getTitolo()%>"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2">Categoria: *</label>
		    			<div class="input-group col-sm-10" style="padding-left:25px">
		    				<div class="radio">
	                			<label><input type="radio" name="categoria" value="libro" <%if(annuncioDettagliato.getCategoria().equalsIgnoreCase("libro")){ %>checked="true"<%}%>>Libro</label>
	              			</div>
	              			<div class="radio">
	                			<label><input type="radio" name="categoria" value="appunti" <%if(annuncioDettagliato.getCategoria().equalsIgnoreCase("appunti")){%>checked="true"<%}%>>Appunti</label>
	              			</div>
		    			</div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2">Facoltà: *</label>
		    			<div class="form-group col-sm-3" style="padding-left:30px">
              			 	<select class="form-control" name="facolta">
               			 		<option value="informatica">Informatica</option>
               					<option value="biologia">Biologia</option>
               			 		<option value="matematica">Matematica</option>
              				</select>
              			</div>
            		</div>
	    			<div class="form-group">
       					<label class="control-label col-sm-2" for="foto">Immagine prodotto *</label>
	    				<div class="col-sm-6"><input class="form-control" type="file" name="foto" id="foto"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="isbn">ISBN: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="isbn" id="isbn"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="autore">Autore: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="autoreLibro" id="autoreLibro"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="edizione">Edizione: </label>
	    				<div class="col-sm-10"><input class="form-control" type="number" name="edizione" id="edizione"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="materia">Materia: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="materia" id="materia"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="condizioni">Condizioni: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="condizioni" id="condizioni"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="descrizione">Descrizione: </label>
	    				<div class="col-sm-10"><textarea class="form-control" name="descrizione" id="descrizione" form="profilo" rows="3" cols="50" maxlength="200"></textarea></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="prezzo">Prezzo: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="number" name="prezzo" id="prezzo"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="email">Email: <% request.getAttribute("email");%> *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="email" id="email"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="dataPubblicazione">DataPubblicazione: <% request.getAttribute("dataPubblicazione");%>*</label>
	    				<div class="col-sm-10"><input class="form-control" type="date" name="dataPubblicazione" id="dataPubblicazione"/></div>
	    			</div>
	    			
    				<input type="submit" class="btn btn-info center-block"/>
				</form>
       		</div>
       	</div>
      </div>		
	   
	   
	 <script type="text/javascript">
    function validateForm()
    {
    var a=document.forms["Form"]["titolo"].value;
    var b=document.forms["Form"]["categoria"].value;
    var c=document.forms["Form"]["facolta"].value;
    var d=document.forms["Form"]["foto"].value;
    var h=document.forms["Form"]["descrizione"].value;
    var e=document.forms["Form"]["prezzo"].value;
    var f=document.forms["Form"]["data"].value;
    var g=document.forms["Form"]["email"].value;
    if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="",h==null || d=="",e==null || e=="",f==null || f=="",g==null || g=="")
      {
    	alert("Completa tutti i campi");
        return false;
      }
    else{
    	alert("Request complete");
    	return true;
    }
    }
    </script> 
	   
      
      <%} %>
      
      <% if (session.getAttribute("admin")!=null || session.getAttribute("user")!=null){ %>
      <div class="col-sm-4">
	      <img id="logo_ubm" class="img-responsive" src="<%=annuncioDettagliato.getFoto()%>" alt="<%=annuncioDettagliato.getTitolo()%>" style="max-width:300px"/>
	      <h3 class="">Pubblicato da: <a href="#" class="btn btn-info"><%=annuncioDettagliato.getEmail()%></a></h3>
	      <h4>Il: <b><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(annuncioDettagliato.getDataPubblicazione()).substring(0,10)%></b></h4>
      </div>
      <%} else {%>
      <div class="col-sm-4">
       <img id="logo_ubm" class="img-responsive" src="<%=annuncioDettagliato.getFoto()%>" alt="<%=annuncioDettagliato.getTitolo()%>" style="max-width:300px"/>
      <h4>Pubblicato Il: <b><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(annuncioDettagliato.getDataPubblicazione()).substring(0,10)%></b></h4>    
       </div>
      <%} %>
        <%if (session.getAttribute("admin")!=null) {%>
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
