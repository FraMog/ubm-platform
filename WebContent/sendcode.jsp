<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page import="java.util.*"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.*"%>




<%
	int codice=(int)(Math.random()* 999999);
	
	session.setAttribute("codice",codice);
	String host = "smtp.gmail.com";
	String to =(String) session.getAttribute("indirizzo"); 
	String from = request.getParameter("pasquale335@gmail.com");
	String subject = request.getParameter("subject"); 
	String messageText = "CODICE DI VERIFICA "+codice+".";
	final String username="pasquale335@gmail.com";
	final String password="pasquale11";
	// Create some properties and get the default Session.
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session sessionMail = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(sessionMail);
			sessionMail.setDebug(true);

			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
 
			Transport.send(message);
 
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	
	%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script language="Javascript"> 
	
	window.location.href="codiceVerifica.jsp?codice_locale="+"0" ;
	</script>
</body>
</html>