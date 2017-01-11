/**
 * Funzione javascript che permette, cliccando su una immagine piccola dei risultati ricerca, di vederla grande nel lightbox (modal)
 */

function loadModal(){
var modal = document.getElementById('ilMioModal');

//Get the image and insert it inside the modal - use its "alt" text as a caption

var immagini = document.getElementsByClassName('modalImageClasse');
var immaginiLength= immagini.length;
var img; 
var modalImg = document.getElementById("immagineModalGrande");
	for (var j=0; j<immaginiLength;j++){
		img = immagini[j];
		img.onclick = function(){
			   modal.style.display = "block";
			   modalImg.src = this.src;
			   document.body.style.overflow="hidden";
		}
	}


//Get the <span> element that closes the modal
//var span = document.getElementsByClassName("close")[0];
var span =document.getElementById("closeImageModalButton");
//When the user clicks on <span> (x), close the modal
span.onclick = function() {
 modal.style.display = "none";
 document.body.style.overflow="auto";
}
  
}//End loadModal