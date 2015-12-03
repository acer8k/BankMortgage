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
<title>Admin Page</title>
        <script>
            function confirmGo(m,u) {
                if ( confirm(m) ) {
                    window.location = u;
                }
            }
        </script>
</head>
<body>
 <h3>Search for User based on user_ID</h3>
        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/bank"
                           user="root"  password="4580"/>
 
  <form method="post" action="Admin.jsp" name="Query Input Form">
        <input type="text" name="query" value="" />
        <input type="submit" value="Submit Query" name="Submit" />
    </form>
    
<c:if test="${param.query}">
    <sql:query dataSource="${dbsource}" var="result">
    SELECT * FROM users WHERE username LIKE ?
    <sql:param value="${param.query}"/>        
  </sql:query>    
</c:if>
    
    <p> Query was <c:out value="${param.query}"/> </p>
  

</body>
</html>