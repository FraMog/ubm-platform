<form class="navbar-form navbar-left" action="#" method="post">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <div class="form-group">
              <select class="form-control" name="facolta">
                <option value="informatica">Informatica</option>
              </select>
            </div>
            <div class="form-group">
              <select class="form-control" name="tipo">
                <option value="libro">Libro</option>
              </select>
            </div>
            <div class="input-group" style="margin-left:10px">
              <div class="radio" style="margin-right:10px">
                <label style="color:white"><input type="radio" name="ordine" value="prezzo" checked="true"> Prezzo migliore</label>
              </div>
              <div class="radio">
                <label style="color:white"><input type="radio" name="ordine" value="data"> Più recenti</label>
              </div>
            </div>
            <input type="submit" class="btn btn-default" value="Cerca" style="margin-left:20px"/>
</form>