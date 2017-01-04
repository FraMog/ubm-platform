<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, it.ubmplatform.annunci.*" %>
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
    <script type="text/javascript" src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
  	<script type="text/javascript" src="javascript/annunci/loadlightboxImmagini.js"></script>
    <script src="javascript/amministrazione/rimuoviAnnuncio.js"></script>
    <script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
    <script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
    <script src="javascript/annunci/settaIdAnnuncioDaRimuovereAmministratore.js"></script>
     <script src="javascript/annunci/settaIdAnnuncioDaRimuovereUtente.js"></script>
  </head>
  <body>
  

  <%String tipologiaUtenteConnesso=null, emailLoggato=null;
   tipologiaUtenteConnesso=(String)session.getAttribute("user");
   if (tipologiaUtenteConnesso!=null && tipologiaUtenteConnesso.equals("utente")){
	   emailLoggato=(String)session.getAttribute("emailLoggato");
   }   
  %>
  
  
  <%if (tipologiaUtenteConnesso==null){ %>
      <%@ include file="includes/navbarNonLoggato.jsp" %>
   <%}else if(tipologiaUtenteConnesso.equals("utente")) {%>
         <%@ include file="includes/navbarLoggato.jsp" %>
    <%} else if(tipologiaUtenteConnesso.equals("admin")){%>
    	<%@ include file="includes/navbarAdmin.jsp" %>
    <%} %>
    
    <%@ include file="includes/sideBar.jsp" %>
    <%ArrayList <Annuncio> annunciPertinenti= (ArrayList<Annuncio>) request.getAttribute("annunciPertinenti"); %>
    
    <%-- Se sto cercando mostrando gli annunci di una facoltà allora seleziono nel sideBar la facoltà scelta --%>
    <%if (request.getAttribute("titolo")==null && request.getAttribute("facolta")!=null && request.getAttribute("categoria")==null && request.getAttribute("ordine")==null){%>
    <script type="text/javascript"> 
    $(document).ready(function() {
   	    $('#<%=request.getParameter("facolta")%>ListItem').css("background-color", "#aaa");
    });
    </script>
    <%} %>
    
    <section class="col-sm-10" id="section">
      <div class="row">
        <div class="col-sm-10">
          <%if (request.getAttribute("titolo")!=null && request.getAttribute("facolta")!=null && request.getAttribute("categoria")!=null && request.getAttribute("ordine")!=null){%><h1>Hai cercato <b><%=request.getAttribute("titolo")%></b></h1>
         <h5 style="opacity:0.7;">Categoria: <b><%=request.getAttribute("categoria")%></b>  Facolta: <b><%=request.getAttribute("facolta")%></b></h5>
         <%}  else if (request.getAttribute("facolta")!=null){%><h3 style="opacity:0.7;">Ultimi annunci pubblicati nella facoltà di: <b><%=request.getAttribute("facolta")%></b></h3><h5 style="opacity:0.7;">Cerca annunci in questa facolta</h5><%} %>
        </div>
      </div>
      
      

      <div id="cont_ordine" class="container-fluid" style="padding-left:0px;">

        <%if (request.getAttribute("titolo")!=null && request.getAttribute("facolta")!=null && request.getAttribute("categoria")!=null && request.getAttribute("ordine")!=null){%>
        <form id="riordinaRisultati" class="form-inline col-xs-12" action="RicercaAnnuncio" method="post">
          <div class="form-group">
            <label class="control-label" for="ordine">Ordina per </label>
            <select id="ordine" class="form-control" name="ordine" onchange="riordina()">
               <% if (request.getAttribute("ordine").equals("DataPubblicazione")){ %>
            	<option value="DataPubblicazione" selected="selected">Più recenti</option>
              <option value="Prezzo">Prezzo migliore</option>
              <%} else { %>
              <option value="DataPubblicazione">Più recenti</option>
              <option value="Prezzo"  selected="selected">Prezzo migliore</option>
              <%} %>
            </select>
          </div>
        </form>
        <%} else if (request.getAttribute("facolta")!=null){%> <%--Se sono giunto a questa pagina basandomi su mostra annunci facoltà, devo poter ricercare annunci in questa sottosezione --%>
           <form class="navbar-form col-xs-12" action="RicercaAnnuncio" method="post">
            <div class="form-group">
              <input name="titolo" type="text" class="form-control" placeholder="Search" pattern="[a-zA-Z]{1}[a-zA-Z0-9 ]{0,49}" title="Il titolo deve contenere tra 1 e 50 caratteri alfanumerici" required="required">
            </div>
           
            <div class="form-group">
              <select class="form-control" name="categoria">
              	<option value="tutto" selected="selected">Tutto</option>
                <option value="libro">Libro</option>
                <option value="appunti">Appunti</option>
              </select>
            </div>
            <div class="input-group" style="margin-left:10px">
              <div class="radio" style="margin-right:10px">
                <label><input type="radio" name="ordine" value="Prezzo" checked="true"> Prezzo migliore</label>
              </div>
              <div class="radio">
                <label><input type="radio" name="ordine" value="DataPubblicazione"> Più recenti</label>
              </div>
            </div>
            <input type="hidden" name="facolta" value="<%=request.getAttribute("facolta")%>">
            <input type="submit" class="btn btn-default" value="Cerca" style="margin-left:20px"/>
</form>
        <%} %>
	
	<div class="col-xs-12 pull-right" id="divMostraSizeRisultatiRiserca" style="text-align:right;"><h4>Annunci trovati: <b><%=annunciPertinenti.size()%></b></h4></div>
 
   </div>
   
<%--ENDROW --%>





<%-- Il contenuto dei paginator che cambia dinamicamente --%>
<div id="dynamic_content"></div>
<%--L'indice paginator --%>
	<div id="show_paginator" class="pull-right"></div>


  <%for(int i=0; i<annunciPertinenti.size();i++){%>
      
      <%if (i%5==0){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione --%>
   <div style="display:none;" class= "containerResearchResults" id='<%="containerResearchResults" + (i/5 + 1)%>' >
      <%} %>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
     
        <%if (tipologiaUtenteConnesso!=null && tipologiaUtenteConnesso.equals("utente") && emailLoggato.equals(annunciPertinenti.get(i).getEmail())){ %>
          <div class="row">
          <div class="col-xs-4 col-md-8"></div>
           <button onmouseover="this.style.color='white';" onmouseleave="this.style.color='#5bc0de';" style="color: #5bc0de;" class="btn btn-info btn-outline col-md-2" type="button" onclick="window.location.href='VisualizzaDettagliAnnuncio?annuncioID=<%=annunciPertinenti.get(i).getId()%>'">Modifica</button>
            <button class="btn btn-info col-md-2" type="button"  data-toggle="modal" data-target="#rimuoviModal" onclick="settaIdAnnuncioDaRimuovereUtente('<%=annunciPertinenti.get(i).getId()%>')">Elimina</button>
        </div>
        <%} else if (tipologiaUtenteConnesso!=null && tipologiaUtenteConnesso.equals("admin")){ %>
         <div class="row">
         <div class="col-xs-8 col-md-10"></div>
           <button class="btn btn-info col-md-2" type="button"  data-toggle="modal" data-target="#rimuoviModal" onclick="settaIdAnnuncioDaRimuovereAmministratore('<%=annunciPertinenti.get(i).getId()%>')">Rimuovi</button>
         </div>
        <%} %>
        <div class="row">
          <div class="col-xs-12 col-sm-2"><img src="<%=annunciPertinenti.get(i).getFoto() %>" alt="Foto" class="img-responsive center-block modalImageClasse"></div>
          <div class="col-xs-12 col-sm-8">
          
            <h4><a href='<%="VisualizzaDettagliAnnuncio?annuncioID=" + annunciPertinenti.get(i).getId()%>'><%= annunciPertinenti.get(i).getTitolo()%></a></h4>
            <p><%= annunciPertinenti.get(i).getDescrizione()%></p>
          </div>
          <div class="col-xs-12 col-sm-2 pull-right">
            <h4>Prezzo: <%= annunciPertinenti.get(i).getPrezzo()%></h4>
            <% java.sql.Date data= annunciPertinenti.get(i).getDataPubblicazione(); %>
            <p>Data pubblicazione: <%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(data).substring(0,10)%></p>
          </div>
        </div>
        </div>
      </div>
      
      <%if (i%5==4 || i==(annunciPertinenti.size()-1)){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione, oppure devo farlo quando sono arrivato all'ultimo elemento --%>
  </div>    
      <%} %>
      
    <%} %><%-- ENDFOR --%>
    
    
<script>
function loadBootpagAfterLoadingPage(){
  $('#show_paginator').bootpag({
      total: <%=annunciPertinenti.size()/(5+1)+1%>,
      page: 1,
      maxVisible: 5
  }).on('page', function(event, num)
  {
     $("#dynamic_content").html($("#containerResearchResults" + num).html()); 
     loadModal();
  });
  
  $("#dynamic_content").html($("#containerResearchResults1").html()); 
}

  window.onload(loadBootpagAfterLoadingPage());

	  
  </script> 
    
  
  
<%@include file="includes/lightboxImmagini.jsp" %>
        
 <!-- Modal per la cancellazione -->       
        

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
			         	<button id="rimuoviButton"  class="btn btn-danger">Rimuovi</button>
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
          




<script type="text/javascript">
window.onload(loadModal());
</script>        
          
        
        
<script>

function riordina(){
	var inputTitolo = $("<input>")
           .attr("type", "hidden")
           .attr("name", "titolo").val('<%=request.getAttribute("titolo")%>');
           $('#riordinaRisultati').append($(inputTitolo));
    var inputFacolta = $("<input>")
           .attr("type", "hidden")
           .attr("name", "facolta").val('<%=request.getAttribute("facolta")%>');
           $('#riordinaRisultati').append($(inputFacolta));              
    var inputCategoria = $("<input>")
        .attr("type", "hidden")
        .attr("name", "categoria").val('<%=request.getAttribute("categoria")%>');
        $('#riordinaRisultati').append($(inputCategoria));
     
        
    $('#riordinaRisultati').submit();
           
}


</script>          
       
          
</section>
    
 
  
    
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
