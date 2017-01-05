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
       			<form id="annuncio" class="form-horizontal" action="InserisciAnnuncioServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="titolo">Titolo: *</label>
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
	    				<label class="control-label col-sm-2">Facoltà: *</label>
		    			<div class="form-group col-sm-3" style="padding-left:30px">
              			 	<select class="form-control" name="facolta">
               			 		<option value="informatica">Informatica</option>
               					<option value="biologia">Biologia</option>
               			 		<option value="matematica">Matematica</option>
              				</select>
              			</div>
            		</div>
	    			<div class="form-group">
       					<label class="control-label col-sm-2" for="foto">Immagine prodotto *</label>
	    				<div class="col-sm-3"><input class="form-control" type="file" name="foto" id="foto"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="isbn">ISBN: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="isbn" id="isbn"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="autore">Autore: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="autoreLibro" id="autoreLibro"/></div>
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
	    				<label class="control-label col-sm-2" for="descrizione">Descrizione: *</label>
	    				<div class="col-sm-10"><textarea class="form-control" name="descrizione" id="descrizione" form="annuncio" rows="3" cols="50" maxlength="200"></textarea></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="prezzo">Prezzo: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="prezzo" id="prezzo"/></div>
	    			</div>
    				<input type="submit" class="btn btn-info center-block"/>
				</form>
				
	<script type="text/javascript">
    function validateForm()
    {
    var a=document.forms["Form"]["titolo"].value;
    var b=document.forms["Form"]["categoria"].value;
    var c=document.forms["Form"]["facolta"].value;
    var d=document.forms["Form"]["foto"].value;
    var e=document.forms["Form"]["descrizione"].value;
    var f=document.forms["Form"]["prezzo"].value;
    if (a==null || a=="",b==null || b=="",c==null || c=="",d==null || d=="",e==null || e=="",f==null || f=="")
      {
    	alert("Completa tutti i campi");
        return false;
      }
    else{
    	alert("Request complete");
    	return true;
    }
    }
    </script>
       		</div>
       	</div>
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
