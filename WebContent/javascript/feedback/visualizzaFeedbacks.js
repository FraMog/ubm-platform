/**
 * http://usejsdoc.org/
 */

//al caricamento avvenuto del documento (la parte del modal)
//invio una richiesta alla servlet VisualizzaFeedbackServlet che
//si occupa di ottenere tutti i feedback data un'email
//la risposta (text) verrà data in json, convertita, e verra chiamata una funzione
//che si occupa di stilare i feedback nel modal
$(document).ready(function(){
	var emailR = $("#email").text();
	$.get("VisualizzaFeedbackServlet?emailR=" + emailR, function(text, status){
		alert("Text = " + text + " and status = " + status);
	})
})