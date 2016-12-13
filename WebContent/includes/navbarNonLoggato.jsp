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
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login<span class="caret"></span></a>
              <div class="dropdown-menu" style="padding:10px;">
                <form class="form" action="#">
                  <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="e-mail">
                  </div>
                  <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Password"><br>
                  </div>
                  <a href="recuperaPassword.jsp">recupera password</a>
                  <div class="checkbox">
                    <label><input type="checkbox">Remember me</label>
                  </div>
                    <input type="submit" class="btn btn-primary" value="Login"/>
                </form>
              </div>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          </ul>
        </div>
      </div>
    </nav>