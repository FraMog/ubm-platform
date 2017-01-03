			
  function validateEmail(emailField){
	  // prendo il valore del textbox tramite javascript selezionando l'id del textbox
		var txt1=document.getElementById("email");
	  
	  var reg = /^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it$)/;
      if (reg.test(txt1.value) == false) 
      {
    	  //DEBU
          alert('Invalid Email Address\nEmail:'+txt1.value);
          return false;
      }

      return true;

}