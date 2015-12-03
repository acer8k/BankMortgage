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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
 	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>
table, th, td {
    
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
body {
	background-color :#ffffd9;
	margin: 0;
	padding: 0;
}

</style> 	
<title>Admin Index</title>
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
<form class="navbar-form navbar-left" name="empForm" action="Admin_Index.jsp" method="post">
  <div class="form-group">
    <input type="text" class="form-control" placeholder="USERNAME" name="username" value="" size="20">
  </div>
  <button type="submit" class="btn btn-success">SEARCH</button>
  
       <sql:setDataSource var="emp" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/bank"
     user="root"  password="4580"/>
     
     <br><br><br><br>
           <form name="empForm" action="Admin_Index.jsp" method="post">
           
            <div>
 			<table align="center" width=55%  border="1">
                    <thead>
                        <tr>
                         <th bgcolor="#660066"><font color="yellow">USERNAME</font></th>
                         <th bgcolor="#660066"><font color="yellow">EMAIL</font></th>
                             <th>Action: </th>
                          </tr>
                    </thead>
                    <tbody>
                    <sql:query var="empData" dataSource="${emp}">
                        select * from users where
                        users.username like '%<%=request.getParameter("username")%>%'
                                         </sql:query>


  
                       <c:forEach var="row" items="${empData.rows}">
<tr>
   <td><c:out value="${row.username}"/></td>
   <td><c:out value="${row.email}"/></td>
  <td><a href="Admin_Update.jsp?user_Id=<c:out value="${row.user_Id}"/>" class="btn btn-danger">Update</a></td>
</tr>
</c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
  
</form>
        
        
    </body>
</html>