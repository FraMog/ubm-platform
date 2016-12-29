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
    
    
     
    
    <ul class="pagination pull-right" style="margin:0px;">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <li class="page-item active" >
      <a class="page-link" href="#">1 <span class="sr-only">(current)</span></a>
    </li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item"><a class="page-link" href="#">6</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
  </ul>
   </div>
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
