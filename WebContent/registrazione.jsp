<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
<link rel="icon" href="img/favicon.ico" />
<title>UBM Platform</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, shrink-to-fit=no, initial-scale=1" />
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" language="javascript" src="javascript/email/validateForm.js"></script>
</head>
<body>
	<%@ include file="includes/navbarNonLoggato.jsp"%>
	<%@ include file="includes/sideBar.jsp"%>
	<%@ page import="java.sql.*"%>

	<%@ page import="javax.sql.*"%>




	<section class="col-sm-10" id="section">
		<div class="row">
			<div class="col-sm-10">
				<h1>Registrazione</h1>
				<h3>Inserisci i tuoi dati personali</h3>
			</div>
		</div>
		<div>	
		<img id="logo_ubm" class="img-responsive col-sm-5 pull-right" style="margin-right:20%;" src="img/logo.PNG" alt="UBM Platform" />
		</div>
			<%if(request.getAttribute("esiste")!=null){ %>
				<h1>Sei gia registrato alla piattaforma con questo indirizzo mail.</h1>
			<%request.removeAttribute("esiste");} %>
		
		<form action="RegistraUtenteServlet" onsubmit="return validateForm()" method="post">
			
				E-mail <br>
				<input type="text" name="email" id="email" placeholder="Inserisci la tua email" required="required" pattern="^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it$)" title="L'email deve essere del tipo es: a.nappo25@studenti.unisa.it."/>
				<br>
				<br>
				Password <br>
				<input type="password" name="pass" id="password" placeholder="Inserisci la tua password" required="required" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="La password deve essere lunga tra gli 8 e i 20 caratteri, contenere almeno 1 numero(i) e 1 lettera(e) minuscola(e) o maiuscola(e)."/>
				<br>
				<br>
			
		
				Conferma Password <br>
				<input type="password" name="pass" id="passwordV" placeholder="Reinserisci la tua password" required="required"  />
				<br>
				<br>
			
				<input type="submit" name="submit" value="Registrati" />
			

		</form>
	</section>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>