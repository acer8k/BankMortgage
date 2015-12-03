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
<title>Update</title>
</head>
<body>
        <sql:setDataSource var="dbsource" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost/bank"
                           user="root"  password="4580"/>
        <sql:update dataSource="${dbsource}" var="count">
            UPDATE user_profiles SET address = ?, city=? , state_country=? , zipcode=? , phonenumber=?
            WHERE user_Id='${param.user_Id}'
            <sql:param value="${param.address}" />
             <sql:param value="${param.city}" />
              <sql:param value="${param.state_country}" />
               <sql:param value="${param.zipcode}" />
            <sql:param value="${param.phonenumber}" />
        </sql:update>
        <c:if test="${count>=1}">
            <font size="5" color='green'>City and State updated successfully.</font>
              <a href="accsetting.jsp">Go To Account Page.</a>          
        </c:if>
    </body>
</html>