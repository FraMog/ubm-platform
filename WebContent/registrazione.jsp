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
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a id="ubm" href="index.jsp" style="font-size: 110%">UBM
							Platform</a></li>
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
					<div class="input-group" style="margin-left: 10px">
						<div class="radio" style="margin-right: 10px">
							<label style="color: white"><input type="radio"
								name="ordine" value="prezzo" checked="true">Prezzo
								migliore</label>
						</div>
						<div class="radio">
							<label style="color: white"><input type="radio"
								name="ordine" value="data">Più recenti</label>
						</div>
					</div>
					<input type="submit" class="btn btn-default" value="Cerca"
						style="margin-left: 20px" />
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Login<span class="caret"></span></a>
						<div class="dropdown-menu" style="padding: 10px;">
							<form class="form" action="#">
								<div class="form-group">
									<input name="username" type="text" class="form-control"
										placeholder="e-mail">
								</div>
								<div class="form-group">
									<input name="password" type="password" class="form-control"
										placeholder="Password"><br>
								</div>
								<div class="checkbox">
									<label><input type="checkbox">Remember me</label>
								</div>
								<input type="submit" class="btn btn-primary" value="Login" />
							</form>
						</div></li>
					<li><a href="#"><span class="glyphicon glyphicon-user"></span>
							Sign Up</a></li>
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
				$(window).on('resize', function() {
					var win = $(this);
					if (win.width() < 768) {
						$('#lista').removeClass('in');
						$('#collapse_link').attr("href", "#lista");
					} else {
						$('#lista').addClass('in');
						$('#collapse_link').attr("href", "#");
					}
				});

				$(document).ready(function() {
					var win = $(this);
					if (win.width() < 768) {
						$('#lista').removeClass('in');
						$('#collapse_link').attr("href", "#lista");
					} else {
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
			<img id="logo_ubm" class="img-responsive col-sm-2" src="img/logo.PNG"
				alt="UBM Platform" />
			<div class="col-sm-10">
				<h1>Registrazione</h1>
				<h3>Inserisci i tuoi dati personali</h3>
			</div>
		</div>
		<div id="tabella" class="panel panel-default">
			<table>
				<tr>
					<td class="td">username</td>
					<td><input type="text" name="username" /></td>
				<tr>
				<tr>
					<td class="td">e-mail</td>
					<td><input type="text" name="email" /></td>
				<tr>
				<tr>
					<td class="td">password</td>
					<td><input type="password" name="password" /></td>
				<tr>
				<tr>
					<td class="td">conferma password</td>
					<td><input type="password" name="passowrdV" /></td>
				<tr>
			</table>
			<center><button onclick="#">Invia</button></center>
		</div>
	</section>
	<div id="clearfooter"></div>
	<footer class="footer">
		<div id="footer_cont" class="container-fluid">
			<div id="contact">
				<p>
					a <b>@nome_gruppo</b> production
				</p>
				<p>
					contact us at <a href="">indirizzo@email.it</a>
				</p>
			</div>
			<div id="disclaimer" class="navbar-right">
				<b>Disclaimer</b>
				<p>
					Questa piattaforma non è responsabile degli annunci inseriti e dei
					prodotti real<br /> ma offre opportunità di poter
					condividere/ricercare annunci riguardanti materiale universitario
				</p>
			</div>
		</div>
	</footer>
</body>
</html>
