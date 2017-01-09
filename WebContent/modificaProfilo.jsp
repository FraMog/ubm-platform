<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@page import="it.ubmplatform.profilo.Profilo" %>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

<% 
	Profilo profileToUpdate = null;
	try{
		profileToUpdate = (Profilo) request.getSession().getAttribute("profileToShow");
		if(profileToUpdate == null) throw new Exception();
	} catch (Exception e){
		response.sendRedirect("index.jsp");
	}
	
	String fotoPath = "img/profilo/default_profile.PNG";
	if(profileToUpdate.getFoto()!=null)
		fotoPath = profileToUpdate.getFoto();
	
%>
  <body>
  
   <%if(session.getAttribute("user")==null) {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} else if (session.getAttribute("user").equals("admin")) {%>
	<%@ include file="includes/navbarAdmin.jsp" %>
	<%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
    
    <section class="col-sm-12" id="section">
  
      <div class="col-sm-12">
       	<div class="panel panel-default">
       		<div class="panel-body">
       			<form id="formProfilo" class="form-horizontal" action="ModificaProfiloServlet" method="post" onclick="">
       				<div class="form-group col-sm-2">
       					<img id="foto" class="img-responsive" src="<%=fotoPath %>" style="width:150px"/>
	    				<label class="btn btn-info btn-file" style="margin-top:5px;margin-left:20px">Foto profilo<input class="btn btn-primary" type="file" name="img" id="img" accept=".jpg,.png,.jpg,.jpeg" style="display:none"/></label>
	    				<script type="text/javascript">
		    				function readURL(input) {
		    				    if (input.files && input.files[0]) {
		    				        var reader = new FileReader();	
		    				        reader.onload = function (e) {
		    				            $('#foto').attr('src', e.target.result);
		    				        }	
		    				        reader.readAsDataURL(input.files[0]);
		    				    }
		    				}	
		    				$("#img").change(function(){
		    				    readURL(this);
		    				});
	    				</script>
	    			</div>
	    			<div class="col-sm-10">
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="nome">Nome: *</label>
	    				<div class="col-sm-8"><input class="form-control" type="text" name="nome" value="<%=profileToUpdate.getNome() %>" id="nome" required="required" pattern="[A-Z a-z] {1-20}" title="Il nome deve contenere al più 20 lettere"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="cognome">Cognome: *</label>
	    				<div class="col-sm-8"><input class="form-control" type="text" name="cognome" value="<%=profileToUpdate.getCognome() %>" id="cognome" required="required" pattern="[A-Z a-z] {1-20}" title="Il cognome deve contenere al più 20 lettere"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="email">E-mail: </label>
	    				<div class="col-sm-8"><input class="form-control" type="text" name="emailToShow" id="emailToShow" readonly="readonly" value="<%=profileToUpdate.getEmail()%>"/></div>
	    			</div>
	    			<div class="form-group">
						<label class="control-label col-sm-2" for="oldPassword">Vecchia Password *</label>
						<div class="col-sm-8">
							<input class="form control" type="password" name="oldPassword" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}" title= "8-20 caratteri, almeno una maiuscola, almeno un simbolo">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="newPassword">Nuova Password *</label>
						<div class="col-sm-8">
							<input class="form control" type="password" name="newPassword" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="checkPassword">Conferma Password *</label>
						<div class="col-sm-8">
							<input class="form control" type="password" name="checkPassword" pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}">
						</div>
					</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="tel">Telefono: </label>
	    				<div class="col-sm-8"><input class="form-control" type="text" name="telefono" value="<%=profileToUpdate.getTelefono() %>" id="telefono" pattern="[0-9]{10}" title="Questo campo deve contenere 10 numeri"/></div>
	    			</div>
	    			<% 
	    			String dataNascita = " -";

					if (profileToUpdate.getDataNascita() != null) {
						SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
						dataNascita = f.format(profileToUpdate.getDataNascita());
					}
					%>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="data">Data di nascita: </label>
	    				<div class="col-sm-8"><input class="form-control" type="text" value="<%=dataNascita%>"  name="data" id="data"/></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="interessi">Interessi: </label>
	    				<div class="col-sm-10"><textarea class="form-control" name="interessi" id="interessi"  form="formProfilo" rows="3" cols="50" maxlength="100" title="Massimo 100 caratteri"><%=profileToUpdate.getInteressi() %></textarea></div>
	    			</div>
	    			<div class="form-group">
	    				<label class="control-label col-sm-2" for="residenza">Residenza: </label>
	    				<div class="col-sm-8"><input class="form-control" type="text" name="residenza" value="<%=profileToUpdate.getResidenza() %>" id="residenza" pattern="[A-Za-z]{0-40}" title="Massimo 40 caratteri"/></div>
	    			</div>
    					<div class="col-sm-2">
								<button class="btn btn-warning btn-lg" type="button" >
								<a style="outline:none; text-decoration:none; color:white; hover:none" href="index.jsp">ANNULLA</a> </button>
								
							</div>
							<div class="col-sm-2">
								<button class="btn btn-success btn-lg" type="submit">SALVA</button>
							</div>
							<div class="col-sm-2">
								<button class="btn btn-danger btn-lg" type="button"
									data-toggle="modal" data-target="#disattivaModal">DISATTIVA
									PROFILO</button>
								<!-- Popup in caso di tentata disattivazione -->
								<div id="disattivaModal" class="modal fade" role="dialog">
									<div class="modal-dialog">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">Giovanni, sei sicuro di voler
													disattivare il tuo profilo?</h4>
											</div>
											<div class="modal-body">
												<p>Cliccando su Disattiva Profilo tutti i tuoi dati
													(Profilo, annunci, etc), eccetto i Feedback che hai
													fornito, non saranno più visibili da nessuno sulla
													piattaforma. Tuttavia, ti basterà effettuare nuovamente il
													login per riattivare il tuo profilo, ritrovando tutti i
													tuoi vecchi dati.</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-success"
													data-dismiss="modal">Annulla</button>
												<a href="DisattivaProfilo" class="btn btn-danger" >Disattiva Profilo</a>
											</div>
										</div>

									</div>
								</div>
							</div>
    				</div>
				</form>
       		</div>
       	</div>
      </div>
    </section>
    <%@ include file="includes/footer.jsp" %>
  
  </body>
</html>