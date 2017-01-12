function validateForm(){
	var password=document.getElementById("password").value;
	var passwordV=document.getElementById("passwordV").value;
	var email= document.getElementById("email").value;
	var patternEmail = /^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it$)/;
	var patternPassword= /(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}/;


	if(!patternPassword.test(password)){
		$('#erroreModalP').modal('show'); //mostro il modal
		return false;
	}

	if(!patternEmail.test(email)){
		$('#erroreModalE').modal('show'); //mostro il modal
		return false;
	}

	if(password!=passwordV)
	{
		$('#erroreModal').modal('show'); //mostro il modal
		return false;

	}
}