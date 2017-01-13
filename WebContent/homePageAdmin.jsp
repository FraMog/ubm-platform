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
    <script  type="text/javascript"> 
    $(document).ready(function(){
    	$.ajax({type:"GET",
    		url: "AnnunciRecenti",
    		success: function(risposta){ //se la richiesta ha successo
    			$('#content').html(risposta);
    		},
    		error: function (response) { //se la richiesta fallisce
    			$('#content').html("Non ci sono annunci da visualizzare");
    	    }
    	});
    });
    </script>
  </head>
  <body>
   <%@ include file="includes/navbarAdmin.jsp"%>
   <%@ include file="includes/sideBar.jsp" %>
   
    <section class="col-sm-10" id="section">
     <div class="row">
        <img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG" alt="UBM Platform"/>
        <div class="col-sm-10">
          <h1>Benvenuto UBM-Admin!</h1>
        </div>
      </div>
      <div id="content" class="panel panel-default">
      </div>
    </section>
   <%@ include file="includes/footer.jsp" %>
  </body>
</html>