<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, it.ubmplatform.annunci.*" %>

<%ArrayList<Annuncio> lista=(ArrayList<Annuncio>)request.getAttribute("lista"); %>

<%for(Annuncio a: lista){ %>
<div class="panel-body risultato" id="annunciHome">
	<img src="img/annunci/<%=a.getFoto()%>" alt="libro" class="img-responsive col-sm-2 modalImageClasse">
	<div class="col-sm-8">
    	<h4><a href="VisualizzaDettagliAnnuncio?annuncioID=<%= a.getId()%>"><%= a.getTitolo() %></a></h4>
    	<p><%= a.getDescrizione()%></p>
  	</div>
  	<div class="col-sm-2 pull-right">
    	<h4>Prezzo: <%=a.getPrezzo() %></h4>
    	<p>Data pubblicazione: <%= new java.text.SimpleDateFormat("dd-MM-yyyy").format(a.getDataPubblicazione())%> alle <%= new java.text.SimpleDateFormat("HH:mm:ss").format(a.getDataPubblicazione()).substring(0,8)%></p>
  	</div>
</div>
<%}%>