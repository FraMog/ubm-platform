//sull'attributo dell'id vediFeedbackModal, quando accade l'evento "show"
//esegui quella funzione
$('#vediFeedbackModal').on('show.bs.modal', function(e){
	//ottengo il link del file contenente il caricamento dell'html con contenuto remoto
	var link = $(e.relatedTarget);

	//nell'attributo href ho la pagina html in cui carico dinamicamente
	//i feedback presenti nel database
	//trovo quindi l'attributo con la classe modal-body e 
	//ci carico all'interno il contenuto della pagina suddetta
	$(this).find('.modal-body').load(link.attr('href'));
})
