function cancellaAccount(email){
	if(email==null) alert("Non è possibile rimuovere l'account riprovare più tardi!")
	else{
		var conferma= confirm("Sei sicuro di voler rimuovere definitivamente l'account di " +  email  +"?"); //si richiede all admin se vuole eliminare davvero l'account
		if(conferma==false)alert("Operazione annullata correttamente")
		else{
			var eliminaFeedback= confirm("Vuoi eliminare anche i feedback creati da "+ email +"?");
			$.get("CancellaAccountServlet", { "email": email, "eliminaFeedback": eliminaFeedback}, function(valore){
				if(valore) {
					
				console.log("sono prima del refresh",valore);
			location.reload(true);
				}
				else{
					alert("Non è possibile eliminare l'account selezionato si è verificato un errore.")
				}
			}).fail(function() {
			    alert("Non è possibile eliminare l'account selezionato si è verificato un errore.");
			});
			
		}
	}
	
}


function invalidaAccount(email){
	if(email==null) alert("Non è possibile invalidare l'account riprovare più tardi!")
	else{
		var conferma= confirm("Sei sicuro di voler invalidare definitivamente l'account di " +  email  +"?"); //si richiede all admin se vuole eliminare davvero l'account
		if(conferma==false)alert("Operazione annullata correttamente")
		else{
			var eliminaFeedback= confirm("Vuoi eliminare anche i feedback creati da "+ email +"?");
			$.get("InvalidaAccountServlet", { "email": email, "eliminaFeedback": eliminaFeedback}, function(valore){
				if(valore) {
				console.log("sono prima del refresh",valore);
			location.reload(true);
				}
				else{
					alert("Non è possibile invalidare l'account selezionato si è verificato un errore.")
				}
			}).fail(function() {
			    alert("Non è possibile invalidare l'account selezionato si è verificato un errore.");
			});
			
		}
	}
	
}
