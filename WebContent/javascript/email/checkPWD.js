 	function checkPWD(){
  		var txt1=document.getElementById("password");
  		var txt2=document.getElementById("passwordV");
  		
  		if(txt1.value!=txt2.value)
  			{
  				alert("Controlla le due password \n"+txt1.value+"\n"+txt2.value);
  				return false;
  				
  			}

  	}