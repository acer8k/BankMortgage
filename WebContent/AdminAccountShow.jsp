<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details</title>

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

</head>
<body>
<%
User_Profile myUser = (User_Profile)session.getAttribute("user_profile");
Account currentAccount = (Account)session.getAttribute("curAcc");
ArrayList<User_Profile> owners = (ArrayList<User_Profile>)session.getAttribute("owners");
session.setAttribute("returnMes", "");

ArrayList<Transaction> history = (ArrayList<Transaction>)session.getAttribute("history");

%>


<%= "Details for " + currentAccount.getType() + " account #" + currentAccount.getAccountId()  %>
<br>
<%="Balance: " + currentAccount.getBalance()%>
<br>
<%="Intrest Rate: " + currentAccount.getIntrestRate()%>
<br>
<br>
<%="Owners:"  %>
<%	for(int i = 0; i < owners.size();i++){
%>
<br>
<% 
	out.print(owners.get(i).getUserId() + "    "+ owners.get(i).getFirstName() + " " + owners.get(i).getLastName());
	}
%>

<h3>HISTORY</h3>
<%="---------------------------\n" %>
<br>
<%="Transaction ID\t\t\tType\t\t\tAmount\t\t\tAccount\t\t\tDate\t\t\tTime\t\t\tPervious Balance" %>
<%	for(int i = history.size() - 1; i > -1;i--){
%>
<br>
<% 
	out.print(history.get(i).toString());
	}
%>

</body>
</html>