/**
 * È necessario validare il form di modifica di un annuncio, qualora il browser non supporti 
 * gli attributi pattern e title di HTML5, tramite Javascript.
 */

function validaFormModificaAnnuncio(){
 $("#annuncio").submit(function(event){


	var regExpTitolo=new RegExp("^([a-zA-Z]{1})([a-zA-Z0-9 ]{0,49})$");
		if(!regExpTitolo.test($("#titolo").val())){
			alert("Errore nella Modifica!\nErrore in titolo!\nInserire una stringa alfanumerica di lunghezza 1-50");
			event.preventDefault();
			return;
		}
	  
     var valorePrezzo=parseFloat($("#prezzo").val());
     if(isNaN(valorePrezzo)|| valorePrezzo>=1000){
			  alert("Errore nella Modifica!\nIl prezzo deve essere nel formato xxx o xxx.y\nPrezzo inserito:" + $("#prezzo").val());
			  event.preventDefault();
			  return;
	}
     
    
	var regexpCondizioni= new RegExp("^$|^([a-zA-Z0-9 ]{0,20})$"); 
		if(!regexpCondizioni.test($("#condizioni").val())){
			  alert("Errore nella Modifica!\nLe condizioni devono essere nel formato deve essere una stringa alfanumerica di lunghezza 0-20");
			  event.preventDefault();
			  return;
		} 	

	if(!$("#isbn").prop('disabled')){	
	var regexpISBN = new RegExp("^$|^[a-zA-Z0-9]{13}$");
	if(!regexpISBN.test($("#isbn").val())){
		  alert("Errore nella Modifica!\nL'ISBN deve essere una stringa alfanumerica di lunghezza 0 o di lunghezza 13");
		  event.preventDefault();
		  return;
	}
	}
	
	if(!$("#autoreLibro").prop('disabled')){	
	var regexpAutore = new RegExp("^$|^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,19}$");
	if(!regexpAutore.test($("#autoreLibro").val())){
		  alert("Errore nella Modifica!\nL'autore deve essere una stringa alfanumerica di lunghezza 0-20");
		  event.preventDefault();
		  return;
	}
	}

	if(!$("#materia").prop('disabled')){	
	var regexpMateria = new RegExp("^$|^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,39}$");
	if(!regexpMateria.test($("#materia").val())){
		  alert("Errore nella Modifica!\nLa materia deve essere una stringa alfanumerica di lunghezza 0-40");
		  event.preventDefault();
		  return;
	}
	}
	
	
	var regexpDescrizione = new RegExp("^$|^[a-zA-Z]{1}[a-zA-Z0-9 ]{0,99}$");
	if(!regexpDescrizione.test($("#descrizione").val())){
		  alert("Errore nella Modifica!\nLa descrizione deve essere una stringa alfanumerica di lunghezza 0-100");
		  event.preventDefault();
		  return;
	} 	
	 
	var ext = $('#foto').val().split('.').pop().toLowerCase();
	if($.inArray(ext, ['gif','png','jpg','jpeg','']) == -1) {
		alert("L'estensione dell'immagine non è riconosciuta");
		event.preventDefault();
	    return;
	}
	
	
	
	if ( window.FileReader && window.File && window.FileList && window.Blob ){
		if($("#foto")[0].files[0].size>10*1024*1024){
			alert('Il file può pesare al massimo 10MB');
			event.preventDefault();
			return;
		}
	} else console.log( "Il tuo browser non supporta la validazione dell'immagine" );

 });
}

