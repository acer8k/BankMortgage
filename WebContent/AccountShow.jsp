<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details</title>
</head>
<body>
<%
User_Profile myUser = (User_Profile)session.getAttribute("user");
Integer currentAccount = (Integer)session.getAttribute("curAcc");
ArrayList<Transaction> history = (ArrayList<Transaction>)session.getAttribute("history");

%>

<%= "Details for " + myUser.getAccounts().get(currentAccount.intValue()).getType() + " account #" + myUser.getAccounts().get(currentAccount.intValue()).getAccountId()  %>
<br>
<%="Balance: " + myUser.getAccounts().get(currentAccount.intValue()).getBalance()%>
<br>
<h3>HISTORY</h3>
<%="---------------------------\n" %>
<br>
<%="Transaction ID\t\t\tType\t\t\tAmount\t\t\tAccount\t\t\tDate\t\t\tTime\t\t\tPervious Balance" %>
<%	for(int i = history.size() - 1; i > -1;i--){
%>
<br>
<% 
	out.print(history.get(i).toString());
	}
%>


</body>
</html>