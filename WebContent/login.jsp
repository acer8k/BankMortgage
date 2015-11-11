<%@page import="controller.CaptchasDotNet"%>
<%@page import="java.net.Authenticator" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<% String loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn!=null){
	
%> 
<div id="header">
	<%@ include file="nav.jsp" %>
</div>
<% }%>	   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<link rel="stylesheet" href="css_styles/main.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Universal Bank Of Albany</title>
</head>
<%	loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn==null){
	
%>
<body>  
<%
//MAKING A CAPTCHA OBJECT
//NEED TO IMPORT THE CaptchasDotNet Class in auth
CaptchasDotNet captchas = new controller.CaptchasDotNet(
  request.getSession(true),     // Ensure session
  "Trudgling",                       // client <--use these
  "P1W9WnjyjYZ1di1F0NegiWalX2rOUJS1ieNTinvY"    // secret
  );
%> 
 
  <h2 align="center" style="color:#FFDE59;">Universal Bank Of Albany</h2>
</div>
    </br>
     <div class="login-card">
    <h3>Secure Online Login</h3>
        <div align="right">  
</div>
<% String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3 style=color:red>"+msg+"</h3>");
	
%>
   
   <form action="LoginServlet" method="post">
    <input type="text" name="user" placeholder="Username">
    <input type="password" name="pass" placeholder="Password">

    
    <% 
  //CAPCHA FORM BEGINS HERE
  %>
  <input type="text" name="capPhrase" placeholder="Captcha Phrase" >
  <%= captchas.image() %><br>
  <input type="submit" name="login" class="login login-submit" value="Login">
  <% 
  //CAPCHA FORM ENDS HERE
  %>  
    
    
  </form>
   <form action=signup.jsp>
    <input type="submit" name="register" class="login login-register" value="Enroll">

  </form>
<div class="login-help">
    <a href="#">Forgot Password</a>
  </div>
</div> 

<% }%>
<%  loggedIn = (String) session.getAttribute("loggedIn"); 
if(loggedIn!=null){	
%>
<div id="footer">
	<%@ include file="logout.jsp" %>
</div>
<%} %>
</body>
<div id="footer" align="center">
  </br>
  <a href="contact2.jsp" style="color:#FFDE59;">Contact Us</a> &nbsp; || &nbsp;&nbsp;
  <a href="aboutus.jsp" style="color:#FFDE59;">About Us</a> &nbsp; || &nbsp;&nbsp;
  <a href="sitemapp.jsp" style="color:#FFDE59;">Site Map</a>
  
</div>
</html>