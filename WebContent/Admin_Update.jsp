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
<title>Update Users</title>
</head>
<body>
        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/bank"
                           user="root"  password="4580"/>
                           
 		<sql:query dataSource="${dbsource}" var="result">
            SELECT * from users where user_Id=?;
            <sql:param value="${param.user_Id}" />
        </sql:query>  
                <form action="Admin_Update_DB.jsp" method="post">
            <table align="center">
                    <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <tr>
                        <th>EMAIL</th>
                        <td><input type="hidden" value="${param.user_Id}" name="user_Id"/>
                         	<input type="text" value="${row.email}" name="email" /></td>
                        </tr> 

                        <td><input type="submit" class="btn btn-success" value="Confirm"/></td>
                        <td><a href="Admin_Index.jsp" class="btn btn-primary" role="button">Back to Admin INDEX</a></td>
                </c:forEach>
            </table>
            </form>                  
</body>
</html>