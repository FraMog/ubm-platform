$("#modificaFeedbackModal").on('show.bs.modal', function(){
	//svuoto il div del logger
	$('#changeLogger').text("");

	//assegno l'evento che toglie il "non modificabile" se l'utente cambia testo
	$('#changingDescrizione').on('click', function(){
		$(this).attr("readonly", false);
	})

	//var emailR = $("#email").text();
	var emailR = "marco@unisa.it";

	//recupero il feedback da modificare e lo stampo a video
	recuperaFeedback(emailR);

	//assegno l'evento dell'onclick
	$('#buttonModifica').on('click', function(){
		var valutazione = $('#changingValutazione').val();
		var descrizione = $('#changingDescrizione').val();

		if(valutazione != null && descrizione != null){
			modificaFeedback(valutazione, descrizione, emailR);
		}else $('#changeLogger').text("Errore con i parametri!");
	})


})




function recuperaFeedback(emailR){
	$.get("ModificaFeedbackServlet?tipo=richiedi&emailR=" + emailR, function(text,status){
		//ottengo il feedback con il parsing del json e setto la valutazione e la descrizione in base agli attributi

		if(status == 'success'){
			if(text.state == "error"){
				$('#changeLogger').text("Impossibile recuperare il feedback da modificare!");

			}else if(text.state == "requesterror"){
				$('#changeLogger').text("Errore con la richiesta!");

			}else if(text.state == "nosession"){

				$('#changeLogger').text("Devi essere loggato al sistema!");

			}else{

				$('#changingValutazione option[value=' + text.valutazione + ']').prop('selected', true);
				$("#changingDescrizione").text(text.descrizione);
				done = true;
			}
		}else{
			$('#changeLogger').text("Problema con la richiesta!");
		}
	})
}

function modificaFeedback(valutazione, descrizione, emailR){
	$.get("ModificaFeedbackServlet?tipo=modifica&valutazione=" + valutazione + "&descrizione=" + descrizione + "&emailR=" + emailR, function(text, status){

		if(status == 'success'){
			//controllo lo stato dell'inserimento
			var state = text.state;

			switch(state){
			case 'ok':
				window.location.reload(true);
				break;
			case 'feedbackerror':
				$('#changeLogger').text("Problema con l'inserimento nel database!");
				break;
			case 'inputerror':
				$('#changeLogger').text("Problema con gli input!");
				break;
			case 'valerror':
				$('#changeLogger').text("Problema con la valutazione!");
				break;
			case 'nosession':
				$('#changeLogger').text("Devi essere loggato al sistema!");
				break;

			}

		}else $('#changeLogger').text("Problema di connessione al server!");
	})
}