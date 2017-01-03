/**
 * Questa funzione serve a sapere quale annuncio (ovvero quale id) è stato selezionato per essere eliminato 
 * da parte dell'amministratore, settando la funzione che deve essere eseguita con il corretto id per la 
 * rimozione dello stesso annuncio
 */


  function settaIdAnnuncioDaRimuovereAmministratore(id){
	  
	  $("#rimuoviButton").click(function(){
		  rimuovi(id);//funzione definita in javascript/amministrazione/rimuoviAnnuncio.js
	  });
  }