<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*,it.ubmplatform.account.Account"%>

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
<script type="text/JavaScript" src="javascript/admin/gestioneAdmin.js"></script>
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
					<%
						ArrayList<Account> lista = (ArrayList) request.getAttribute("lista");
						int i = 1;
					%>
					<%
						System.out.println(lista.size());
					%>

					<%
						for (Account p : lista) {
					%>
					<tr>
						<th scope="row"><%=i++%></th>
						<td><%=p.getEmail()%></td>
						<%
							String email = p.getEmail();
						%>
						<%
							System.out.println(email);
						%>
						<td><button type="button" class="btn btn-default"
								onclick="javascript:cancellaAccount('<%=email%>')">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button></td>
						<td><button type="button" class="btn btn-default"
								onclick="javascript:invalidaAccount('<%=email%>')">
								<span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
							</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			</table>
		</div>

		<%--Modal cancellaAccount --%>
		<div id="cancellaAccountModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Sei sicuro di voler cancellare questo
							account?</h4>
					</div>
					<div class="modal-body">
						<p>Cancellando questo account cancellerai anche i relativi
							annunci.</p>
						<input type="checkbox" id="cancellaFeedback"
							name="cancellaFeedback" value="true")>Seleziona per
						eliminare i feedback dell'account.<br>
					</div>
					<div class="modal-footer">
						<button type="button" id="annulla" name="annulla"
							class="btn btn-success" data-dismiss="modal">Annulla</button>
						<button type="button" id="prosegui" name="prosegui"
							class="btn btn-success">Prosegui</button>

					</div>
				</div>
			</div>
		</div>

		<%--Modal invalidaAccount --%>
		<div id="invalidaAccountModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Sei sicuro di voler cancellare questo
							account?</h4>
					</div>
					<div class="modal-body">
						<p>Invalidando l'account selezionato, quest'ultimo non potra
							accedere alla piattaforma per 7 giorni.</p>
						<input type="checkbox" id="cancellaFeedbackI"
							name="cancellaFeedbackI" value="true")>Seleziona per
						eliminare i feedback dell'account.<br>
					</div>
					<div class="modal-footer">
						<button type="button" id="annullaI" name="annullaI"
							class="btn btn-success" data-dismiss="modal">Annulla</button>
						<button type="button" id="proseguiI" name="proseguiI"
							class="btn btn-success">Prosegui</button>

					</div>
				</div>
			</div>
		</div>

		<%--Modal Operazione fallita --%>
		<div id="operazioneFallitaModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Impossibile completare l'operazione</h4>
						</div>
						<div class="modal-body">
							<p>L'operazione non ha avuto successo riprova in seguito.</p>
						</div>
						<div class="modal-footer">
							<button type="button" id="ok" name="ok" class="btn btn-success"
								data-dismiss="modal">OK</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>