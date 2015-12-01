<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Account Info</title>
</head>
<body>
 
        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/bank"
                           user="root"  password="4580"/>
 
        <sql:query dataSource="${dbsource}" var="result">
            SELECT * from user_profiles where user_Id=?;
            <sql:param value="${param.user_Id}" />
        </sql:query>
        <form action="accsetting_update_db.jsp" method="post">
            <table border="0" width="40%">
                <caption>Update Info</caption>
                <tr>
                    <th>City</th>
                    <th>State</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td><input type="hidden" value="${param.user_Id}" name="user_Id"/>
                            <input type="text" value="${row.city}" name="city"/></td>
                        <td><input type="text" value="${row.state_country}" name="state_country"/></td>
                        <td><input type="submit" value="Update"/></td>
                    </tr>
                </c:forEach>
            </table>
            <a href="accsetting.jsp">Go Home</a>
        </form>
    </body>
</html>