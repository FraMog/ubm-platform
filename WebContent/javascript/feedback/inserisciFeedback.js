//all'attivamento del modal inserisci feedback
$('#inserisciFeedbackModal').on('show.bs.modal', function(){
	//svuoto il div del logger
	$('#insertLogger').text("");
	
	$('#buttonInserisci').on('click', function(){
		var valutazione = $('#valutazioneFeedback').val();
		var descrizione = $('#descrizioneFeedback').val();
		val emailR = $('#emailR').text();
		
		if(valutazione != null && descrizione != null && emailR != null){
			
			$.get("InserisciFeedbackServlet?valutazione=" + valutazione + "&descrizione=" + descrizione + "&emailR=" + emalR, function(text, status){
				
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