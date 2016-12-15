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
            <li><a href="#" style="font-size:110%">UBM Platform</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <section class="col-sm-12" id="section">
      <div class="col-sm-3">
        <img id="logo_ubm" class="img-responsive" src="img/logo.PNG" alt="UBM Platform"/>
        <div>
          <h3>Prima di iniziare è necessario creare il profilo</h3>
        </div>
      </div>
      <div class="col-sm-9">
       	<div class="panel panel-default">
       		<div class="panel-body">
       			<form id="profilo" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
       				<div class="form-group">
       					<img id="foto" class="img-responsive col-sm-2" src="img/default_profile.PNG" style="width:150px"/>
	    				<div class="col-sm-10"><input class="btn btn-primary" type="file" name="img" id="img" accept=".jpg,.png,.jpg,.jpeg"/></div>
	    				<script type="text/javascript">
		    				function readURL(input) {
		    				    if (input.files && input.files[0]) {
		    				        var reader = new FileReader();
	
		    				        reader.onload = function (e) {
		    				            $('#foto').attr('src', e.target.result);
		    				        }
	
		    				        reader.readAsDataURL(input.files[0]);
		    				    }
		    				}
	
		    				$("#img").change(function(){
		    				    readURL(this);
		    				});
	    				</script>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="nome">Nome: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="nome" id="nome" required="required"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="cognome">Cognome: *</label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="cognome" id="cognome" required="required"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="email">E-mail: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="email" id="email" readonly="readonly"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="tel">Telefono: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="tel" id="tel" pattern="[0-9]{10}" title="Questo campo deve contenere 10 numeri"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="data">Data di nascita: </label>
	    				<div class="col-sm-10"><input class="form-control" type="date" name="data" id="data"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="interessi">Interessi: </label>
	    				<div class="col-sm-10"><textarea class="form-control" name="interessi" id="interessi" form="profilo" rows="3" cols="50" maxlength="200"></textarea></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="residenza">Residenza: </label>
	    				<div class="col-sm-10"><input class="form-control" type="text" name="residenza" id="residenza"/></div>
	    			</div>
    				<input type="submit" class="btn btn-info center-block"/>
				</form>
       		</div>
       	</div>
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>
