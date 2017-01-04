<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
    <%@ include file="includes/navbarNonLoggato.jsp" %>
	<%@ include file="includes/sideBar.jsp" %>
	<section class="col-sm-10" id="section">
		<div class="col-sm-10">
			<div class="container-fluid">
				<h3 style="margin-bottom: 20px">Recupero Password</h3>
				<h4 style="margin-top: 36px">Inserisci l'indirizzo e-mail del
					tuo account. Ti sarà inviata una e-mail contenente la password
					corrente.</h4>
				<form id="email" class="form-horizontal" action="RecuperaPasswordServlet" method="POST" enctype="multipart/form-data">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email" style="margin-top: 23px; margin-left: 23px;">E-mail: *</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="email" id="email" required="required" style="margin-top: 18px;" />
						</div>
					</div>
					<input type="submit" class="btn btn-info center-block"
						style="margin-top: 26px" />
				</form>
			</div>
		</div>
	</section>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
