
//al caricamento avvenuto del documento (la parte del modal)
//invio una richiesta alla servlet VisualizzaFeedbackServlet che
//si occupa di ottenere tutti i feedback data un'email
//la risposta (text) verrà data in json, convertita, e verra chiamata una funzione
//che si occupa di stilare i feedback nel modal
$('#vediFeedbackModal').on('show.bs.modal',function(){

	
	var emailR = $("#emailR").text();
	if(emailR != null && emailR != undefined){
		$.get("VisualizzaFeedbackServlet?emailR=" + emailR, function(text, status){
			if(status == 'success'){

				var feedbacks = JSON.parse(text);

				if(feedbacks.state == 'feedbackerror'){
					$('#viewFeedbackLogger').text("Problema con l'inserimento nel database!");
				}else if(feedbacks.state == 'emailerror'){
					$('#viewFeedbackLogger').text("Problema con il recupero dell'email!");
				}else if(feedbacks.state == 'nosession'){
					$('#viewFeedbackLogger').text("Devi essere loggato al sistema!");
				}else{
					//nessun feedback presente

					if(feedbacks.length == 0){
						$('#viewFeedbackLogger').text("Non sono presenti feedback! Se hai fatto già acquisti con l'utente, sii il primo ad inserirne uno!");
					}else{
						mostraFeedbacks(feedbacks);
					}
				}


			}
		})
	}else $('#viewFeedbackLogger').text("Problema con il recupero dell'email!");
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
		
		//tabella per la valutazione
		var tableVal = document.createElement('table');
		tableVal.className = "table";
		
		var head = document.createElement('thead');
		var tr = document.createElement('tr');
		var th = document.createElement('th');
		
		th.innerHTML = "Valutazione";
		
		tr.appendChild(th);
		head.appendChild(tr);
		tableVal.appendChild(head);
		
		var body = document.createElement('tbody');
		tr = document.createElement('tr');
		var td = document.createElement('td');

		//LA VALUTAZIONE -- CREO L'IMMAGINE APPOSITA
		var imgVal = document.createElement('img');
		imgVal.id = "feedback-stars";
		imgVal.className = "img-responsive col-sm-5";
		imgVal.style = "padding-left: 0px; padding-bottom: 0px;";
		imgVal.src="img/feedback/feedback" + feedbacks[i].valutazione + ".png";
		imgVal.alt = "Valutazione feedback";
		imgVal.title = "Feedback average";
		
		td.appendChild(imgVal);
		tr.appendChild(td);
		
		tr.style.background = "white";
		
		body.appendChild(tr);
		tableVal.appendChild(body);
		
		divText.appendChild(tableVal);
		
		
		//ORA CREO LA TABELLA PER LA DESCRIZIONE -------
		var tableDesc = document.createElement('table');
		tableDesc.className = "table";
		
		head = document.createElement('thead');
		tr = document.createElement('tr');
		th = document.createElement('th');
		
		th.innerHTML = "Descrizione:";
		
		tr.appendChild(th);
		head.appendChild(tr);
		tableDesc.appendChild(head);
		
		body = document.createElement('tbody');
		tr = document.createElement('tr');
		td = document.createElement('td');

		var pDesc = document.createElement('p');
		pDesc.innerHTML = feedbacks[i].descrizione;
		
		td.appendChild(pDesc);
		tr.appendChild(td);
		
		tr.style.background = "white";
		
		body.appendChild(tr);
		
		tableDesc.appendChild(body);
		
		divText.appendChild(tableDesc);
		divText.className = "list-group-item-texting";
		
		
		//aggiungo header e text alla riga 
		li.appendChild(divHead);
		li.appendChild(divText);
		li.style.marginBottom = "5%";
		
		//aggiungo il bordo
		li.style.border = ("medium groove #FFFFFF");
		li.style.background = "#ECE9F9";
		li.style.borderRadius = "8%";
		//aggiungo la riga al div listFeedback
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

//l'evento per la pulizia alla chiusura del modal
$('#vediFeedbackModal').on('hidden.bs.modal', function(){
	$('#listFeedback').text("");
})