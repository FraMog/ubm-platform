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
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li><a id="ubm" href="index.jsp" style="font-size:110%">UBM Platform</a></li>
          </ul>
          <form class="navbar-form navbar-left" action="#">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <div class="form-group">
              <select class="form-control" name="facolta">
                <option value="informatica">Informatica</option>
              </select>
            </div>
            <div class="form-group">
              <select class="form-control" name="tipo">
                <option value="libro">Libro</option>
              </select>
            </div>
            <div class="input-group" style="margin-left:10px">
              <div class="radio" style="margin-right:10px">
                <label style="color:white"><input type="radio" name="ordine" value="prezzo" checked="true"> Prezzo migliore</label>
              </div>
              <div class="radio">
                <label style="color:white"><input type="radio" name="ordine" value="data"> Più recenti</label>
              </div>
            </div>
            <input type="submit" class="btn btn-default" value="Cerca" style="margin-left:20px"/>
          </form>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Benvenuto Giovanni<span class="caret"></span></a>
              <div class="dropdown-menu" style="padding:10px;">
                <form class="form" action="#">
                  <div class="form-group" style="text-align:center;">
                    <input type="button" class="btn btn-primary" value="Il mio Profilo" />
                  </div>
                </form>
              </div>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
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
    <div id="clearfooter"></div>
    <footer class="footer">
      <div id="footer_cont" class="container-fluid">
        <div id="contact">
          <p>a <b>@nome_gruppo</b> production</p>
          <p>contact us at <a href="">indirizzo@email.it</a></p>
        </div>
        <div id="disclaimer" class="navbar-right">
          <b>Disclaimer</b>
          <p>Questa piattaforma non è responsabile degli annunci inseriti e dei prodotti real<br/> ma offre opportunità di poter condividere/ricercare annunci riguardanti materiale universitario</p>
        </div>
      </div>
    </footer>
  </body>
</html>