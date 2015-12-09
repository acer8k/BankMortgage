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
<title>Admin Search</title>
</head>
<body>
<sql:setDataSource var="emp" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/bank"
     user="root"  password="4580"/>
        <h1>Employee Data</h1>
        <form name="empForm" action="Admin_Search.jsp" method="post">
           
            <div>
                <table border="1">
                    <thead>
                        <tr>
                            <th style="alignment-adjust: auto">Employee Id:</th>
                        <td><input type="text" name="username" value="<%=request.getParameter("username")%>" size="20" /></td>
                        </tr>
                        <tr>
                       <center> <input type="submit" value="GetData" /></center>
                                </td>
                        </tr>
                    </thead>
                </table>
 <table border="1">
                    <thead>
                        <tr>
                            <th>username::</th>
                        </tr>
                    </thead>
                    <tbody>
          
                        <%
   out.println("select *from emptable emp where emp.empId like '%"+request.getParameter("empId")+"%' and "
           + "               emp.empName like '%"+request.getParameter("empName")+"%' and"
           + " emp.empSalary like '%"+request.getParameter("empSalary")+"%'   ");
   
   out.println("select count(*) from emptable");
                            %>
                    <sql:query var="empData" dataSource="${emp}">
                        select * from users where
                        users.username like '%<%=request.getParameter("username")%>%'
                                         </sql:query>


  
                       <c:forEach var="row" items="${empData.rows}">
<tr>
   <td><c:out value="${row.username}"/></td>
   <td><c:out value="${row.password}"/></td>
  <td><a href="Admin_Update.jsp?user_Id=<c:out value="${row.user_Id}"/>">Update</a></td>
</tr>
</c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
</body>
</html>