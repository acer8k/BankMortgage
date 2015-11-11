<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tax Form</title>
</head>
<body>
<link rel="stylesheet" href="css_styles/bootstrap.homescreen.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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

         <li><a href="homescreen.jsp">Home</a></li>
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
      <li><a href="aboutus.jsp"></span> About Us</a></li>
        <li><a href="contact2.jsp"></span> Contact Us</a></li>
        <li><a href="welcome.jsp"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

   <div align="right"> 
 <table border="0" width="80%" cellpadding="15" align="center" >
                <thead>
                    <tr>
                        <th colspan="5" style="font-family: sans-serif;" bgcolor="gold">Tax Forms</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><a href = "pdfs/f1040.pdf">Form 1040</a></br>Us Individual Income Tax Return</td>
                        <td>Annual Income tax return filed by citizens or residents of the United States.
                        <embed src="pdfs/f1040.pdf" width="400" height="275" type='application/pdf'>
                        </td>
                    </tr>     
                    <tr>
                        <td><a href = "pdfs/fw9.pdf">Form W-9</a></br>Request for Taxpayer Identification Number and Certification</td>
                        <td>Used to request a taxpayer identification number for reporting on an information return the amount paid.
                        <embed src="pdfs/fw9.pdf" width="400" height="275" type='application/pdf'>
                        </td>
                    </tr>
                    <tr>
                        <td><a href = "pdfs/fw4.pdf" >Form W-4</a></br>Employee's Withholding allowance certificate</td>
                        <td>Complete form W-4 so that employer can withhold the correct federal income tax.
                        <embed src="pdfs/fw4.pdf" width="400" height="275" type='application/pdf'></td>
                    </tr>
                    <tr>
                </tbody>
            </table>
</body>
</html>