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
			<img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG"
				alt="UBM Platform" />
			<div class="col-sm-10">
				<h1>Registrazione</h1>
				<h3>Inserisci i tuoi dati personali</h3>
			</div>
		</div>
			<%if(request.getAttribute("esiste")!=null){ %>
				<h1>Sei gia registrato alla piattaforma con questo indirizzo mail.</h1>
			<%request.removeAttribute("esiste");} %>
		<form action="${pageContext.request.contextPath}/RegistraUtenteServlet" onsubmit="return validateForm()" method="post">
			<p>
				e-mail <input type="text" name="email" id="email" required="required" pattern="^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it$)" title="L'email deve essere del tipo es: a.nappo25@studenti.unisa.it."/>
			</p>
			<p>
				password <input type="password" name="pass" id="password" required="required" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title="La password deve essere lunga tra gli 8 e i 20 caratteri, contenere almeno 1 numero(i) e 1 lettera(e) minuscola(e) o maiuscola(e)."/>
				
			</p>

			<p>
				conferma password <input type="password" name="pass" id="passwordV" required="required"  />
			</p>
			<p>
				<input type="submit" name="submit" value="Registrati" />
			</p>

		</form>


		</div>
	</section>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>