function caricaAnnunci(){
	$.ajax({type:"GET",
		url: "AnnunciRecenti",
		success: function(risposta){ //se la richiesta ha successo
			$('#content').html(risposta);
		},
		error: function (response) { //se la richiesta fallisce
			$('#content').html("Non ci sono annunci da visualizzare");
	    }
	});
}