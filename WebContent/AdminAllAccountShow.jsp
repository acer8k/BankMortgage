<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
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
//Account currentAccount = (Account)session.getAttribute("curAcc");

ArrayList<Account> accounts = (ArrayList<Account>)session.getAttribute("accs");
session.setAttribute("returnMes", "");

%>



<br>
<h4>Hello <%=u.getFirstName()%>, here is the list of all registered user's account details.</h4>
<br>
<%--  <%="---------------------------\n" %>
<br>
<%="Account ID\t\t\tType\t\t\tBalance\t\t\tInterest Rate\t\t\t" %>
<%	for(int i = accounts.size() - 1; i > -1;i--){
%>
<br>
<% 
	out.print(accounts.get(i).getAccountId() + "\t" + accounts.get(i).getType() + "\t" + accounts.get(i).getBalance() + "\t" + accounts.get(i).getIntrestRate());
	}
%>  --%>

 <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/final_bank"
                           user="root"  password="4580"/>
 
        <sql:query dataSource="${dbsource}" var="result">
           select distinct user_Id, ownerships.account_Id, type, balance, intrestRate from ownerships inner join accounts on ownerships.account_Id = accounts.account_Id;
        </sql:query>
	<form>
            <table border="1" width="75%" align="center">
                <tr>
                    <th>Member ID</th>
                    <th>Account ID</th>
                    <th>Account Type</th>
                    <th>Balance</th>
                    <th>Interest Rate</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
					<td><c:out value="${row.user_Id}" /></td>
					<td><c:out value="${row.account_Id}" /></td>
					<td><c:out value="${row.type}" /></td>
					<td><c:out value="${row.balance}" /></td>
					<td><c:out value="${row.intrestRate}" /></td>

				</tr>
                </c:forEach>
            </table>
        </form>

</body>
</html>