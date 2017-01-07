function confrontoCodice(){
	console.log("sono nelllo script");
	alert("ssript");
				//var codiceInserito=document.getElementById("codiceInserito").value;
				//var codiceInviato=document.getElementById("codiceInviato").value;
				var email=document.getElementById("email").value;
				var password=document.getElementById("password").value;
				//console.log("sono nelllo script",codiceInserito,codiceInviato,email);
				//if(codiceInserito==codiceInviato){
					$.get("controlloCodiceServlet", { "email": email, "password": password}, function(valore){
						if(valore=="true") {
							
						console.log("sono prima del redirect",valore);
						window.location = "registrazioneEffettuata.jsp";
						}
						else{
							alert("Non è possibile cambiare lo stato dell'account selezionato si è verificato un errore."+valore)
						}
					}).fail(function() {
					    alert("Non è possibile cambiare lo stato dell'account selezionato si è verificato un errore."+valore);
					});
				//}
				//else{
				//	alert("Il codice inserito è errato riprova la registrazione.")
				//	window.location("registrazione.jsp")
				//}
}
        		   		