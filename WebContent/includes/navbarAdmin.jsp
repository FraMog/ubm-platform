<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a id="ubm" href="index.jsp" style="font-size: 110%">UBM
						Platform</a></li>
			</ul>
			<%@ include file="navbarSearchForm.jsp"%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Cerca utente<span class="caret"></span></a>
					<div class="dropdown-menu" style="padding: 10px;">
						<form class="form" action="#">
							<div class="form-group" style="text-align: center;">
								<input type="text" class="form-control" name="key"
									placeholder="keyword" />
							</div>
							<input type="button" class="btn btn-primary" value="Cerca" />
						</form>
					</div></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Benvenuto UBM-ADMIN<span class="caret"></span></a>
					<div class="dropdown-menu"
						style="padding: 10px; text-align: center">

						<form action="VisualizzaListaUtentiServlet" method="get">
							<div>
								<input type="submit" value="Lista utenti" class="btn btn-primary" />
							</div>
						</form>

						<%--<a href="#" class="btn btn-primary">Lista utenti</a>--%>


					</div></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-out"></span>
						Logout</a></li>
			</ul>
		</div>
	</div>
</nav>