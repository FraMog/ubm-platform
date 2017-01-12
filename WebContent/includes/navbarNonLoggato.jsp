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
          <%@ include file= "navbarSearchForm.jsp"%>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Login<span class="caret"></span></a>
              <div class="dropdown-menu" style="padding:10px;">
                <form class="form" action="LoginServlet" method="POST">
                  <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="e-mail" 
                    pattern="^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@studenti.unisa.it)|^(?=.{5,40}$)(([A-Z0-9a-z._%+-])+@unisa.it)|ubmplatform@gmail.com" 
                    title="L'e-mail deve essere del tipo nome@studenti.unisa.it o nome@unisa.it, con 'nome' contenente tra 5 e 40 caratteri alfanumerici e non (sono consentiti i seguenti simboli: .,_,%,+,-)."
                    autofocus required="required">
                  </div>
                  <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="password"
                    pattern="(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}"
                    title="La password deve essere lunga tra gli 8 e i 20 caratteri, contenere almeno 1 numero(i) e 1 lettera(e) minuscola(e) o maiuscola(e)." required="required"><br>
                  </div>
                  <a href="recuperaPassword.jsp">Password dimenticata?</a>

                    <input type="submit" class="btn btn-primary" value="Login"/>
                </form>
              </div>
            </li>
            <li><a href="registrazione.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
          </ul>
        </div>
      </div>
    </nav>