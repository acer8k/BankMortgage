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

ArrayList<User_Profile> profiles = (ArrayList<User_Profile>)session.getAttribute("mems");
session.setAttribute("returnMes", "");
%>



<br>
<h3>Hello <%=u.getFirstName()%>, here is the list of all registered users.</h3>
<%="---------------------------\n" %>
<br>
<%-- <%="Member ID\t\t\tFirst Name\t\t\tLast Name\t\t\tPhone Number\t\t\t" %> --%>
<%-- <%	for(int i = profiles.size() - 1; i > -1;i--){
%> --%>
<br>
<%-- <% 
	out.print(profiles.get(i).getUserId() + "\t " + profiles.get(i).getFirstName() + "\t" + profiles.get(i).getLastName()  + "\t" + profiles.get(i).getPhone_number());
out.print(profiles.get(i).getUserId());
	}
%> --%>
 <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/final_bank"
                           user="root"  password="4580"/>
 
        <sql:query dataSource="${dbsource}" var="result">
           select users.user_Id, firstName, lastName, phonenumber, email, account_Type from user_profiles inner join users on user_profiles.user_Id= users.user_Id;
        </sql:query>
	<form>
            <table border="1" width="75%" align="center">
                <tr>
                    <th>Member ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Account Type</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
					<td><c:out value="${row.user_Id}" /></td>
					<td><c:out value="${row.firstName}" /></td>
					<td><c:out value="${row.lastName}" /></td>
					<td><c:out value="${row.phonenumber}" /></td>
					<td><c:out value="${row.email}" /></td>
					<td><c:out value="${row.account_Type}" /></td>

				</tr>
                </c:forEach>
            </table>
        </form>


</body>
</html>