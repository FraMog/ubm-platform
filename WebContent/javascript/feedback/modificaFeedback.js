$("#modificaFeedbackModal").on('show.bs.modal', function(){
	
	//assegno l'evento che toglie il "non modificabile" se l'utente cambia testo
	$('#changingDescrizione').on('click', function(){
		$(this).attr("readonly", false);
	})

	var emailR = $("#emailR").text();
	

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
				//allargo la colonna da col-sm-6 a 9 per il logger 
				//perchè non ho il pulsante modifica (nascosto)
				//diminuisco invece quello per i pulsanti
				
				$('#buttonsChange').attr('class', 'col-sm-3');
				$('#changeLogger').attr('class', 'col-sm-9');
				
				$('#changeLogger').text("Impossibile recuperare il feedback da modificare!");
				$('#changingFeedback').hide();
				$('#buttonModifica').hide();
			}else if(text.state == "requesterror"){
				//allargo la colonna da col-sm-6 a 9 per il logger 
				//perchè non ho il pulsante modifica (nascosto)
				//diminuisco invece quello per i pulsanti
				
				$('#buttonsChange').attr('class', 'col-sm-3');
				$('#changeLogger').attr('class', 'col-sm-9');
				
				$('#changeLogger').text("Errore con la richiesta!");
				$('#changingFeedback').hide();
				$('#buttonModifica').hide();
			}else if(text.state == "nosession"){
				//allargo la colonna da col-sm-6 a 9 per il logger 
				//perchè non ho il pulsante modifica (nascosto)
				//diminuisco invece quello per i pulsanti
				
				$('#buttonsChange').attr('class', 'col-sm-3');
				$('#changeLogger').attr('class', 'col-sm-9');
				
				$('#changeLogger').text("Devi essere loggato al sistema!");
				$('#changingFeedback').hide();
				$('#buttonModifica').hide();
			}else if(text.state == "emailerror"){
				//allargo la colonna da col-sm-6 a 9 per il logger 
				//perchè non ho il pulsante modifica (nascosto)
				//diminuisco invece quello per i pulsanti
				
				$('#buttonsChange').attr('class', 'col-sm-3');
				$('#changeLogger').attr('class', 'col-sm-9');
				
				$('#changeLogger').text("Problema con il recupero dell'email!");
				$('#changingFeedback').hide();
				$('#buttonModifica').hide();
			}else{
				//tutto ok
				$('#changingValutazione option[value=' + text.valutazione + ']').prop('selected', true);
				$("#changingDescrizione").text(text.descrizione);

			}
		}else{
			//allargo la colonna da col-sm-6 a 9 per il logger 
			//perchè non ho il pulsante modifica (nascosto)
			//diminuisco invece quello per i pulsanti
			
			$('#buttonsChange').attr('class', 'col-sm-3');
			$('#changeLogger').attr('class', 'col-sm-9');
			
			$('#changeLogger').text("Problema con la richiesta!");
			$('#changingFeedback').hide();
			$('#buttonModifica').hide();
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


//l'evento per la pulizia alla chiusura del modal
$('#modificaFeedbackModal').on('hidden.bs.modal', function(){
	//svuoto il div del logger
	$('#changeLogger').text("");
	
})