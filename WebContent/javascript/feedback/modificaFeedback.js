/**
 * http://usejsdoc.org/
 */

$("#modificaFeedbackModal").on('show.bs.modal', function(){
	//assegno l'evento che toglie il "non modificabile" se l'utente cambia testo
	$('#changingDescrizione').on('click', function(){
		$(this).attr("readonly", false);
	})
	
	//var emailR = $("#email").text();
	var emailR = "example@studenti.unisa.it";
	$.get("ModificaFeedbackServlet?tipo=richiedi&emailR=" + emailR, function(text,status){
		//ottengo il feedback con il parsing del json e setto la valutazione e la descrizione in base agli attributi
		var feedback = JSON.parse(text);
		$('#changingValutazione option[value=' + feedback.valutazione + ']').prop('selected', true);
		$("#changingDescrizione").text(feedback.descrizione);
	})
	
	
	
})