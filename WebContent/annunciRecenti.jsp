<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, it.ubmplatform.annunci.*" %>

<%ArrayList<Annuncio> lista=(ArrayList<Annuncio>)request.getAttribute("lista"); %>

<%for(Annuncio a: lista){ %>
<div class="panel-body risultato">
	<img src="img/annunci/<%=a.getFoto()%>" alt="libro" class="img-responsive col-sm-2">
	<div class="col-sm-8">
    	<h4><a href="VisualizzaDettagliAnnuncio?annuncioID=<%= a.getId()%>"><%= a.getTitolo() %></a></h4>
    	<p><%= a.getDescrizione()%></p>
  	</div>
  	<div class="col-sm-2 pull-right">
    	<h4>Prezzo: <%=a.getPrezzo() %></h4>
    	<p>Data pubblicazione: <%= a.getDataPubblicazione()%></p>
  	</div>
</div>
<%}%>