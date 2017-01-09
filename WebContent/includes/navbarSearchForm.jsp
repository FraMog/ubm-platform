<form id="formRicercaAnnuncio" class="navbar-form navbar-left" action="RicercaAnnuncio" method="post">
            <div class="form-group">
              <input id="titoloAnnuncioRicerca" name="titolo" type="text" class="form-control" placeholder="Search" pattern="[a-zA-Z]{1}[a-zA-Z0-9 ]{0,49}" title="Il titolo deve contenere tra 1 e 50 caratteri alfanumerici" required="required">
            </div>
            <div class="form-group">
              <select class="form-control" name="facolta">
                <option value="Informatica" selected="selected">Informatica</option>
                <option value="Matematica">Matematica</option>
                <option value="Biologia">Biologia</option>
              </select>
            </div>
            <div class="form-group">
              <select class="form-control" name="categoria">
              	<option value="tutto" selected="selected">Tutto</option>
                <option value="libro">Libro</option>
                <option value="appunti">Appunti</option>
              </select>
            </div>
            <div class="input-group" style="margin-left:10px">
              <div class="radio" style="margin-right:10px">
                <label style="color:white"><input type="radio" name="ordine" value="Prezzo" checked="true"> Prezzo migliore</label>
              </div>
              <div class="radio">
                <label style="color:white"><input type="radio" name="ordine" value="DataPubblicazione"> Più recenti</label>
              </div>
            </div>
            <input type="submit" class="btn btn-default" value="Cerca" style="margin-left:20px"/>
</form>

<script src="javascript/annunci/validaRicercaAnnuncio.js"></script>

<script>
$("document").ready(function(){
	validaFormRicerca ("formRicercaAnnuncio");
});
		
</script>
