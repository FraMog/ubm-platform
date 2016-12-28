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
        <img id="profile_picture" class="img-responsive col-sm-2" src="img/default_profile.PNG" alt="Foto del profilo" title="Giovanni Ciampi"/>
        <div class="col-sm-7">
          <h1>Giovanni Ciampi</h1>
          <h3>E-mail: <a href="mailto:g.ciampi5@studenti.unisa.it">g.ciampi5@studenti.unisa.it</a></h3>
          <h3>Telefono: 390 1341704</h3>
          <h3>Nato il 16/12/1995</h3>
          <h3>Residente a Mercato S. Severino</h3>
          <h3>Interessi: Informatica, Programmazione, Musica.</h3>
        </div>
        <div class="col-sm-3">
          <div>
            <h2>Feedback</h2>
            <img id="feedback-stars" class="img-responsive col-sm-10" style="padding-left:0px; padding-bottom: 0px" src="img/feedback-stars.png" alt="valutazione feedback" title="feedback average"/> 
          </div>
          <div class="col-sm-12" style="padding-left: 0px; padding-top: 0px">
            <h4>Giovanni ha 7 valutazioni:</h4>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <h4><a href="#" style="color:black" title="Vai al profilo di questo utente" style="padding-left:0px; padding-bottom: 0px" >Marco: <small>Giudizio:5/5</small></a></h4>
            </div>
            
            <div class="col-sm-12">
              <p>Utente serio e preciso.</p>
            </div>  
             <div class="col-sm-12">
              <h4><a href="#" style="color:black" title="Vai al profilo di questo utente" style="padding-left:0px; padding-bottom: 0px" >Antonio: <small>Giudizio:4/5</small></a></h4>
            </div>
            
            <div class="col-sm-12">
              <p>Libro perfetto.</p>
            </div>
            
            <div class="col-sm-12">
              <h5><a data-toggle="modal" data-target="#vediFeedbackModal">Visualizza tutti i Feedback</a></h5>
            </div>
            <div class="col-sm-12">
              <h5>Hai fatto affari con Giovanni? <a data-toggle="modal" data-target="#inserisciFeedbackModal">Inserisci un Feedback!</a></h5>
            </div> 
          </div>
          
        </div>
        
        <div class="col-sm-12">
          <hr>
          <h2>Annunci di Giovanni:</h2>
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
    
    <!-- MODAL VISUALIZZA FEEDBACK-->
 	<div class="modal fade" id="vediFeedbackModal" role="dialog">
    	<div class="modal-dialog">
    
   		    <!-- Modal content-->
     		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">Modal Header</h4>
        		</div>
        		<div class="modal-body">
          			<p>Some text in the modal.</p>
        		</div>
       	 		<div class="modal-footer">
          			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		</div>
      		</div>
      
    	</div>
  	</div>
  	
  	<!-- MODAL INSERISCI FEEDBACK-->
  	<div class="modal fade" id="inserisciFeedbackModal" role="dialog">
    	<div class="modal-dialog">
    
   		    <!-- Modal content-->
     		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">Inserisci feedback</h4>
        		</div>
        		
        		<form action="InserisciFeedbackServlet" method="POST">
        		
        		<div class="modal-body">
        			<div class="form-group">
          				<label for="valutazioneFeedback">Valutazione:</label>
          				<select class="form-control" id="valutazioneFeedback" name="valutazioneFeedback">
          					<option value="1">1</option>
          					<option value="2">2</option>
          					<option value="3">3</option>
          					<option value="4">4</option>
          					<option value="5">5</option>
          				</select>
          			
          				<label for="descrizioneFeedback">Descrizione:</label>
          				<textarea style="overflow:auto;resize:none" class="form-control" rows="5" id="descrizioneFeedback" name="descrizioneFeedback"></textarea>
          			
          			</div>
        		</div>
       	 		<div class="modal-footer">
       	 			<input type="submit" class="btn btn-default">
          			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		</div>
        		
        		</form>
      		</div>
      
    	</div>
  	</div>
  	
  	<!-- MODAL MODIFICA FEEDBACK-->
  	<div class="modal fade" id="modificaFeedbackModal" role="dialog">
    	<div class="modal-dialog">
    
   		    <!-- Modal content-->
     		<div class="modal-content">
        		<div class="modal-header">
          			<button type="button" class="close" data-dismiss="modal">&times;</button>
          			<h4 class="modal-title">Modal Header</h4>
        		</div>
        		<div class="modal-body">
          			<p>Some text in the modal.</p>
        		</div>
       	 		<div class="modal-footer">
          			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        		</div>
      		</div>
      
    	</div>
  	</div>
    
  </body>
</html>