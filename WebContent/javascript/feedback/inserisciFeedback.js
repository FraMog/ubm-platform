//all'attivamento del modal inserisci feedback
$('#inserisciFeedbackModal').on('show.bs.modal', function(){
	//svuoto il div del logger
	$('#insertLogger').text("");
	
	var emailR = $("#emailR").text();
	
	//controllo se il feedback esiste
	checkNotExistsFeedback(emailR);
	
	$('#buttonInserisci').on('click', function(){
		var valutazione = $('#valutazioneFeedback').val();
		var descrizione = $('#descrizioneFeedback').val();
			
		if(valutazione != null && descrizione != null && emailR != null){
			
			$.get("InserisciFeedbackServlet?valutazione=" + valutazione + "&descrizione=" + descrizione + "&emailR=" + emailR, function(text, status){
				
				if(status == 'success'){
					
					//controllo lo stato dell'inserimento
					var state = text.state;
					
					switch(state){
						case 'ok':
							window.location.reload(true);
							break;
						case 'feedbackerror':
							$('#insertLogger').text("Problema con l'inserimento nel database!");
							break;
						case 'inputerror':
							$('#insertLogger').text("Problema con gli input!");
							break;
						case 'valerror':
							$('#insertLogger').text("Problema con la valutazione!");
							break;
						case 'nosession':
							$('#insertLogger').text("Devi essere loggato al sistema!");
							break;
					
					}
				}else $('#insertLogger').text("Problema di connessione al server!");
			})
		}else $('#insertLogger').text("Errore con i parametri!");
	})
})


function checkNotExistsFeedback(emailR){
	$.get("ModificaFeedbackServlet?tipo=richiedi&emailR=" + emailR, function(text,status){
		//ottengo il feedback con il parsing del json e setto la valutazione e la descrizione in base agli attributi

		if(status == 'success'){
			//tutto ok, non ho trovato il feedback
			if(text.state != "error"){
				//qualche problema
				$('#insertLogger').text("E' stato riscontrato un problema! Hai già inserito un feedback a questo utente?");
				$('#insertingFeedback').hide();
				$('#buttonInserisci').hide();
			}
		}else{
			$('#insertLogger').text("Problema con la richiesta!");
			$('#insertingFeedback').hide();
			$('#buttonInserisci').hide();
		}
	})
}