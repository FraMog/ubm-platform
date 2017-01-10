 function validateForm(){
	 var password=document.getElementById("password").value;
	 var txt1=document.getElementById("password");
	 var txt2=document.getElementById("passwordV");
	  
	  if(txt1.value!=txt2.value)
		{
			$('#erroreModal').modal('show'); //mostro il modal
	         return false;

		}
 }