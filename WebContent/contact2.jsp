<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Us</title>
<style>
table, th, td {
    border: 0px solid black;
    width:70%;
}
body {
    background-color: #d0e4fe;
}

h1 {
    color: orange;
    text-align: left;
    line-height:7px;
    font-size: 20px;
}

p {
    font-family: "Times New Roman";
    font-size: 17px;
    line-height:8px;
}

div#map_container{
	width:100%;
	height:250px;
</style>
<script type="text/javascript" 
   src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>

<script type="text/javascript">
  function loadMap() {
    var latlng = new google.maps.LatLng(42.6867393,-73.822842);
    var myOptions = {
      zoom: 15,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
	
    var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title:"Universal Bank Of Albany"
    }); 
  
  }
</script>
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
        <li><a href="contactus.jsp"></span> Contact Us</a></li>
        <li>	<form action="LogoutServlet" method="post">
 	<input type="submit" value="Logout" name="logout " style="background-color:#660066; color:#FFFF00; height:50px; width:65px; font-weight: 900;"/>
      </form></li>
      </ul>
    </div>
  </div>
</nav>

<table align="center">
  
  <tr>
    <td><h1> Contact Us</h1>
	<p>As part of our commitment to outstanding client service,</p> 
	<p>we are available to help with your financial needs in a way </p>
	<p>that is convenient for you-whether by email or phone or in </p>
	<p>person at one of our Preferred Banking Offices.</p>
	<h1>Customer Care</h1>
	<p>Phone:- (555)444-8888</p>
	<p>Hours(Eastern Time)</p>
	<p>Monday - Friday 5am - 9pm</p>
	<p>Saturday 6am - 8pm</p>
	<p>Sunday 7am - 5pm</p></td>
    <td><body onload="loadMap()">
<form action="EmailSendingServlet" method="post">
		
		<table border="0" width="35%" align="right">
		
			<caption><h3>Have A Query? Send us an E-mail</h3></caption>
			
			<tr>
				<td>Subject </td>
				<td><input type="text" name="subject" size="45"/></td>
			</tr>
			<tr>
				<td>Content </td>
				<td><textarea rows="10" cols="46" name="content"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Send"/></td>
			</tr>
		<tr><td colspan="2" align="center">${Message}</td></tr>
		
		</table>
		
	</form></td>
  </tr>
  <tr>
    <td><h1>By Email</h1>
	<p>
	<a href="mailto:ualbanybank@gmail.com">ualbanybank@gmail.com</a>
	</p>
	<h1>By Live Chat</h1>
	<p>Monday to Friday. 9AM TO 5PM EST</p>
	<h1>Branch Office:</h1>
    <p> 1400 Washington Ave</p>
    <p>Albany, NY 12222</p></td>
    <td><div id="map_container" style="text-align:right;float:right;"></div></td>
  </tr>
</table>
</body>
</html>