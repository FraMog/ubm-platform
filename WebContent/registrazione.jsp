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
</head>
<body>
	<%@ include file="includes/navbarNonLoggato.jsp" %>
	<%@ include file="includes/sideBar.jsp" %>
	<%@ page import ="java.sql.*" %>
	
<%@ page import ="javax.sql.*" %>
 



	<section class="col-sm-10" id="section">
		<div class="row">
			<img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG"
				alt="UBM Platform" />
			<div class="col-sm-10">
				<h1>Registrazione</h1>
				<h3>Inserisci i tuoi dati personali</h3>
			</div>
		</div>
	
		<form action="${pageContext.request.contextPath}/RegistraUtenteServlet" method="post" >
    <p>e-mail        
    <input type="text" name="name" id= "email" onblur="validateEmail()" />
    <script type="text/javascript" language="javascript" src="javascript/email/validateEmail.js"></script>
    </p>

    <p>password       
    <input type="password" name="pass" id="password" onblur="validatePassword()"/>
    <script type="text/javascript" language="javascript" src="javascript/email/validatePassword.js"></script>
	</p>
    
    <p>conferma password       
    <input type="password" name="pass" id="passwordV" onblur="checkPWD()"/>
    <script type="text/javascript" language="javascript" src="javascript/email/checkPWD.js"></script>
	</p>
    <p><input type="submit" name="submit" value="OK" /></p>
	
</form>
		
		
		</div>
	</section>
	<%@ include file="includes/footer.jsp" %>
</body>
</html>
