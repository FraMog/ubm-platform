<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String request_email = (String) request.getAttribute("emailInviata");
%>
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
<script type="text/javascript">
    onload = function()
    {
    	var request_email_obj= '<%=request_email%>';

		if (request_email_obj == "false") {
			$('#emailNonInviata').modal('show'); //mostro il modal
			request.removeAttribute("emailInviata");
			return false;
		}
	}
</script>
</head>
<body>
  	<%if(session.getAttribute("user")==null) {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} else if (session.getAttribute("user").equals("admin")) {%>
	<%@ include file="includes/navbarAdmin.jsp" %>
	<%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
	<%@ include file="includes/sideBar.jsp"%>
	<section class="col-sm-10" id="section">
		<div class="col-sm-10">
			<div class="container-fluid">
				<h3 style="margin-bottom: 20px">Recupero Password</h3>
				<h4 style="margin-top: 36px">Inserisci l'indirizzo e-mail del
					tuo account. Ti sarà inviata una e-mail contenente la password
					corrente.</h4>
				<form id="email" class="form-horizontal"
					action="RecuperaPasswordServlet" method="POST">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email"
							style="margin-top: 23px; margin-left: 23px;">E-mail: *</label>
						<div class="col-sm-8">
							<input class="form-control" type="text" name="email" id="email"
								required="required" style="margin-top: 18px;"
								pattern="^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it)|^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@unisa.it)|ubmplatform@gmail.com"
								title="L'e-mail deve essere del tipo nome@studenti.unisa.it o nome@unisa.it, con 'nome' contenente tra 5 e 40 caratteri alfanumerici e non (sono consentiti i seguenti simboli: .,_,%,+,-)." />
						</div>
					</div>
					<input type="submit" class="btn btn-info center-block"
						style="margin-top: 26px" />
				</form>
			</div>
		</div>
	</section>
	<%@ include file="includes/footer.jsp"%>
	<!-- MODAL EMAILNONINVIATA -->
	<div id="emailNonInviata" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content panel-danger">
				<div class="modal-header panel-heading">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Errore</h4>
				</div>
				<div class="modal-body">
					<p>C'è stato un problema nell'invio della e-mail. Riprova ad
						inserire l'e-mail correttamente.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">0K</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
