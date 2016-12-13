<nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
          <ul class="nav navbar-nav">
            <li><a id="ubm" href="index.jsp" style="font-size:110%">UBM Platform</a></li>
          </ul>
          <form class="navbar-form navbar-left" action="#">
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
                <label style="color:white"><input type="radio" name="ordine" value="prezzo" checked="true">Prezzo migliore</label>
              </div>
              <div class="radio">
                <label style="color:white"><input type="radio" name="ordine" value="data">Più recenti</label>
              </div>
            </div>
            <input type="submit" class="btn btn-default" value="Cerca" style="margin-left:20px"/>
          </form>
          <ul class="nav navbar-nav navbar-right">
          	<li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cerca utente<span class="caret"></span></a>
              <div class="dropdown-menu" style="padding:10px;">
                <form class="form" action="#">
                  <div class="form-group" style="text-align:center;">
                  	<input type="text" class="form-control" name="key" placeholder="keyword"/>
                  </div>
                  <input type="button" class="btn btn-primary" value="Cerca" />
                </form>
              </div>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Benvenuto Giovanni<span class="caret"></span></a>
              <div class="dropdown-menu" style="padding:10px;">
                <form class="form" action="#">
                  <div class="form-group" style="text-align:center;">
                    <input type="button" class="btn btn-primary" value="Il mio Profilo" />
                  </div>
                  <div class="form-group" style="text-align:center;">
                    <input type="button" class="btn btn-primary" value="Inserisci annuncio" />
                  </div>
                </form>
              </div>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
          </ul>
        </div>
      </div>
    </nav>