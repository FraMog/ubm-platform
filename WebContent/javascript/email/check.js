function prova(){
				var txt1=document.getElementById("cod");
				$.ajax({
					  type: "POST",
					  url: 'codiceVerifica.jsp',
					  data: { 'codice_locale': txt1.value },

					});
				document.location.href ="codiceVerifica.jsp?codice_locale="+txt1.value; 
				console.log(txt1.value);
}
        		