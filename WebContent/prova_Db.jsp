<html>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%

String user=request.getParameter("userid"); 
session.putValue("userid",user); 
String pwd=request.getParameter("pwd"); 
String fname=request.getParameter("email"); //Prende valori tramite post nell'uri passati da registrazione.jsp
String lname=request.getParameter("password"); 
String email=request.getParameter("email"); 
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ubmplatform",
"root",""); 
Statement st= con.createStatement(); 
ResultSet rs; 
int i=st.executeUpdate("INSERT INTO account VALUES ('"+fname+"','"+lname+"','n')"); 

session.setAttribute("indirizzo",fname);
%>

<head></head>
<body>

<script type="text/javascript">
	window.location.href="sendcode.jsp";
</script>
</body>
</html>