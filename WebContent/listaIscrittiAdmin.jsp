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
	<%@ include file="includes/navbarAdmin.jsp"%>
	<%@ include file="includes/sideBar.jsp"%>

	<section class="col-sm-10" id="section">
		<div class="row">
			<div class="col-sm-12">
				<h2>Lista iscritti alla piattaforma</h2>
				<hr>
			</div>
		</div>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>E-mail</th>
						<th>Rimuovi Account</th>
						<th>Invalida Account</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>m.memoli39@studenti.unisa.it</td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button></td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
							</button>
							</button></td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>Jacob@studenti.unisa.it</td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button></td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
							</button>
							</button></td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td>Larry@studenti.unisa.it</td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button></td>
						<td><button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
							</button>
							</button></td>
					</tr>
				</tbody>
			</table>
			</table>
		</div>
	</section>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>