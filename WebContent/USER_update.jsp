<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page import="model.*"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String loggedIn = (String) session.getAttribute("loggedIn");
	User_Profile u = (User_Profile)session.getAttribute("user_profile");
	User userJava = (User)session.getAttribute("user");
	String msg = (String) request.getAttribute("msg");
	if(msg == null)
		msg="";
	
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table, th, td {
    border: 0.5px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 15px;
}
</style>
<title>Update USER</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="">Universal Bank of Albany</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">

         <li><a href="homescreen_unlocked.jsp">Home</a></li>
         
      
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="contact2.jsp"></span> Contact Us</a></li>
       <!--  <li><a href="logout.jsp"></span> Logout</a></li> -->
       
          <li>	<form action="LogoutServlet" method="post">
 	<input type="submit" value="Logout" name="logout " style="background-color:#660066; color:#FFFF00; height:50px; width:65px; font-weight: 900;"/>
      </form></li>
	
      </ul>
    </div>
  </div>
</nav> 
        <form action="UserUpdateServlet" name ="UpdateUserInfo" method="post">
            <table align="center">
                       <tr>
                        <tr>
                        <th>Address</th>
                        <td><input type="hidden" value="<%=u.getUserId()%>" name="user_Id"/>
                         	<input type="text" value="<%=u.getAddress()%>" name="address" /></td>
                        </tr> 
                        <tr>
                        <th>City</th>
                        <td><input type="text" value="<%=u.getCity()%>" name="city"/></td>
                        </tr>
                        <tr>
                        <th>State</th>
                        <td><input type="text" value="<%=u.getState_country()%>" name="state_country"/></td>
                        </tr>
                        <tr>
                        <th>Zip Code</th>
                        <td><input type="text" value="<%=u.getZipcode()%>" name="zipcode"/></td>
                        </tr>
                        <tr>
                        <th>Phone Number</th>
                        <td><input type="text" value="<%=u.getPhone_number()%>" name="phone_number"/></td>
                        </tr>
                        
                        <td><input type="submit" class="btn btn-success" value="Confirm"/></td>
                        <td><a href="accsetting.jsp" class="btn btn-primary" role="button">Back to Account Page</a></td>
     
            </table>
            </form>

    </body>
</html>