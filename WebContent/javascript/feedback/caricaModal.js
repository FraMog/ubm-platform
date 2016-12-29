/**
 * http://usejsdoc.org/
 */

//sull'attributo dell'id vediFeedbackModal, quando accade l'evento "show"
//esegui quella funzione
$("#vediFeedbackModal").on("show.bs.modal", function(e){
	//ottengo il link del file contenente il caricamento dell'html con contenuto remoto
	var link = $(e.relatedTarget);
	
	//trova la classe modal-body e caricaci il contenuto dell'attributo href (il file suddetto)
	$(this).find(".modal-body").load(link.attr("href"));
})