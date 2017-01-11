function cancellaAccount(email){
	$('#cancellaAccountModal').modal("show");
	var eliminaFeedback=false;
	$(":checkbox").click(function(){
		var cancellaFeedback = $(this).val();
		if(cancellaFeedback=="true") eliminaFeedback=true;
	});
	$("#prosegui").click(function () {
		console.log("eliminaFeedback"+eliminaFeedback);

		$.get("CancellaAccountServlet", { "email": email, "eliminaFeedback": eliminaFeedback}, function(valore){
			if(valore=="true") {

				console.log("sono prima del refresh",valore);
				location.reload(true);
			}
			else{
				alert("Non è possibile eliminare l'account selezionato si è verificato un errore.")
			}
		}).fail(function() {
			alert("Non è possibile eliminare l'account selezionato si è verificato un errore.");
		});
	});
}	



function invalidaAccount(email){
	$('#invalidaAccountModal').modal("show");
	var eliminaFeedback=false;
	$(":checkbox").click(function(){
		var cancellaFeedback = $(this).val();
		if(cancellaFeedback=="true") eliminaFeedback=true;
	});
	$("#proseguiI").click(function () {
		console.log("eliminaFeedback"+eliminaFeedback);

		$.get("InvalidaAccountServlet", { "email": email, "eliminaFeedback": eliminaFeedback}, function(valore){
			if(valore=="true") {
				console.log("sono prima del refresh",valore);
				location.reload(true);
			}
			else{
				alert("Non è possibile invalidare l'account selezionato si è verificato un errore.")
			}
		}).fail(function() {
			alert("Non è possibile invalidare l'account selezionato si è verificato un errore.");
		});
	});
}
