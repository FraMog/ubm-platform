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
    <script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
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
    
    <%if (session.getAttribute("user")==null || !session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%> <%--Se l'utente che sta naigando non è loggato come l'utente che ha pubblicato l'annuncio può solo visualizzare e non modificare --%>
    <section class="row col-sm-10" id="section">
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
		<%} else if (session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%>	<%--Se l'utente che sta navigando è l'utente che ha pubblicato l'annuncio può modificare --%>
		 <div class="col-sm-6">
       	<div class="panel panel-default">
       		<div class="panel-body">
       			<form id="annuncio" class="form-horizontal" action="ModificaAnnuncioServlet" method="post" enctype="multipart/form-data">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="titolo">Titolo: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="titolo" id="titolo" required="required" value="<%=annuncioDettagliato.getTitolo()%>" placeholder="Inserisci un titolo" pattern="[a-zA-Z0-9]{1,50}" title="Inserire una stringa alfanumerica di lunghezza 1-50"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2">Categoria: *</label>
		    			<div class="input-group col-sm-10" style="padding-left:25px">
		    				<div class="radio">
	                			<label><input type="radio" id="libroRadioButton" onclick="categoriaLibro()" name="categoria" value="libro" <%if(annuncioDettagliato.getCategoria().equalsIgnoreCase("libro")){ %>checked<%}%>>Libro</label>
	              			</div>
	              			<div class="radio">
	                			<label><input type="radio" id="appuntiRadioButton" onclick="categoriaAppunti()" name="categoria" value="appunti" <%if(annuncioDettagliato.getCategoria().equalsIgnoreCase("appunti")){%>checked<%}%>>Appunti</label>
	              			</div>
		    			</div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2">Facoltà: *</label>
		    			<div class="form-group col-sm-4" style="padding-left:30px">
              			 	<select class="form-control" name="facolta">
               			 		<option value="Informatica" <%if (annuncioDettagliato.getFacolta().equalsIgnoreCase("Informatica")){%> selected="selected"<%}%>>Informatica</option>
               					<option value="Biologia" <%if (annuncioDettagliato.getFacolta().equalsIgnoreCase("Biologia")){%> selected="selected"<%}%>>Biologia</option>
               			 		<option value="Matematica" <%if (annuncioDettagliato.getFacolta().equalsIgnoreCase("Matematica")){%> selected="selected"<%}%>>Matematica</option>
              				</select>
              			</div>
            		</div>
            		<div class="form-group">
	    				<label class="control-label col-sm-2" for="prezzo">Prezzo:*</label>
	    				<div class="col-sm-10"><input class="form-control" type="number" step="0.1" name="prezzo" id="prezzo" value="<%=annuncioDettagliato.getPrezzo()%>" placeholder="Inserisce il prezzo nel formato x,y o nel formato x" /></div>
	    			</div>
	    			<div class="form-group">
       					<label class="control-label col-sm-2" for="foto">Immagine prodotto *</label>
	    				<div class="col-sm-6"><input class="form-control" type="file" name="foto" id="foto" value="<%=annuncioDettagliato.getFoto()%>"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="descrizione">Descrizione:* </label>
	    				<div class="col-sm-10"><textarea class="form-control" name="descrizione" id="descrizione" form="annuncio" rows="3" cols="50" maxlength="100" placeholder="Inserisci una Descrizione" required="required"><%if(annuncioDettagliato.getDescrizione()!=null){%><%=annuncioDettagliato.getDescrizione()%><%}%></textarea></div>
	    			</div>
	    			
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="condizioni">Condizioni: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="condizioni" id="condizioni" <%if (annuncioDettagliato.getCondizioni()!=null){%>value="<%=annuncioDettagliato.getCondizioni()%>"<%}%> placeholder="Inserisci le condizioni del prodotto"/></div>
	    			</div>	   
	    			
	    		<div id="soloLibro"> <!-- Solo libro -->
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="isbn">ISBN: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="isbn" id="isbn" <%if (annuncioDettagliato.getIsbn()!=null){%>value="<%=annuncioDettagliato.getIsbn()%>" <%}%>placeholder="Inserisci un ISBN" pattern="[a-zA-Z0-9]{0,13}" title="Inserire una stringa alfanumerica di lunghezza 0-13"/></div>
	    			</div>

	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="autore">Autore: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="autoreLibro" id="autoreLibro" <%if (annuncioDettagliato.getAutoreLibro()!=null){%>value="<%=annuncioDettagliato.getAutoreLibro()%>"<%}%> placeholder="Inserisci un autore" pattern="[a-zA-Z0-9]{0,20}" title="Inserire una stringa alfanumerica di lunghezza 0-20"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="edizione">Edizione: </label>
	    				<div class="col-sm-10"><input class="form-control" type="number" name="edizione" id="edizione" <%if(annuncioDettagliato.getEdizione()!=0){%>value="<%=annuncioDettagliato.getEdizione()%>"<%}%> /></div>
	    			</div>
	    		</div>  
	    		 <div id="soloAppunti">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="materia">Materia: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="materia" id="materia" <%if (annuncioDettagliato.getMateria()!=null){%>value="<%=annuncioDettagliato.getMateria()%>"<%}%> placeholder="Inserisci una Materia" /></div>
	    			</div>
	    		  </div>	
	    			 			
	    			
    				<input type="submit" class="btn btn-info center-block"/>
				</form>
       		</div>
       	</div>
      </div>		

	   
	
	   
      
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
        <%if (session.getAttribute("admin")!=null || (session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail()))) {%>
		<button class="btn btn-danger btn-lg col-sm-1 col-sm-offset-4 pull-left" type="button"  data-toggle="modal" data-target="#rimuoviModal">Rimuovi</button>
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
			         	<button onclick='<%if(session.getAttribute("admin")!=null){%>rimuovi<%} else if(session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%>rimuoviAnnuncioUtente<%}%>(<%=annuncioDettagliato.getId()%>)' class="btn btn-danger">Rimuovi</button>
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
	<%}%>
</section>	
   
    
    
   <script>
   function categoriaLibro(){
	  $("#soloAppunti").css("display", "none");
	  $("#soloLibro").css("display", "block");
	  $("#isbn").prop('disabled', false);
	  $("#autoreLibro").prop('disabled', false);
	  $("#edizione").prop('disabled', false);
	  $("#materia").prop('disabled', true);
   }
   </script>
   
   
     
   <script>
   function categoriaAppunti(){
	  $("#soloAppunti").css("display", "block");
	  $("#soloLibro").css("display", "none");
	  $("#isbn").prop('disabled', true);
	  $("#autoreLibro").prop('disabled', true);
	  $("#edizione").prop('disabled', true);
	  $("#materia").prop('disabled', false);
   }
   </script>
   
   <script>
   
   function controllaCategoriaSelezionata(){
	   if($('#libroRadioButton').is(':checked')){
		   categoriaLibro();
	   }
	   else if($('#appuntiRadioButton').is(':checked')){
		   categoriaAppunti();
	   }
   }
   
   window.onload(controllaCategoriaSelezionata());

   
   </script>
   
   
  <%if (session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%>	
   <div id="containerFooterVisualizzaAnnuncio">
   <%} %>
    <%@ include file="includes/footer.jsp" %>
   <%if (session.getAttribute("user")!=null && session.getAttribute("user").equals(annuncioDettagliato.getEmail())){%>
  </div>
   <%}%>
  </body>
</html>
