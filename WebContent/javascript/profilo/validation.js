function validation(){
	var ext = $('#img').val().split('.').pop().toLowerCase();
	if($.inArray(ext, ['gif','png','jpg','jpeg','']) == -1) {
	    $('#errore').text("L'estensione dell'immagine non è riconosciuta");
	    return false;
	}
	if($("#img")[0].files[0].size>10*1024*1024){
		$('#errore').tetx('Il file può pesare al massimo 10MB');
		return false;
	}
	var reg=/^[a-zA-Z ]{1,20}$/;
	if($('#nome').val().length==0 || !reg.test($('#nome').val())){
		$('#errore').tetx('Il nome deve contenere al più 20 lettere');
		return false;
	}
	if($('#nome').val().length==0 || !reg.test($('#cognome').val())){
		$('#errore').text('Il cognome deve contenere al più 20 lettere');
		return false;
	}
	reg=/^[0-9]{0}$|^[0-9]{10}$/;
	if(!reg.test($('#tel').val())){
		$('#errore').text('Il campo telefono deve contenere 10 numeri');
		return false;
	}
	return true;
}