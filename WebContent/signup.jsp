<%@page import="dao.AuthDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css_styles/register_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
</head>

<body>
   <div class="login-card">
        <h1>Universal Bank Of Albany</h1>
    <h3>Secure Online Registration</h3>
    <% 
	String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg="";
	out.write("<h3 style=color:red>"+msg+"</h3>");
	
	String loggedIn = (String) session.getAttribute("loggedIn");
	if(loggedIn == null){
%>
   
    
   <form action="SignupServlet" name ="signup" method="post">
    <br><input id="username" type="text" name="username" placeholder="Username">
  <br><input type="submit" value="Check Username Availability" name="check"> 
    
    <br><input id="email" type="text" name="email" placeholder="Email">
    
    <br><input id="password" type="password" name="password" placeholder="Password">
    <br><input id="password" type="password" name="cpassword" placeholder="Confirm Password">
    
    <br><input id="firstname" type="text" name="firstname" placeholder="First Name">
    <br><input id="lastname" type="text" name="lastname" placeholder="Last Name">   
    
    <br><input id="address" type="text" name="address" placeholder="Address">   
    <br><input id="city" type="text" name="city" placeholder="City">   
    <br><input id="state" type="text" name="state" placeholder="State">   
    <br><input id="zipcode" type="text" name="zipcode" placeholder="Zipcode">   
    <br><input id="phonenumber" type="text" name="phonenumber" placeholder="Phone Number">   

    <input type="submit" name="submit" class="login login-register" value="SUBMIT">
<!-- 	<input type="reset" name="reset" class="login login-register" value="Reset" >  -->

</div> 
<%-- <%
	String login_msg=(String)request.getAttribute("error");  
	if(login_msg!=null)
	out.println("<font color=orange size=10px>"+login_msg+"</font>");
	%> --%>
</body>
<% }
	
	if(loggedIn!=null)
		out.write("<h3>You are already a member!</h3>");%>


<div id="footer" align="center">
 
  <a href="login.jsp" style="color:#FFDE59;">Home</a> &nbsp; || &nbsp;&nbsp;
  <a href="contact2.jsp" style="color:#FFDE59;">Contact Us</a> &nbsp; || &nbsp;&nbsp;
  <a href="aboutus.jsp" style="color:#FFDE59;">About Us</a> &nbsp; || &nbsp;&nbsp;
  <a href="sitemapp.jsp" style="color:#FFDE59;">Site Map</a></div>
</html>






