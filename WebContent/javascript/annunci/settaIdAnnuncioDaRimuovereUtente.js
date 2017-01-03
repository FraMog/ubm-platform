/**
 * Questa funzione serve a sapere quale annuncio (ovvero quale id) è stato selezionato per essere eliminato 
 * da parte dell'utente che lo ha inserito, settando la funzione che deve essere eseguita con il corretto id per la 
 * rimozione dello stesso annuncio
 */


  function settaIdAnnuncioDaRimuovereUtente(id){
	   $("#rimuoviButton").click(function(){
		  rimuoviAnnuncioUtente(id);//funzione definita in javascript/annunci/rimuoviAnnuncioUtente.js
		  
	  });
  }