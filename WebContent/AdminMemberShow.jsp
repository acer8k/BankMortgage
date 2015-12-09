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
	session.setAttribute("returnMes", "");
	String loggedIn = (String) session.getAttribute("loggedIn");
	User_Profile u = (User_Profile)session.getAttribute("curMem");
	User curUser = (User)session.getAttribute("curUser");
	User userJava = (User)session.getAttribute("user");
	String msg = (String) request.getAttribute("msg");
	//Integer currentAccount = (Integer)session.getAttribute("curAcc");
	if(msg == null)
		msg="";
		 
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

 
<title>My Account - Settings</title>
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
      <li><a href="aboutus.jsp"></span> About Us</a></li>
        <li><a href="contact2.jsp"></span> Contact Us</a></li>
       <!--  <li><a href="logout.jsp"></span> Logout</a></li> -->
       
          <li>	<form action="LogoutServlet" method="post">
 	<input type="submit" value="Logout" name="logout " style="background-color:#660066; color:#FFFF00; height:50px; width:65px; font-weight: 900;"/>
      </form></li>
	
      </ul>
    </div>
  </div>
</nav>

  
<div class="container">
  <h3>User Info</h3>
            
 <table style="width: 25%;" class="table table-bordered">
    <thead>
      <tr>
        <th>Username</th>
        <td><%=curUser.getUsername()%></td>
      </tr>
    </thead>
	    <thead>
      <tr>
        <th>Name</th>
        <td> <%=u.getFirstName() + " " + u.getLastName() %></td>
      </tr>
    </thead>

  </table>
  
    <h3>Contact Information</h3>
            
 <table style="width: 25%;" class="table table-bordered">
    <thead>
      <tr>
        <th>E-MAIL</th>
               <!--  <td>Error displaying email</td> -->
        <td><%=curUser.getEmail()%></td>
      </tr>
    </thead>
	    <thead>
      <tr>
        <th>Phone #</th>
        <td><%=u.getPhone_number()%></td>
      </tr>
    </thead>
  </table>
  
    <h3>Mailing Address</h3>
   
    

         
 <table style="width: 40%;" class="table table-bordered">
    <thead>
      <tr>
        <th>Address 1</th>
        <td><%=u.getAddress()%></td>
      </tr>
    </thead>
	    <thead>
      <tr>
        <th>City</th>
        <td><%=u.getCity()%></td>
      </tr>
    </thead>
    <thead>
      <tr>
        <th>State</th>
        <td><%=u.getState_country()%></td>
      </tr>
    </thead>
	<thead>
      <tr>
        <th>Zip</th>
        <td><%=u.getZipcode()%></td>
      </tr>
    </thead>
    <thead>
      <tr>
        <th>userID</th>
        <td><%=u.getUserId()%></td>
      </tr>
    </thead>

  </table>
</div>


<div class="container">
  <h3>Accounts with ownership:</h3>
            
 <table style="width: 25%;" class="table table-bordered">
    <thead>
      <tr>
        <th>Account Type</th>
        <td> Account Id</td>
      </tr>
    </thead>
	
	<% 
for(int i = 0; i < u.getAccounts().size();i++){
	%>
	<tr>
        <th><%=u.getAccounts().get(i).getType()%></th>
        <td><%=u.getAccounts().get(i).getAccountId()%></td>
      </tr>
	<% 
}
	%>

  </table>


<%-- <a href="accsetting_update.jsp?user_Id=<%=u.getUserId()%>"><b>Update Contact Information<b></a> --%>  
	<%--  <a href="accsetting_update.jsp?user_Id=<%=u.getUserId()%>" class="btn btn-danger" role="button">Update Contact Information</a> --%>

</body>
</html>