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
    <%@ include file="includes/navbarLoggato.jsp" %>
    <aside class="col-sm-2">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a id="collapse_link" data-toggle="collapse" href="#lista">Facoltà</a>
          </h4>
        </div>
        <script type="text/javascript">
          $(window).on('resize', function(){
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
              $('#lista').addClass('in');
              $('#collapse_link').attr("href", "#");
            }
          });

          $(document).ready(function() {
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
              $('#lista').addClass('in');
              $('#collapse_link').attr("href", "#");
            }
          });
        </script>
        <div id="lista" class="panel-collapse collapse">
          <ul class="list-group">
            <li class="list-group-item"><a href="#">Informatica</a></li>
            <li class="list-group-item"><a href="#">Matematica</a></li>
            <li class="list-group-item"><a href="#">Biologia</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
          </ul>
        </div>
      </div>
    </aside>
    <section class="col-sm-10" id="section">
      <div class="col-sm-9 panel panel-default">
      	<div class="panel-body">
			<h3 style="margin-bottom: 20px">Titolo annuncio</h3>
			<div class="row"><h4 class="col-xs-4">Nome prodotto:</h4><h4 class="col-xs-3">Ingegneria</h4></div>
			<div class="row"><h4 class="col-xs-4">Categoria:</h4><h4 class="col-xs-3">Libro</h4></div>
			<!--Solo libro -->
			<div class="row"><h4 class="col-xs-4">ISBN:</h4><h4 class="col-xs-3">1345344</h4></div>
			<div class="row"><h4 class="col-xs-4">Autore:</h4><h4 class="col-xs-3">Brugge</h4></div>
			<div class="row"><h4 class="col-xs-4">Edizione:</h4><h4 class="col-xs-3">2</h4></div>
			<!--  -->
			<div class="row"><h4 class="col-xs-4">Materia:</h4><h4 class="col-xs-3">Ingegneria</h4></div>
			<div class="row"><h4 class="col-xs-4">Condizioni prodotto:</h4><h4 class="col-xs-3">Sottolineato</h4></div>
			<div class="row"><h4 class="col-xs-4">Data:</h4><h4 class="col-xs-3">13/12/2016</h4></div>
			<div class="row"><h4 class="col-xs-4">Prezzo:</h4><h4 class="col-xs-3">15€</h4></div>
		</div>
      </div>
      <img id="logo_ubm" class="img-responsive col-sm-3" src="img/annunci/libro.jpg" alt="UBM Platform"/>
      <h3 class="pull-right">Pubblicato da: <a href="#" class="btn btn-info">Nome Utente</a></h3>
    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
