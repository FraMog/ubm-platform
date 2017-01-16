<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, it.ubmplatform.profilo.*" %>
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
    <script type="text/javascript" src="http://botmonster.com/jquery-bootpag/jquery.bootpag.js"></script>
  	<script type="text/javascript" src="javascript/annunci/loadlightboxImmagini.js"></script>
 

  </head>
  <body>
  
  	<%if(session.getAttribute("user")==null) {%>
    	<%@ include file="includes/navbarNonLoggato.jsp" %>
    <%} else if(session.getAttribute("user").equals("admin")){%>
    	<%@ include file="includes/navbarAdmin.jsp" %>
    <%} else {%>
    	<%@ include file="includes/navbarLoggato.jsp" %>
    <%} %>
    <%@ include file="includes/sideBar.jsp" %>
    <%
    
      ArrayList <Profilo> profiliPertinenti = null;
	  try{
	  	profiliPertinenti = (ArrayList<Profilo>) request.getSession().getAttribute("listaProfili");
	  	request.getSession().removeAttribute("listaProfili");
	  } catch (Exception e ){
		  profiliPertinenti = null;
	  }
	  int size;
	  if(profiliPertinenti == null || profiliPertinenti.size() == 0){
		  size = 0;
	  } else 
		  size = profiliPertinenti.size();
    
    %>
    
    <section class="col-sm-10" id="section">

      
    <div id="cont_ordine" class="container-fluid" style="padding-left:0px;">


	<div class="col-xs-12 pull-right" id="divMostraSizeRisultatiRiserca" style="text-align:right;"><h4>Risultati della ricerca: <b><%=size%></b></h4></div>
 
   </div>
   
<%--ENDROW --%>





<%-- Il contenuto dei paginator che cambia dinamicamente --%>
<div id="dynamic_content"></div>
<%--L'indice paginator --%>
	<div id="show_paginator" class="pull-right"></div>
	
	<%!
	public void setter(String mail, HttpServletRequest request){
		request.getSession().setAttribute("emailToShow", mail);
	}
	%>


  <%for(int i=0; i<size;i++){%>
      
      <%if (i%5==0){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione --%>
   <div style="display:none;" class= "containerResearchResults" id='<%="containerResearchResults" + (i/5 + 1)%>' >
      <%} %>
      <div id="content" class="panel panel-default">
        <div class="panel-body risultato">
     
      
        <div class="row">
          <div class="col-xs-12 col-sm-2"><img src="img/profilo/<%if(profiliPertinenti.get(i).getFoto()!=null) %><%= profiliPertinenti.get(i).getFoto() %><%else {%>default_profile.PNG<%} %>" alt="Foto" class="img-responsive center-block modalImageClasse"></div>
          <div class="col-xs-12 col-sm-8">
          
            <h4>
            
            <a href='<%="VisualizzaProfiloServlet?emailToShow="+ profiliPertinenti.get(i).getEmail()%>'>
            <%=profiliPertinenti.get(i).getNome() + " " + profiliPertinenti.get(i).getCognome() %></a></h4>
            <p><%="di " + profiliPertinenti.get(i).getResidenza()%></p>
          </div>
          <div class="col-xs-12 col-sm-2 pull-right">
            <% 	java.sql.Date data=null;
            	if(profiliPertinenti.get(i).getDataNascita()!=null)
            		data= new java.sql.Date(profiliPertinenti.get(i).getDataNascita().getTime()); %>
            <p>Nato il: <%if (data!=null)%><%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(data).substring(0,10)%><%else {%>-<%} %></p>
          </div>
        </div>
        </div>
      </div>
      
      <%if (i%5==4 || i==(profiliPertinenti.size()-1)){ %> <%--ogni 5 elementi devo creare un nuovo div da mostrare in paginazione, oppure devo farlo quando sono arrivato all'ultimo elemento --%>
  </div>    
      <%} %>
      
    <%} %><%-- ENDFOR --%>
    
    
<script>
function loadBootpagAfterLoadingPage(){
  $('#show_paginator').bootpag({
      total: <%=size/(5+1)+1%>,
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
  
<script type="text/javascript">
window.onload(loadModal());
</script>        
          
       
   
          
</section>
    
 
  
    
    <%@ include file="includes/footer.jsp" %>
  </body>
</html>