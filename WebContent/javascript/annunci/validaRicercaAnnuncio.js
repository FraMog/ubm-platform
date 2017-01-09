/**
 * È necessario validare il form di ricerca, qualora il browser non supporti gli attributi pattern e title 
 * di HTML5, tramite Javascript.
 */

function validaFormRicerca(idFormDaValidare){
	$("#" + idFormDaValidare).submit(function( event ) {
		var regexpTitolo= new RegExp("[a-zA-Z]{1}[a-zA-Z0-9 ]{0,49}");  		
		if(!regexpTitolo.test($("#" + idFormDaValidare + " input:first").val())){
				alert("Errore nella ricerca\nIl titolo deve contenere tra 1 e 50 caratteri alfanumerici");
				event.preventDefault();
		}
    });	
}	
	
	  
	
	  
	  

