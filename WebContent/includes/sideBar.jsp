<aside class="col-sm-2">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a id="collapse_link" data-toggle="collapse" href="#lista">Facoltà</a>
          </h4>
        </div>
        <script type="text/javascript">
          $(window).on('resize', function(){
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
              $('#lista').addClass('in');
              $('#collapse_link').attr("href", "#");
            }
          });

          $(document).ready(function() {
            var win = $(this);
            if (win.width() < 768) {
              $('#lista').removeClass('in');
              $('#collapse_link').attr("href", "#lista");
            }
            else
            {
              $('#lista').addClass('in');
              $('#collapse_link').attr("href", "#");
            }
          });
        </script>
        <div id="lista" class="panel-collapse collapse">
          <ul class="list-group">
            <li id="InformaticaListItem" class="list-group-item"><a  href="<%=appendUrl("RicercaAnnuncio", "Informatica")%>">Informatica</a></li>
            <li id="MatematicaListItem" class="list-group-item"><a  href="<%=appendUrl("RicercaAnnuncio", "Matematica")%>">Matematica</a></li>
            <li id="BiologiaListItem" class="list-group-item"><a href="<%=appendUrl("RicercaAnnuncio", "Biologia")%>">Biologia</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
            <li class="list-group-item"><a href="#">...</a></li>
          </ul>
        </div>
      </div>
    </aside>
    

   
    
 <%!
 
 private String appendUrl(String servlet, String appendFacolta){
     
     return servlet + "?facolta=" + appendFacolta;
 }
 
 %>   