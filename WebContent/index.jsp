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
    <%@ include file="includes/navbarNonLoggato.jsp" %>
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
      <div class="row">
        <img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG" alt="UBM Platform"/>
        <div class="col-sm-10">
          <h1>Benvenuto!</h1>
          <h3>Iscriviti subito per poter acquistare e vendere materiale universitario</h3>
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
        <a href="#" class="btn btn-info col-sm-4">Pubblica ora il tuo annuncio</a>
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
