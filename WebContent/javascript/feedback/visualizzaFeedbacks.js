
//al caricamento avvenuto del documento (la parte del modal)
//invio una richiesta alla servlet VisualizzaFeedbackServlet che
//si occupa di ottenere tutti i feedback data un'email
//la risposta (text) verrà data in json, convertita, e verra chiamata una funzione
//che si occupa di stilare i feedback nel modal
$(document).ready(function(){
	
	var emailR = $("#emailR").text();
	
	$.get("VisualizzaFeedbackServlet?emailR=" + emailR, function(text, status){
		if(status == 'success'){
			
			var feedbacks = JSON.parse(text);
			
			if(feedbacks.state == 'feedbackerror'){
				$('#modalBody').text("Problema con l'inserimento nel database!");
			}else if(feedbacks.state == 'emailerror'){
				$('#modalBody').text("Problema con il recupero dell'email!");
			}else if(feedbacks.state == 'nosession'){
				$('#modalBody').text("Devi essere loggato al sistema!");
			}else{
				//nessun feedback presente
				
				if(feedbacks.length == 0){
					$('#modalBody').text("Non sono presenti feedback! Inseriscine uno!");
				}else{
					mostraFeedbacks(feedbacks);
				}
			}
			
			
		}
	})
})

function mostraFeedbacks(feedbacks){
	
	//la struttura da simulare è commentata sotto il metodo
	for(var i = 0; i < feedbacks.length; i++){
		
		//l'elemento da inserire nella lista
		var li = document.createElement('li');
		li.className = "list-group-item";
		
			//l'header di bootstrap per le liste
			var divHead = document.createElement('div');
			divHead.className = "list-group-item-heading col-sm-12";
		
				//i due span per l'email e la data da inserire nell'header
				var spanEmail = document.createElement('span');
				spanEmail.className = "col-sm-8";
				spanEmail.innerHTML = "Pubblicato da:<p>" + feedbacks[i].emailP + "</p>";
		
				var spanData = document.createElement('span');
				spanData.className = "col-sm-4";
				spanData.innerHTML = feedbacks[i].data;
		
			//li aggiungo al div head
			divHead.appendChild(spanEmail);
			divHead.appendChild(spanData);
		
		
		//creo il div che contiene valutazione e descrizione
		var divText = document.createElement('div');
		divText.className = "list-group-item-texting";
		
			//la label che si collega con l'attributo for alla valutazione
			var labelVal = document.createElement('label');
			labelVal.htmlFor = "val";
			labelVal.innerHTML = "Valutazione:";
		
			//LA VALUTAZIONE
			var pVal = document.createElement('p');
			pVal.id = "val";
			pVal.innerHTML = feedbacks[i].valutazione;
		
			//LA LABEL CHE SI COLLEGA ALLA DESCRIZIONE
			var labelDesc = document.createElement('label');
			labelDesc.htmlFor = "desc";
			labelDesc.innerHTML = "Descrizione:";
		
			//LA DESCRIZIONE
			var pDesc = document.createElement('p');
			pDesc.id = "desc";
			pDesc.innerHTML = feedbacks[i].descrizione;
		
		//METTO IN APPEND TUTTI GLI ATTRIBUTI CON ORDINE
		//LABEL VALUTAZIONE - VALUTAZIONE - LABEL DESCRIZIONE - DESCRIZIONE
		//valutazione e descrizione li metto in due tag p differenti
		var p1 = document.createElement('p');
		p1.appendChild(labelVal);
		p1.appendChild(pVal);
		
		var p2 = document.createElement('p');
		p2.appendChild(labelDesc);
		p2.appendChild(pDesc);
		
		//aggiungo i due p al div
		divText.appendChild(p1);
		divText.appendChild(p2);
		
		//aggiungo header e text alla riga 
		li.appendChild(divHead);
		li.appendChild(divText);
		
		//aggiungo una riga al div listFeedback
		document.getElementById("listFeedback").appendChild(li);
	}
	

	/* STRUTTURA SIMULATA:
	 * <li class="list-group-item">
	 		<div class="list-group-item-heading col-sm-12">
	 			<span class="col-sm-7">dwadaw@wow.it</span>
	 			<span class="col-sm-5">21/02/16</span>
	 		</div>
	 		<div class="list-group-item-texting">
	 			<p>
	 				<label for="val">Valutazione</label>
	 				<span id="val">5</span>
	 			</p>
	 					
	 			<p>
	 		 		<label for="desc">Descrizione</label>
	 		 		<span id="desc">Fantastico</span>
	 		 	</p>
	 		 </div>
	 	</li>
	 */
}