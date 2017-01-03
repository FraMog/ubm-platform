<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<%	String dest=request.getParameter("dest");
		session.setAttribute("indirizzo",dest);
		
		//out.print(dest) DEBUG
		%>
		
		<script language="Javascript">
		window.location.href="sendcode.jsp"</script>
</body>
</html>