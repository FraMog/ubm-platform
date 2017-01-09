<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="it.ubmplatform.profilo.Profilo"%>
<%@page import="it.ubmplatform.annunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import= "javax.servlet.RequestDispatcher" %>
<%@page import= "java.io.IOException" %>
<%@page import="java.text.SimpleDateFormat"%>



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
		
	    <script type="text/javascript" src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
  	<script type="text/javascript" src="javascript/annunci/loadlightboxImmagini.js"></script>
    <script src="javascript/amministrazione/rimuoviAnnuncio.js"></script>
    <script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
    <script src="javascript/annunci/rimuoviAnnuncioUtente.js"></script>
    <script src="javascript/annunci/settaIdAnnuncioDaRimuovereAmministratore.js"></script>
     <script src="javascript/annunci/settaIdAnnuncioDaRimuovereUtente.js"></script>
	
</head>
<body>
	<%@ include file="includes/navbarLoggato.jsp"%>
	<%@ include file="includes/sideBar.jsp"%>
	<%
		Profilo profileToShow = null;
		try{
			profileToShow = (Profilo) request.getSession().getAttribute("profileToShow");
		} catch (Exception e){
			response.sendRedirect("index.jsp");
		}
		String foto;
		if(profileToShow.getFoto()!=null)
			foto = profileToShow.getFoto();
		else
			foto = "img/profilo/default_profile.PNG";
		
		
		
	%>


	
	
	<section class="col-sm-10" id="section">
		<div class="row">
			<div class="col-sm-2">
				<img id="profile_picture" class="img-responsive"
					src="<%=foto %>" alt="Foto di <%=profileToShow.getNome() %>"
					title="Giovanni Ciampi" /> <br>
					<form  action="modificaProfilo.jsp" >
						<input type="submit" class="btn btn-info" value="Modifica I Tuoi Dati"  />
					</form>
					
			</div>
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
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
						dataNascita = f.format(profileToShow.getDataNascita());
					}
					

					String residenza = profileToShow.getResidenza();
					if (residenza == null || residenza == "")
						residenza = " -";

					String interessi = profileToShow.getInteressi();
					if (interessi == null || interessi == "" )
						interessi = " -";
				%>


				<h1><%=nomeCognome%></h1>
				<h3>
					E-mail: <a href="<%="mailto:" + email%>"><%=email%></a>
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
						src="img/feedback-stars.png" alt="valutazione feedback"
						title="feedback average" />
				</div>


				<div class="col-sm-12" style="padding-left: 0px; padding-top: 0px">
					<h4>Hai ricevuto 7 valutazioni:</h4>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<h4>
							<a href="#" style="color: black"
								title="Vai al profilo di questo utente"
								style="padding-left:0px; padding-bottom: 0px">Marco: <small>Giudizio:5/5</small></a>
						</h4>
					</div>

					<div class="col-sm-12">
						<p>Utente serio e preciso.</p>
					</div>
					<div class="col-sm-12">
						<h4>
							<a href="#" style="color: black"
								title="Vai al profilo di questo utente"
								style="padding-left:0px; padding-bottom: 0px">Antonio: <small>Giudizio:4/5</small></a>
						</h4>
					</div>

					<div class="col-sm-12">
						<p>Libro perfetto.</p>
					</div>

					<div class="col-sm-12">
						<h5>
							<a href="#">Visualizza tutti i tuoi Feedback</a>
						</h5>
					</div>
				</div>

			</div>

			<div class="col-sm-12">
				<hr>
				<h2>I Tuoi Annunci Online:</h2>
			</div>
		</div>
<!-- visualizzo gli annunci -->
  <%
   
   
   ArrayList<Annuncio> annunciPertinenti;
   int length;
   try{
	   annunciPertinenti = (ArrayList<Annuncio>) request.getAttribute("listaAnnunci");
	   length = annunciPertinenti.size();
	   
   } catch(Exception e){
	   annunciPertinenti = new ArrayList<Annuncio>();
	   length = 0;
	   
	   System.out.println(" len = "+ length);
   }
  %>
   
  
<!-- Codice preso da RicercaAnnuncio di Francesco Mogavero ********************+ -->



<%-- Il contenuto dei paginator che cambia dinamicamente --%>
<div id="dynamic_content"></div>
<%--L'indice paginator --%>
	<div id="show_paginator" class="pull-right"></div>


  <%for(int i=0; i<length;i++){%>
      
      <%if (i%5==0){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione --%>
   <div style="display:none;" class= "containerResearchResults" id='<%="containerResearchResults" + (i/5 + 1)%>' >
      <%} %>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
     
          <div class="row">
          <div class="col-xs-4 col-md-8"></div>
           <button onmouseover="this.style.color='white';" onmouseleave="this.style.color='#5bc0de';" style="color: #5bc0de;" class="btn btn-info btn-outline col-md-2" type="button" onclick="window.location.href='VisualizzaDettagliAnnuncio?annuncioID=<%=annunciPertinenti.get(i).getId()%>'">Modifica</button>
            <button class="btn btn-info col-md-2" type="button"  data-toggle="modal" data-target="#rimuoviModal" onclick="settaIdAnnuncioDaRimuovereUtente('<%=annunciPertinenti.get(i).getId()%>')">Elimina</button>
        </div>
       
        <div class="row">
          <div class="col-xs-12 col-sm-2"><img src="<%=annunciPertinenti.get(i).getFoto() %>" alt="Foto" class="img-responsive center-block modalImageClasse"></div>
          <div class="col-xs-12 col-sm-8">
          
            <h4><a href='<%="VisualizzaDettagliAnnuncio?annuncioID=" + annunciPertinenti.get(i).getId()%>'><%= annunciPertinenti.get(i).getTitolo()%></a></h4>
            <p><%= annunciPertinenti.get(i).getDescrizione()%></p>
          </div>
          <div class="col-xs-12 col-sm-2 pull-right">
            <h4>Prezzo: <%= annunciPertinenti.get(i).getPrezzo()%></h4>
            <% java.sql.Date data= annunciPertinenti.get(i).getDataPubblicazione(); %>
            <p>Data pubblicazione: <%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(data).substring(0,10)%></p>
          </div>
        </div>
        </div>
      </div>
      
      <%if (i%5==4 || i==(annunciPertinenti.size()-1)){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione, oppure devo farlo quando sono arrivato all'ultimo elemento --%>
  </div>    
      <%} %>
      
    <%} %><%-- ENDFOR --%>
    
    <script>
function loadBootpagAfterLoadingPage(){
  $('#show_paginator').bootpag({
      total: <%=annunciPertinenti.size()/(5+1)+1%>,
      page: 1,
      maxVisible: 5
  }).on('page', function(event, num)
  {
     $("#dynamic_content").html($("#containerResearchResults" + num).html()); 
     loadModal();
  });
  
  $("#dynamic_content").html($("#containerResearchResults1").html()); 
}
  window.onload(loadBootpagAfterLoadingPage());
	  
  </script> 
    
  
  
<%@include file="includes/lightboxImmagini.jsp" %>
        
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
			         	<button id="rimuoviButton"  class="btn btn-danger">Rimuovi</button>
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
</body>
</html>