<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, it.ubmplatform.annunci.*" %>
<!DOCTYPE html>
<html lang="it">
  <head>
    <link rel="icon" href="img/favicon.ico"/>
    <title>UBM Platform</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
  	<link rel="stylesheet" href="css/stile.css">
  </head>
  <body>
  	<%if(session.getAttribute("email")==null) {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
    
    
    <%@ include file="includes/sideBar.jsp" %>
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
         <%}  else if (request.getAttribute("facolta")!=null){%><h3 style="opacity:0.7;">Stai visualizzando gli annunci pubblicati in: <b><%=request.getAttribute("facolta")%></b></h3><%} %>
        </div>
      </div>
      
      
      <div class="row">
      <div id="cont_ordine" class="container-fluid">

      
        <form class="pull-left form-inline" action="#" method="get">
          <div class="form-group">
            <label class="control-label" for="ordine">Ordina per </label>
            <select id="ordine" class="form-control" name="ordine">
            	<option value="data">Più recenti</option>
              <option value="prezzo">Prezzo migliore</option>
            </select>
          </div>
        </form>

	<%--L'indice paginator --%>
	<div id="show_paginator" class="pull-right"></div>

 
   </div>
</div>

<%ArrayList <Annuncio> annunciPertinenti= (ArrayList<Annuncio>) request.getAttribute("annunciPertinenti"); %>



<%-- Il contenuto dei paginator che cambia dinamicamente --%>
<div id="dynamic_content"></div>
<script>
function loadBootpagAfterLoadingPage(){
  $('#show_paginator').bootpag({
      total: <%=annunciPertinenti.size()/(5+1)+1%>,
      page: 1,
      maxVisible: 5
  }).on('page', function(event, num)
  {
     $("#dynamic_content").html("Page " + num); // or some ajax content loading...
  });
}
  window.onload(loadBootpagAfterLoadingPage());
  </script> 

  <%for(int i=0; i<annunciPertinenti.size();i++){%>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
          <img src="img/annunci/libro.jpg" alt="Foto" class="img-responsive col-sm-2">
          <div class="col-sm-8">
            <h4><a href="#"><%= annunciPertinenti.get(i).getTitolo()%></a></h4>
            <p><%= annunciPertinenti.get(i).getDescrizione()%></p>
          </div>
          <div class="col-sm-2 pull-right">
            <h4>Prezzo: <%= annunciPertinenti.get(i).getPrezzo()%></h4>
            <% java.sql.Date data= annunciPertinenti.get(i).getDataPubblicazione(); %>
            <p>Data pubblicazione: <%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(data).substring(0,10)%></p>
          </div>
        </div>
        
      </div>
      <%} %>
      
    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
