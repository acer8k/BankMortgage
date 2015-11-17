<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	String loggedIn = (String) session.getAttribute("loggedIn");
	User_Profile u = (User_Profile)session.getAttribute("user_profile");
	String msg = (String) request.getAttribute("msg");
	if(msg == null)
		msg="";
		
%>

<head>
<!-- <META HTTP-EQUIV="REFRESH" CONTENT="10;URL=welcome.jsp"> -->
	<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="javascripts/jquery.js"></script>
	<script type="text/javascript" src="javascripts/jquery-ui.js"></script>
 	<link rel="stylesheet" href="css_styles/cssjq.css" />
       <style>
            html {
                font-size:10px;
            }
            .iframetab {
                width:100%;
                height:auto;
                border:30px;
                margin:0px;
                background:url("images/iframeno.png");
                position:relative;
                top:-13px;
            }
            .ui-tabs-panel {
                padding:5px !important;
            }
            .openout {
                float:right;
                position:relative;
                top:-28px;
                left:-5px;
				
            }
           
        </style>

<script type="text/javascript">
$(document).ready(function() {
    var $tabs = $('#tabs').tabs();
    //get selected tab
    function getSelectedTabIndex() {
        return $tabs.tabs('option', 'active');
    }
    //get tab contents
    beginTab = $("#tabs ul li:eq(" + getSelectedTabIndex() + ")").find("a");
    loadTabFrame($(beginTab).attr("href"),$(beginTab).attr("rel"));
    $("a.tabref").click(function() {
        loadTabFrame($(this).attr("href"),$(this).attr("rel"));
    });
    //tab switching function
   /* function loadTabFrame(tab, url) {
        if ($(tab).find("iframe").length == 0) {
            var html = [];
            html.push('<div class="tabIframeWrapper">');
            html.push('<div class="openout"><a href="' + url + '"><img src="images/world_thumb.png" border="0" alt="Open" title="Remove iFrame" /></a></div><iframe class="iframetab" src="' + url + '">Load Failed?</iframe>');
            html.push('</div>');
            $(tab).append(html.join(""));
            $(tab).find("iframe").height($(window).height()-80);
        }
        return false;
    } */
    
});
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
<!-- 		<input type="submit" value="Logout" name="logout "> -->

		<input type="submit" value="Logout" name="logout " style="background-color:#660066; color:#FFFF00; height:50px; width:75px; font-weight: 900;"/>


	</form></li>
	
      </ul>
    </div>
  </div>
</nav>
<div class="container">
  <div class="jumbotron">
    <h3>Good Morning! <%=u.getFirstName() + " " + u.getLastName() %> </h3>
    <font color="red" size="4"> <%=session.getAttribute("returnMes")%>   </font>   
    <!-- <p>New Messages {0}</p>   
     <p>Your current balance is {$XX,XXX}<br></p>
     <p>Account Number: {########}</p>
 -->  
     <div id="tabs">
            <ul>
                <li><a class="tabref" href="#tabs-1">Account Summary</a></li>
                <li><a class="tabref" href="#tabs-2" rel="https://www.google.com">Pay Mortgages</a></li>
                <li><a class="tabref" href="#tabs-3" rel="https://www.google.com">Transfer Money</a></li>
            </ul>
            <div id="tabs-1" class="tabMain"><br>
             <form action=ViewAccnType><br>
             <%for(int i = 0; i < u.getAccounts().size(); i++){
             	int val = i;
             	String store = "" + i;
             	//session.setAttribute("val", i);
             %>
				<input type="radio" name="accn" value=<%=store%> checked> <%=u.getAccounts().get(i).getType() + " #" + u.getAccounts().get(i).getAccountId() %><br><br>
				
				<% 
             }
				%>
  		
  			  	<!-- <input type="Submit" value="View"> -->
<input type="submit" value="View" style="background-color:#FFFFC9; color:#660066; height:40px; width:100px; font-weight: 900;"/>  			  	
  			  	
  			  	
  			 </form> 
  			</div>
            <div id="tabs-2">
             <form action=indexServlet><br>
             <%="Pay to which Mortgage?"%>
             <br>
             <%for(int i = 0; i < u.getAccounts().size(); i++){
             	int val = i;
             	String store = "" + i;
             	//session.setAttribute("val", i);
             	if(u.getAccounts().get(i).getType().equals("Mortgage")){
             %>
				<input type="radio" name="mortAccn" value=<%=store%> checked> <%=u.getAccounts().get(i).getType() + " #" + u.getAccounts().get(i).getAccountId() %><br><br>
				
				<% 
             	}
             }
				%>
				<br>
				<%="From which account?"%>
				<br>
				<%for(int i = 0; i < u.getAccounts().size(); i++){
             	int val = i;
             	String store = "" + i;
             	//session.setAttribute("val", i);
             	if(u.getAccounts().get(i).getType().equals("Checking")){
             %>
				<input type="radio" name="checkAcc" value=<%=store%> checked> <%=u.getAccounts().get(i).getType() + " #" + u.getAccounts().get(i).getAccountId() %><br><br>
				
				<% 
             	}
             }
				%>
				<%= "Amount to pay" %>
				<br>
				<input type="text" name="payAmmount">
				<br>
  		
  			  	<!-- <input type="Submit" value="View"> -->
<input type="submit" value="Pay" style="background-color:#FFFFC9; color:#660066; height:40px; width:100px; font-weight: 900;"/>  			  	
  			  	
  			  	
  			 </form> 
            
            
            </div>
             <div id="tabs-3">
              <form action=TransferServlet>				
				<br><br>
  				<%="From which account?"%>
				<br>
				<%for(int i = 0; i < u.getAccounts().size(); i++){
             	int val = i;
             	String store = "" + i;
             	//session.setAttribute("val", i);
             	if(u.getAccounts().get(i).getType().equals("Checking") || u.getAccounts().get(i).getType().equals("Savings")){
             %>
				<input type="radio" name="transAcc" value=<%=store%> checked> <%=u.getAccounts().get(i).getType() + " #" + u.getAccounts().get(i).getAccountId() %><br><br>
				
				<% 
             	}
             }
				%>
				<%= "To which account? (Account#)" %>
				<br>
				<input type="text" name="target">
				<br>
				<%= "Amount to pay" %>
				<br>
				<input type="text" name="transAmmount">
				<br>
  		
  			  	<!-- <input type="Submit" value="View"> -->
<input type="submit" value="Transfer" style="background-color:#FFFFC9; color:#660066; height:40px; width:100px; font-weight: 900;"/>  			  	
  			  	
  			 </form>
  			 </div>
            </div>
              <div class=row>
    	<div class="col-md-3">
     		<ul class="nav nav-pills nav-stacked"><br><br>
        	<li class="active"><a href="#">More Stuffs</a></li>
        	<li class="dropdown">               
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
  </div>

</body>

</html>