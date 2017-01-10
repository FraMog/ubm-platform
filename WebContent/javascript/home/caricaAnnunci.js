function caricaAnnunci(){
	$.ajax({type:"GET",
		url: "AnnunciRecenti",
		success: function(risposta){ //se la richiesta ha successo
			//creo il modal con la comunicazione
			$('#title').html("Operazione eseguita");
			$('#text').html("La rimozione è stata eseguita con successo");
			$('#ok').click(function(){ //imposto l'azione associata al bottone
				window.location.href='index.jsp';
			});
			$('#esitoModal').modal('show'); //mostro il modal
			$("#esitoModal").on("hidden.bs.modal", function () {
				window.location.href='index.jsp';
				});
		},
		error: function (response) { //se la richiesta fallisce
			//creo il modal con la comunicazione
			$('#title').html("Operazione non eseguita");
			$('#text').html("La rimozione ha riscontrato dei problemi: "+ response.responseText); //prendo il messaggio di errore inviato dalla servlet
			$('#ok').click(function(){ //imposto l'azione associata al bottone
				$('#esitoModal').modal('hide');
			});
			$('#esitoModal').modal('show'); //mostro il modal
	    }
	});
}