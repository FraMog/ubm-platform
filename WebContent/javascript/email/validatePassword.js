  
  function validatePassword(){
	  var txt1=document.getElementById("password");
	  
	  var reg=/(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}/;
	  
	  if(reg.test(txt1.value)==false)
		  {	
		  	alert('password non valida \nPassword: '+txt1.value);
		  	return false;
		  }
	  return true;
  }
  