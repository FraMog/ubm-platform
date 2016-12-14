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
    <%@ include file="includes/sideBar.jsp" %>
    <section class="col-sm-10" id="section">
      <div class="row">
        <div class="col-sm-2">
        <img id="profile_picture" class="img-responsive" src="img/default_profile.PNG" alt="Foto del profilo" title="Giovanni Ciampi"/>
        <br>
         <input type="button" class="btn btn-info" value="Modifica I Tuoi Dati" />
        </div>
        <div class="col-sm-7">
          <h1>Giovanni Ciampi</h1>
          <h3>E-mail: <a href="mailto:g.ciampi5@studenti.unisa.it">g.ciampi5@studenti.unisa.it</a></h3>
          <h3>Telefono: 390 1341704</h3>
          <h3>Nato il 16/12/1995</h3>
          <h3>Residente a Mercato S. Severino</h3>
          <h3>Interessi: Informatica, Programmazione, Musica.</h3>
        </div>
        <div class="col-sm-3">
          <h3>Feedback</h3>
          <img id="feedback-stars" class="img-responsive" src="img/feedback-stars.png" alt="valutazione feedback" title="feedback average"/>
          <p><b>7 valutazioni totali <br> <a href="#">vedi</a></b></p>
        </div>
        
        <div class="col-sm-12">
          <hr>
          <h2>I Tuoi Annunci Online:</h2>
        </div>
      </div>
      <div id="cont_ordine" class="container-fluid">
        <form class="pull-right" action="#" method="get">
          <div class="form-group">
            <label class="col-sm-3" for="ordine">Ordine</label>
            <div class="col-sm-9">
              <select id="ordine" class="form-control" name="ordine">
                <option value="prezzo">Prezzo migliore</option>
                <option value="data">Più recenti</option>
              </select>
            </div>
          </div>
        </form>
      </div>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
          <img src="img/annunci/libro.jpg" alt="libro" class="img-responsive col-sm-2">
          <div class="col-sm-8">
            <h4><a href="#">Object oriented software engineering</a> &emsp; <small><a href="#">Modifica Annuncio</a> &emsp; <a href="#">Rimuovi Annuncio</a></small> </h4> 
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