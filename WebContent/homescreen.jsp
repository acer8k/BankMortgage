<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <%
	String loggedIn = (String) session.getAttribute("loggedIn");
	User_Profile u = (User_Profile)session.getAttribute("user");
	String msg = (String) request.getAttribute("msg");
	if(msg == null)
		msg="";
	//out.write("<h2 style=color:green>"+msg+"</h2>"); 
	/* if(loggedIn == "true")
		out.write("<h3>Hello " + u.getFirstName()+" "+u.getLastName()+"</h3><br>");
	    
	else{
		if(u == null)
			out.write("<h2 style=color:red>You are not logged in.</h2>");
		else
			out.write(" ");
	} */
%>
<head>
<!-- <META HTTP-EQUIV="REFRESH" CONTENT="10;URL=welcome.jsp"> -->
<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Homescreen</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
var IDLE_TIMEOUT = 6000; //seconds
var _idleSecondsTimer = null;
var _idleSecondsCounter = 0;

document.onclick = function() {
    _idleSecondsCounter = 0;
};

document.onmousemove = function() {
    _idleSecondsCounter = 0;
};

document.onkeypress = function() {
    _idleSecondsCounter = 0;
};

_idleSecondsCounter = window.setInterval(CheckIdleTime, 1000);

function CheckIdleTime() {
     _idleSecondsCounter++;
     var oPanel = document.getElementById("SecondsUntilExpire");
     if (oPanel)
         oPanel.innerHTML = (IDLE_TIMEOUT - _idleSecondsCounter) + "";
    if (_idleSecondsCounter >= IDLE_TIMEOUT) {
        window.clearInterval(_idleSecondsCounter);
        alert("Time expired!");
        document.location.href = "logout.jsp";
    }
}
</script> 

</head>
<title>Homescreen</title>
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

         <li><a href="homescreen.jsp">Home</a></li>
        <li><a href="accsetting.jsp">Account Settings</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="aboutus.jsp"></span> About Us</a></li>
        <li><a href="contact2.jsp"></span> Contact Us</a></li>
       <!--  <li><a href="logout.jsp"></span> Logout</a></li> -->
       
   
       
       <li>	<form action="LogoutServlet" method="post">
		<input type="submit" value="Logout" name="logout">
	</form></li>
	
      </ul>
    </div>
  </div>
</nav>
 
<div class="container">
  <div class="jumbotron">
    <h3>Good Morning!  <% out.write("<h3>Hello " + u.getFirstName()+" "+u.getLastName()+"</h3><br>");%></h3>      
    <p>New Messages {0}</p>   
     <p>Your current balance is {$XX,XXX}<br></p>
     <p>Account Number: {########}</p>
     <p>You will be auto logged out in <span id="SecondsUntilExpire"></span> seconds.<p>     
    <a href="#" class="btn btn-info btn-lg"> Checking-Account</a>
    <a href="#" class="btn btn-info btn-lg"> Saving Account</a>
  </div>

  <div class=row>
    <div class="col-md-3">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">More Stuffs</a></li>
        <li class="dropdown">
          <!-- <a class="dropdown-toggle" data-toggle="dropdown" href="mortgage_calc.jsp">Apply for Mortgage</a> -->
          
        </li>
        <li><a href="mortgage_calc.jsp">Mortgage Calculator</a></li>
        <li><a href="#">Monthly Statements</a></li>
        <li><a href="#">Make Payments</a></li>
        <li><a href="tax_form.jsp">Tax Forms</a></li>
      </ul>
    </div>
    <div class="clearfix visible-lg"></div>
  </div>
</div>

</body>

</html>