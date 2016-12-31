function prova(){

				var cod=document.getElementById("cod");
				var cod2='<%= session.getAttribute("codice") %>';
					
        		if(cod.value==cod2.value)
        			alert("CODICE OK");
        		else
        			alert("CODICE ERRATO");
}
        		