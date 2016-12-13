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
                <label style="color:white"><input type="radio" name="ordine" value="prezzo" checked="true">Prezzo migliore</label>
              </div>
              <div class="radio">
                <label style="color:white"><input type="radio" name="ordine" value="data">Più recenti</label>
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
                  <div class="form-group" style="text-align:center;">
                    <input type="button" class="btn btn-primary" value="Inserisci annuncio" />
                  </div>
                </form>
              </div>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <section class="col-sm-12" id="section">
      <div class="col-sm-3">
        <img id="logo_ubm" class="img-responsive" src="img/logo.PNG" alt="UBM Platform"/>
        <div>
          <h3>Completa i campi obbligatori per pubblicare il tuo annuncio</h3>
        </div>
      </div>
      <div class="col-sm-9">
       	<div class="panel panel-default">
       		<div class="panel-body">
       			<form id="profilo" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="titolo">Titolo annuncio: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="titolo" id="titolo" required="required"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2">Categoria: *</label>
		    			<div class="input-group col-sm-10" style="padding-left:15px">
		    				<div class="radio">
	                			<label><input type="radio" name="categoria" value="libro" checked="true">Libro</label>
	              			</div>
	              			<div class="radio">
	                			<label><input type="radio" name="categoria" value="appunti">Appunti</label>
	              			</div>
		    			</div>
	    			</div>
	    			<div class="form-group">
       					<label class="control-label col-sm-2" for="img">Immagine prodotto *</label>
	    				<div class="col-sm-10"><input class="form-control" type="file" name="img" id="img"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="nome">Nome prodotto: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="nome" id="nome"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="isbn">ISBN: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="isbn" id="isbn"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="autore">Autore: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="autore" id="autore"/></div>
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
    				<input type="submit" class="btn btn-info center-block"/>
				</form>
       		</div>
       	</div>
      </div>
    </section>
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
