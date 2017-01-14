/**
 * Funzione che carica le facolta, richiamata in footer.jsp
 */
function caricaFacolta(){
  $.ajax({
    type: "POST" ,
    url: "xml/facolta.xml" ,
    dataType: "xml" ,
    success: function(xml) {
   	 $(xml).find('facolta').each(function(){
         nomeFacolta= $(this).text();  
         if ($('#elencoFacoltaNavbarSearchForm').length){//se nella pagina c'è il navbarSearcForm
         	 var linkFacolta=nomeFacolta.replace(' ', '\%20');
        	 var facoltaListItemNavbar='<option value="' + nomeFacolta + '">'+ nomeFacolta + '</option>';
          	$("#elencoFacoltaNavbarSearchForm").append(facoltaListItemNavbar);
         }
         if ($('#elencoFacoltaSideBar').length){//se nella pagina c'è la sideBar
        	 var facoltaListItemSidebar='<li id="' + nomeFacolta + 'ListItem" class="list-group-item"><a href="RicercaAnnuncio?facolta=' + nomeFacolta + '">' + nomeFacolta + "</a></li>";
             $("#elencoFacoltaSideBar").append(facoltaListItemSidebar);
          }
         if ($('#elencoFacoltaInserisciAnnuncio').length){//se sono in inserisci annuncio
        	 var facoltaListItemInserisciAnnuncio='<option value="' + nomeFacolta + '">'+ nomeFacolta + '</option>';
             $("#elencoFacoltaInserisciAnnuncio").append(facoltaListItemInserisciAnnuncio);
          }
         if ($('#elencoFacoltaModificaAnnuncio').length){//se sono in inserisci annuncio
        	 var facoltaListItemModificaAnnuncio='<option value="' + nomeFacolta + '">'+ nomeFacolta + '</option>';
             $("#elencoFacoltaModificaAnnuncio").append(facoltaListItemModificaAnnuncio);
             var areEqual = facoltaAnnuncio.toUpperCase() == nomeFacolta.toUpperCase();//Controllo se la facolta che risulta attiva in questa iterazione del for è la stessa di quella dell'annuncio modificabile
             if(areEqual){
            	 $('#elencoFacoltaModificaAnnuncio').val(nomeFacolta);
             }
          }
   	 });
    }
    });
  
}