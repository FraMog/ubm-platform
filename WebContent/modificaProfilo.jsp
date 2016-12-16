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
         		<label class="btn btn-info btn-file">
             	Aggiorna la tua Foto <input type="file" style="display: none;">
          		</label>
       		</div>
        	<form id="profilo" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
        		<div class="col-sm-10">
          			<div class="form-group">
              			<label class="control-label col-sm-2" for="nome">Nome: *</label>
              			<div class="col-sm-8"><input class="form-control" type="text" name="nome" id="nome" required="required" value="Giovanni"/></div>
          			</div>
            		<div class="form-group">
              			<label class="control-label col-sm-2" for="cognome">Cognome: *</label>
              			<div class="col-sm-8"><input class="form-control" type="text" name="cognome" id="cognome" value="Ciampi"/></div>
            		</div>

		            <div class="form-group">
		              <label class="control-label col-sm-2" for="email">E-mail: </label>
		              <div class="col-sm-8"><input class="form-control" type="text" name="email" id="email" readonly="readonly" value="g.ciampi5@studenti.unisa.it"/></div>
		            </div>


            		<div class="form-group">
              			<label class="control-label col-sm-2" for="password">Password: </label>
             			<div class="col-sm-8">
               				 <!-- Trigger the modal with a button -->
                			<button type="button" class="btn btn-info " data-toggle="modal" data-target="#myModal">Modifica Password</button>
             			</div>
             		</div> 
 
              		<div class="form-group">
              			<label class="control-label col-sm-2" for="tel">Telefono: </label>
              			<div class="col-sm-8"><input class="form-control" type="text" name="tel" id="tel" value="390 7839283"/></div>
            		</div>
            		<div class="form-group">
              			<label class="control-label col-sm-2" for="data">Data di nascita: </label>
              			<div class="col-sm-8"><input class="form-control" type="date" name="data" id="data" value="1995-12-16"/></div>
            		</div>
            		<div class="form-group">
              			<label class="control-label col-sm-2" for="interessi">Interessi: </label>
              			<div class="col-sm-8">
                			<textarea class="form-control" name="interessi" id="interessi" form="profilo" rows="3" cols="50" maxlength="200">Informatica, Programmazione, Musica.</textarea>
              			</div>
            		</div>
            		<div class="form-group">
              			<label class="control-label col-sm-2" for="residenza">Residenza: </label>
              			<div class="col-sm-8"><input class="form-control" type="text" name="residenza" id="residenza" value="Mercato San Severino"/></div>
            		</div>
            		<br>
            		<hr>
              		<div class="form-group">
                		<div class="row">
                  			<div class="col-sm-2">
                    			<button class="btn btn-warning btn-lg" type="button">ANNULLA</button>
                  			</div>
                  			<div class="col-sm-2">
                    			<button class="btn btn-success btn-lg" type="submit">SALVA</button>
                  			</div>
                  			<div class="col-sm-2">
                    			<button class="btn btn-danger btn-lg" type="button"  data-toggle="modal" data-target="#disattivaModal">DISATTIVA PROFILO</button>
                    			<!-- Popup in caso di tentata disattivazione -->
                    			<div id="disattivaModal" class="modal fade" role="dialog">
                      				<div class="modal-dialog">

				                        <!-- Modal content-->
				                        <div class="modal-content">
				                          <div class="modal-header">
				                            <button type="button" class="close" data-dismiss="modal">&times;</button>
				                            <h4 class="modal-title">Giovanni, sei sicuro di voler disattivare il tuo profilo?</h4>
				                          </div>
				                          <div class="modal-body">
				                            <p>Cliccando su Disattiva Profilo tutti i tuoi dati (Profilo, annunci, etc), eccetto i Feedback che hai fornito, non 
				                            saranno più visibili da nessuno sulla piattaforma. Tuttavia, ti basterà effettuare nuovamente il login per riattivare il tuo profilo, ritrovando tutti i tuoi vecchi dati.</p>
				                          </div>
				                          <div class="modal-footer">
				                            <button type="button" class="btn btn-success" data-dismiss="modal">Annulla</button>
				                            <button type="button" class="btn btn-danger" >Disattiva Profilo</button>
				                          </div>
				                        </div>

                      				</div>
                    			</div>
                  			</div>
                		</div>
              		</div>
            	</div>    
        	</form>    
      	</div>
               <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
           
          <div class="modal-dialog">
            
              <!-- Modal content-->
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Modifica Password</h4>
              </div>

              <form id="password" class="form-horizontal" action="" method="post" enctype="multipart/form-data">

	              <div class="modal-body">
	                <div class="form-group">
	                  <label class="col-sm-5">Vecchia Password *</label>
	                  <div class="col-sm-7">
	                    <input class="form control" type="password" required="required">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label class="col-sm-5">Nuova Password *</label>
	                  <div class="col-sm-7">
	                    <input class="form control" type="password" required="required">
	                  </div>
	                </div>
	                <div class="form-group">
	                  <label class="col-sm-5">Conferma Password *</label>
	                  <div class="col-sm-7">
	                    <input class="form control" type="password" required="required">
	                  </div>
	                </div>
	              </div>
              </form>
              <div class="modal-footer text-center">
                <button type="submit" class="btn btn-warning text-center" text-align="center" data-dismiss="modal">Aggiorna Password</button>
              </div>
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