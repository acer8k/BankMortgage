<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mortgage Calculator</title>
<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
<link rel="stylesheet" type="text/css" href="css_styles/mort_calc.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="javascripts/cc.js"></script>

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
        <li><a href="contactus.jsp"></span> Contact Us</a></li>
        <li><a href="welcome.jsp"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
	
	<div class="smpc-div">
		<form name=mortgagecalc method=POST>
			<p>
				How much will you be borrowing?<br> <input type=text
					onkeypress="return validNumber(event)" name=loan size=10> <span
					class="smpc-error" id="loanError"></span>
			</p>
			<p>
				What will be the term of this mortgage (in years)?<br> <input
					type=text onkeypress="return validNumber(event)" name=years size=5>
				<span class="smpc-error" id="yearsError"></span>
			</p>
			<p>
				What will be the interest rate?<br> <input type=text
					onkeypress="return validNumber(event)" name=rate size=5> <span
					class="smpc-error" id="rateError"></span>
			</p>
			<input type=button onClick="return myPayment()" value=Calculate>
			<input type=button onClick="return myPaymentReset()" value=Reset>
		</form>
		<small>Instructions: Enter numbers and decimal points. No
			commas or other characters.</small>
		<p class="smpc-monthlypayment" id="monthlyPayment"></p>
		<p class="smpc-friendlyreminder" id="friendlyReminder">This is
			your principal + interest payment, or in other words, what you send
			to the bank each month. But remember, you will also have to budget
			for homeowners insurance, real estate taxes, and if you are unable to
			afford a 20% down payment, Private Mortgage Insurance (PMI). These
			additional costs could increase your monthly outlay by as much 50%,
			sometimes more.</p>
	</div>
	
</body>
</html>