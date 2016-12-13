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
						data-toggle="dropdown">Benvenuto Giovanni<span class="caret"></span></a>
						<div class="dropdown-menu" style="padding: 10px;">
							<form class="form" action="#">
								<div class="form-group" style="text-align: center;">
									<input type="button" class="btn btn-primary"
										value="Il mio Profilo" />
								</div>
								<div class="form-group" style="text-align: center;">
									<input type="button" class="btn btn-primary"
										value="Inserisci annuncio" />
								</div>
							</form>
						</div></li>
					<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
							Logout</a></li>
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
          $(window).on('resize', function(){
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
              $('#lista').addClass('in');
              $('#collapse_link').attr("href", "#");
            }
          });

          $(document).ready(function() {
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
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
		<div class="col-sm-10">
			<div class="container-fluid">
				<h3 style="margin-bottom: 20px">Recupero Password</h3>
				<h4 style="margin-top: 36px">Inserisci l'indirizzo e-mail del tuo account. Ti sarà inviata una e-mail contenente la password corrente.</h4>
				<form id="email" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
					<div class="form-group">
					<label class="control-label col-sm-2" for="email" style="margin-top:23px; margin-left:23px;">E-mail: *</label>
						<div class="col-sm-8"><input class="form-control" type="text" name="email" id="email" required="required" style="margin-top:18px;"/></div>
					</div>
					<input type="submit" class="btn btn-info center-block" style="margin-top: 26px"/>
				</form>
			</div>
		</div>
	</section>
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