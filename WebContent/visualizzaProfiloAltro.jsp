<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="it.ubmplatform.profilo.Profilo"%>
<%@page import="it.ubmplatform.annunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="it.ubmplatform.feedback.*"%>



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
<script src="javascript/amministrazione/rimuoviAnnuncio.js"></script>
<script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
<script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
<script
	src="javascript/annunci/settaIdAnnuncioDaRimuovereAmministratore.js"></script>
<script src="javascript/annunci/settaIdAnnuncioDaRimuovereUtente.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
<script type="text/javascript"
	src="javascript/annunci/loadlightboxImmagini.js"></script>
	<script type="text/JavaScript" src="javascript/admin/gestioneAdmin.js"></script>

</head>
<body>

	<% if(session.getAttribute("user").equals("admin")){%>
    	<%@ include file="includes/navbarAdmin.jsp" %>
    <%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
    <%@ include file="includes/sideBar.jsp" %>
	<%
		Profilo profileToShow = null;
		try {
			profileToShow = (Profilo) request.getSession().getAttribute("profileToShow");
		} catch (Exception e) {
	%><script type="text/javascript">
		alert("Il profilo cercato non esiste");
	</script>
	<%
		response.sendRedirect("index.jsp");
		}
		String foto;
		if (profileToShow.getFoto() != null)
			foto = "img/profilo/"+profileToShow.getFoto();
		else
			foto = "img/profilo/default_profile.PNG";
		ArrayList<Feedback> listaFeedback;
		try{
			listaFeedback = (ArrayList<Feedback>) request.getAttribute("listaFeedback");
		} catch (Exception e){
			listaFeedback = new ArrayList<Feedback>();
		}
		
		int mediaFeedback = this.getFeedbackAverage(listaFeedback);
		Feedback fe;
		%>
		
			<%!
		public int getFeedbackAverage(ArrayList<Feedback> lista){
			if(lista == null)
				return 0;
			
			if(lista.size() == 0)
				return 0;
			
			int sum = 0;
	
			for(Feedback f : lista)
				sum += f.getValutazione();
			
			return (sum / lista.size());
		}
		
	%>

	<section class="col-sm-10" id="section">
		<div class="row">
			<img id="profile_picture" class="img-responsive col-sm-2"
				src="<%=foto%>" alt="Foto di <%=profileToShow.getNome()%>"
				title="<%=profileToShow.getNome()%>" />
			<div class="col-sm-7">

				<%
					//Definisco le varie stringhe

					String nomeCognome = profileToShow.getNome() + " " + profileToShow.getCognome();

					String telefono = profileToShow.getTelefono();
					if (telefono == null || telefono == "")
						telefono = " -";

					String email = profileToShow.getEmail();

					String dataNascita = " -";

					if (profileToShow.getDataNascita() != null) {
						try {
							SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
							dataNascita = f.format(profileToShow.getDataNascita());
						} catch (Exception e) {
							dataNascita = " -";
						}
					}

					String residenza = profileToShow.getResidenza();
					if (residenza == null || residenza == "")
						residenza = " -";

					String interessi = profileToShow.getInteressi();
					if (interessi == null || interessi.equals(""))
						interessi = " -";
				%>

				<h1><%=nomeCognome%></h1>
				<h3>
					E-mail: <a id="emailR" href="<%="mailto:" + email%>"><%=email%></a>
				</h3>
				<h3>
					Telefono:
					<%=telefono%></h3>
				<h3>
					Nato il
					<%=dataNascita%></h3>
				<h3>
					Residente a
					<%=residenza%></h3>
				<h3>
					Interessi:
					<%=interessi%></h3>
			</div>
			<div class="col-sm-3">
				<div>
					<h2>Feedback</h2>
					<img id="feedback-stars" class="img-responsive col-sm-10"
						style="padding-left: 0px; padding-bottom: 0px"
						src="img/feedback/feedback<%=mediaFeedback%>.png" alt="valutazione feedback"
						title="feedback average" />
				</div>
				<div class="col-sm-12" style="padding-left: 0px; padding-top: 0px">
					<h4><%=profileToShow.getNome() %> ha ricevuto <%= listaFeedback.size() %> valutazioni:</h4>
				</div>
				<div class="row">
				<%if(listaFeedback.size() > 0) { %>
				<% fe = listaFeedback.get(0);%>
					<div class="col-sm-12">
						<h4>
							<a href="VisualizzaProfiloServlet?emailToShow=<%=fe.getEmailP() %>" style="color: black"
								title="Vai al profilo di questo utente"
								style="padding-left:0px; padding-bottom: 0px">
								<%=fe.getEmailP() %>: <small>Giudizio:<%=fe.getValutazione()%>/5</small></a>
						</h4>
					</div>

					<div class="col-sm-12">
						<p><%=fe.getDescrizione() %></p>
					</div>
					<%} if(listaFeedback.size() > 1){%>
					<% fe = listaFeedback.get(1);%>
					<div class="col-sm-12">
						<h4>
							<a href="VisualizzaProfiloServlet?emailToShow=<%=fe.getEmailP() %>" style="color: black"
								title="Vai al profilo di questo utente"
								style="padding-left:0px; padding-bottom: 0px"><%=fe.getEmailP() %>: <small>Giudizio:<%=fe.getValutazione()%>/5</small></a>
						</h4>
					</div>

					<div class="col-sm-12">
						<p><%fe.getDescrizione(); %></p>
					</div>
					<%} %>
					<%if(listaFeedback.size() > 0) { %>
					<div class="col-sm-12">
						<!-- data-remote = false non mi fa caricare direttamente il modal al click (deprecato) -->
						<h5>
							<a href="modalVisualizzaFeedback.html" data-remote="false"
								data-toggle="modal" data-target="#vediFeedbackModal">Visualizza
								tutti i Feedback di <%=profileToShow.getNome() %></a>
						</h5>
					</div>
					<%} %>
					<div class="col-sm-12">
						<h5>
							Hai fatto affari con <%=profileToShow.getNome() %>? <a data-toggle="modal" data-target="#inserisciFeedbackModal">Inserisci
								un Feedback!</a>
						</h5>
						
						<h5>
							<a data-toggle="modal"
								data-target="#modificaFeedbackModal">Modifica un Feedback!</a>
						</h5>
					</div>
					
				</div>
			<%--BOTTONI VISIBILI ALL'ADMIN PER CANCELLARE E INVALIDARE ACCOUNT --%>
				<%
				if (session.getAttribute("user").equals("admin")) {
				%>
				<br>
				<button type="button" class="btn btn-warning" onclick="javascript:invalidaAccountA('<%=email%>')">Invalida</button> 
				<button type="button" class="btn btn-danger" onclick="javascript:cancellaAccountA('<%=email%>')">Cancella</button>
				<%} %>

			</div>

			<div class="col-sm-12">
				<hr>
				<h2>
					Annunci di
					<%=nomeCognome%>:
				</h2>
			</div>
		</div>







		<!-- visualizzo gli annunci -->
		<%
			String tipologiaUtenteConnesso = null, emailLoggato = null;
			tipologiaUtenteConnesso = (String) session.getAttribute("user");
			if (tipologiaUtenteConnesso != null && tipologiaUtenteConnesso.equals("utente")) {
				emailLoggato = (String) session.getAttribute("emailLoggato");
			}

			ArrayList<Annuncio> annunciPertinenti;
			int length;
			try {
				annunciPertinenti = (ArrayList<Annuncio>) request.getAttribute("listaAnnunci");
				length = annunciPertinenti.size();

			} catch (Exception e) {
				annunciPertinenti = new ArrayList<Annuncio>();
				length = 0;
			}
		%>


		<!-- Codice preso da RicercaAnnuncio di Francesco Mogavero ********************+ -->



		<%-- Il contenuto dei paginator che cambia dinamicamente --%>
		<div id="dynamic_content"></div>
		<%--L'indice paginator --%>
		<div id="show_paginator" class="pull-right"></div>


		<%
			for (int i = 0; i < length; i++) {
		%>

		<%
			if (i % 5 == 0) {
		%>
		<%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione --%>
		<div style="display: none;" class="containerResearchResults"
			id='<%="containerResearchResults" + (i / 5 + 1)%>'>
			<%
				}
			%>
			<div id="content" class="panel panel-default">
				<div class="panel-body risultato">

					<%
						if (session.getAttribute("user").equals("admin")) {
					%>
					<div class="row">
						<div class="col-xs-8 col-md-10"></div>
						<button class="btn btn-info col-md-2" type="button"
							data-toggle="modal" data-target="#rimuoviModal"
							onclick="settaIdAnnuncioDaRimuovereAmministratore('<%=annunciPertinenti.get(i).getId()%>')">Rimuovi</button>
					</div>
					<%
						}
					%>
					<div class="row">
						<div class="col-xs-12 col-sm-2">
							<img src="img/annunci/<%=annunciPertinenti.get(i).getFoto()%>" alt="Foto"
								class="img-responsive center-block modalImageClasse">
						</div>
						<div class="col-xs-12 col-sm-8">

							<h4>
								<a
									href='<%="VisualizzaDettagliAnnuncio?annuncioID=" + annunciPertinenti.get(i).getId()%>'><%=annunciPertinenti.get(i).getTitolo()%></a>
							</h4>
							<p><%=annunciPertinenti.get(i).getDescrizione()%></p>
						</div>
						<div class="col-xs-12 col-sm-2 pull-right">
							<h4>
								Prezzo:
								<%=annunciPertinenti.get(i).getPrezzo()%></h4>
							<%
								java.sql.Timestamp data = annunciPertinenti.get(i).getDataPubblicazione();
							%>
							<p>
								Data pubblicazione:
								<%=new java.text.SimpleDateFormat("dd-MM-yyyy").format(data).substring(0, 10)%> alle <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data).substring(10,19)%></p>
						</div>
					</div>
				</div>
			</div>

			<%
				if (i % 5 == 4 || i == (annunciPertinenti.size() - 1)) {
			%>
			<%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione, oppure devo farlo quando sono arrivato all'ultimo elemento --%>
		</div>
		<%
			}
		%>

		<%
			}
		%><%-- ENDFOR --%>

		<script>
			function loadBootpagAfterLoadingPage() {
				$('#show_paginator').bootpag({
					total :
		<%=annunciPertinenti.size() / (5 + 1) + 1%>
			,
					page : 1,
					maxVisible : 5
				})
						.on(
								'page',
								function(event, num) {
									$("#dynamic_content")
											.html(
													$(
															"#containerResearchResults"
																	+ num)
															.html());
									loadModal();
								});

				$("#dynamic_content").html(
						$("#containerResearchResults1").html());
			}
			window.onload(loadBootpagAfterLoadingPage());
		</script>



		<%@include file="includes/lightboxImmagini.jsp"%>

		<!-- Modal per la cancellazione -->


		<!-- Popup in caso di tentata rimozione -->
		<div id="rimuoviModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Sei sicuro?</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">Annulla</button>
						<button id="rimuoviButton" class="btn btn-danger">Rimuovi</button>
					</div>
				</div>
			</div>
		</div>
		<div id="esitoModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h4 id="title" class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<p id="text"></p>
					</div>
					<div class="modal-footer">
						<button id="ok" class="btn btn-info">OK</button>
					</div>
				</div>
			</div>
		</div>





		<script type="text/javascript">
			window.onload(loadModal());
		</script>







	</section>
	<%@ include file="includes/footer.jsp"%>


	<!-- MODAL VISUALIZZA FEEDBACK-->
	<div class="modal fade" id="vediFeedbackModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Visualizza feedbacks</h4>
				</div>
				
				<!-- body caricato dinamicamente con jquery -->
				<div class="modal-body" id="modalBody" style="overflow: scroll;">
				</div>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- MODAL INSERISCI FEEDBACK-->
	<div class="modal fade" id="inserisciFeedbackModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Inserisci feedback</h4>
				</div>
				<div class="modal-body">
					<div class="form-group" id="insertingFeedback">
						<label for="valutazioneFeedback">Valutazione:</label> <select
							class="form-control" id="valutazioneFeedback"
							name="valutazioneFeedback">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select> <label for="descrizioneFeedback">Descrizione:</label>
						<textarea style="overflow: auto; resize: none"
							class="form-control" rows="4" id="descrizioneFeedback"
							name="descrizioneFeedback" maxlength="50"></textarea>

					</div>
				</div>
				<div class="modal-footer">
					<!-- Nel footer è presente un col-sm-6 per il logger -->
					<!-- e un col-sm-6 per i due pulsanti inserisci e annulla -->
					<div class="formgroup col-sm-12">
						<div class="col-sm-6" id="insertLogger"></div>
						<div class="col-sm-6">
							<button type="button" class="btn btn-primary"
								id="buttonInserisci">Inserisci</button>
							<button type="button" class="btn btn-warning"
								data-dismiss="modal">Annulla</button>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>

	<!-- MODAL MODIFICA FEEDBACK-->
	<div class="modal fade" id="modificaFeedbackModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Modifica feedback</h4>
				</div>

				<form action="ModificaFeedbackServlet" method="POST">

					<div class="modal-body">
						<div class="form-group" id="changingFeedback">
							<label for="changingValutazione">Valutazione</label> <select
								class="form-control" id="changingValutazione"
								name="changingValutazione">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select> <label for="changingDescrizione">Descrizione</label>
							<textarea style="overflow: auto; resize: none"
								class="form-control" rows="4" id="changingDescrizione"
								name="changingDescrizione" maxlength="50" readonly></textarea>
						</div>
					</div>


					<div class="modal-footer">
						<!-- Nel footer è presente un col-sm-6 per il logger -->
						<!-- e un col-sm-6 per i due pulsanti modifica e annulla -->
						<div class="formgroup col-sm-12">
							<div class="col-sm-6" id="changeLogger"></div>
							<div class="col-sm-6">
								<button type="button" class="btn btn-primary"
									id="buttonModifica">Modifica</button>
								<button type="button" class="btn btn-warning"
									data-dismiss="modal">Annulla</button>
							</div>
						</div>
					</div>

				</form>

			</div>

		</div>
	</div>
	
				<%--Modal cancellaAccount --%>
		<div id="cancellaAccountModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Sei sicuro di voler cancellare questo account?</h4>
					</div>
					<div class="modal-body">
						<p>Cancellando questo account cancellerai anche i relativi annunci.</p>
						<input type="checkbox" id="cancellaFeedback" name="cancellaFeedback" value="true")>Seleziona per eliminare i feedback dell'account.<br>
					</div>
					<div class="modal-footer">
						<button type="button" id ="annulla" name="annulla" class="btn btn-success" data-dismiss="modal" >Annulla</button>
						<button type="button" id ="prosegui" name="prosegui" class="btn btn-success" >Prosegui</button>

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
						<h4 class="modal-title">Sei sicuro di voler cancellare questo account?</h4>
					</div>
					<div class="modal-body">
						<p>Invalidando l'account selezionato, quest'ultimo non potra accedere alla piattaforma per 7 giorni.</p>
						<input type="checkbox" id="cancellaFeedbackI" name="cancellaFeedbackI" value="true")>Seleziona per eliminare i feedback dell'account.<br>
					</div>
					<div class="modal-footer">
						<button type="button" id ="annullaI" name="annullaI" class="btn btn-success" data-dismiss="modal" >Annulla</button>
						<button type="button" id ="proseguiI" name="proseguiI" class="btn btn-success" >Prosegui</button>

					</div>
				</div>
			</div>
		</div>
	<script src="javascript/feedback/caricaModal.js"></script>
	<script src="javascript/feedback/modificaFeedback.js"></script>
	<script src="javascript/feedback/inserisciFeedback.js"></script>
</body>
</html>
<!-- AGGIORNATO 11/01/2017 -->