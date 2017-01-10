/**
 * È necessario validare il form di modifica di un annuncio, qualora il browser non supporti 
 * gli attributi pattern e title di HTML5, tramite Javascript.
 */

function validaFormModificaAnnuncio(){
 $("#annuncio").submit(function(event){


	var regExpTitolo=new RegExp("^([a-zA-Z]{1})([a-zA-Z0-9 ]{0,49})$");
		if(!regExpTitolo.test($("#titolo").val())){
			$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
			$('#campiNonCorrettiModificaAnnuncioTesto').html('Errore nella Modifica!<br />Errore in titolo!<br />Inserire una stringa alfanumerica di lunghezza 1-50');
			event.preventDefault();
			return;
		}
	  
     if(isNaN($("#prezzo").val())|| parseFloat($("#prezzo").val())>=1000){
    	 	$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
			$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />Il prezzo deve essere un valore numerico nel formato xxx o xxx.y<br />Prezzo inserito:" + $("#prezzo").val());
			  event.preventDefault();
			  return;
	}
     
    
	var regexpCondizioni= new RegExp("^$|^([a-zA-Z0-9 ]{0,20})$"); 
		if(!regexpCondizioni.test($("#condizioni").val())){
			$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
			$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />Le condizioni devono essere nel formato deve essere una stringa alfanumerica di lunghezza 0-20");
			  event.preventDefault();
			  return;
		} 	

	if(!$("#isbn").prop('disabled')){	
	var regexpISBN = new RegExp("^$|^[a-zA-Z0-9]{13}$");
	if(!regexpISBN.test($("#isbn").val())){
		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
		$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />L'ISBN deve essere una stringa alfanumerica di lunghezza 0 o di lunghezza 13");
		  event.preventDefault();
		  return;
	}
	}
	
	if(!$("#autoreLibro").prop('disabled')){	
	var regexpAutore = new RegExp("^$|^[a-zA-Z]{1}[a-zA-Z ]{0,19}$");
	if(!regexpAutore.test($("#autoreLibro").val())){
		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
		$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />L'autore deve essere una stringa alfabetica di lunghezza 0-20");
		  event.preventDefault();
		  return;
	}
	}

	if(!$("#materia").prop('disabled')){	
	var regexpMateria = new RegExp("^$|^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,39}$");
	if(!regexpMateria.test($("#materia").val())){
		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
		$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />La materia deve essere una stringa alfanumerica di lunghezza 0-4");
		  event.preventDefault();
		  return;
	}
	}
	
	
	var regexpDescrizione = new RegExp("^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,99}$");
	if(!regexpDescrizione.test($("#descrizione").val())){
		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
		$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />La descrizione deve essere una stringa alfanumerica di lunghezza 1-100");
		  event.preventDefault();
		  return;
	} 	
	
	
	if(isNaN($("#edizione").val())){
 	 		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
			$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />L'edizione deve esssere un valore numerico!");
			  event.preventDefault();
			  return;
	}
	
	 
	var ext = $('#foto').val().split('.').pop().toLowerCase();
	if($.inArray(ext, ['gif','png','jpg','jpeg','']) == -1) {
		$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
		$('#campiNonCorrettiModificaAnnuncioTesto').html("Errore nella Modifica!<br />L'estensione dell'immagine non è riconosciuta");
		event.preventDefault();
	    return;
	}
	
	
	
	if ( window.FileReader && window.File && window.FileList && window.Blob ){
		if($("#foto")[0].files[0].size>10*1024*1024){
			$('#campiNonCorrettiModificaAnnuncioModal').modal('show');
			$('#campiNonCorrettiModificaAnnuncioTesto').html("Il file può pesare al massimo 10MB");
			event.preventDefault();
			return;
		}
	} else console.log( "Il tuo browser non supporta la validazione dell'immagine" );

 });
}

