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
				$('#cancellaAccountModal').modal("hide");
				$('#operazioneFallitaModal').modal("show");
			}
		}).fail(function() {
			$('#cancellaAccountModal').modal("hide");
			$('#operazioneFallitaModal').modal("show");
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
				$('#invalidaAccountModal').modal("hide");
				$('#operazioneFallitaModal').modal("show");
			}
		}).fail(function() {
			$('#invalidaAccountModal').modal("hide");
			$('#operazioneFallitaModal').modal("show");
		});
	});
}




//metodi per visualizzaprofiloAltro

function cancellaAccountA(email){
	console.log("dentro");
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
				location.replace("homePageAdmin.jsp");
			}
			else{
				$('#cancellaAccountModal').modal("hide");
				$('#operazioneFallitaModal').modal("show");
			}
		}).fail(function() {
			$('#cancellaAccountModal').modal("hide");
			$('#operazioneFallitaModal').modal("show");
		});
	});
}	

function invalidaAccountA(email){
	console.log("dentro");
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
				location.replace("homePageAdmin.jsp");
			}
			else{
				$('#invalidaAccountModal').modal("hide");
				$('#operazioneFallitaModal').modal("show");
			}
		}).fail(function() {
			$('#invalidaAccountModal').modal("hide");
			$('#operazioneFallitaModal').modal("show");
		});
	});
}