<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="./css/main.css" type="text/css" />
<title>UAlbany's Cafe</title>
<link rel="stylesheet" href="./css/jquery-ui.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"><!-- <for header n footer> -->
  <script src="./js/jquery-1.10.2.js"></script>
<script src="./js/jquery-ui.js"></script>
 
<script>
    $(function() {
        $("#tabs").tabs();
    });
</script>
  

<style>
body {
    background-color: #E1D7FA;
     }

</style>

</head>
<body>

      <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="containter">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!--    <a href="#" class="navbar-brand">UALBANY's Cafe</a>
         -->   
         <a href="#" class="navbar-brand">UAlbany Cafe</a>
            </div>  
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="SEfirst.html"><img src="images/homego.png" style="width:35px; height:30px;">HOME</a></li>
                        <li><a href="SEfirst.html"><img src="images/CART.png" style="width:40px; height:35px;">MY CART</a></li>
                        <li><a href="Login.jsp" ><img src="images/logout.png" style="margin-left:300px;width:25px; height:25px;">LOGOUT</a></li>
                        
                
                           
                            </ul>   
                        </li>
                    </ul>
                </div>
        </div>
    </div>

<div><br><br><br><br><br>
</div>
    <h3><%
String name=request.getParameter("username");
out.print("WELCOME "+name);
%></h3>

<p id="demo"></p>
 <sql:setDataSource var="myDS" driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/cafe" user="root" password="rootSquare89" />
    
  
<input type="submit" onclick="inserttable()" value="SEE HM"></input> 

  <!-- <div id="formatss" style="left: 100px; right:200px;"> 
   -->  
 <!-- <button type="button" onclick="inserttable()">Request data</button> -->
 
    <div id="tabs" style="left: 50px; right:200px;">
        <ul>
            <li><a href="#tabs-1">Food</a></li>
            <li><a href="#tabs-2">Beverages</a></li>
            
        </ul>
        <div id="tabs-1">
            <div id="horizBorder"></div>
            <div id="register">
                <!-- <form action="CatalogueCon" method="post"> -->
                <sql:query var="listMenu" dataSource="${myDS}">
                    select *from Product where Itemid=1;
                                        
            </sql:query>

                <div align="left">
                    <table border="0" cellpadding="0">
                         <caption>
                            <h2>VIEW PRODUCTS</h2>
                          </caption>
                       
                 <c:forEach var="stud" items="${listMenu.rows}">
                  <tr>
                              <%--  <td><c:out value="${stud.productID}" />
                                <c:out value="${stud.productName}" /></td>
                                <td><c:out value="${stud.productDesc}" /></td> --%>
                                <%-- <td><c:out value="${stud.EMAIL}" /></td>
                                <td><c:out value="${stud.TEAM_ID}" /></td>
                                <td><c:out value="${stud.ROLE}" /></td> --%>
                         <td></br></br><img src="${stud.ImagePath}" alt="${stud.ImageName}" width="250px"></br>
                         </td>
                          <td><h3>${stud.ImageName}</h3><p>${stud.productDesc}</p><p>Price:${stud.price}</p>ID:${stud.productID}   </td> 
                          <td>Quantity:<select id="${stud.productID}" >
                          <option value="0">0</option><option value="1">1</option><option value="2">2</option>
                          <option value="3">3</option><option value="4">4</option>
                          <option value="5">5</option><option value="6">6</option>
                          <option value="7">7</option><option value="8">8</option>
                          <option value="9">9</option><option value="10">10</option></select>
                          </td>                    
        
                          <td><input name="${stud.productID}" type="reset" onclick="getproduct(this)" value="Add to Cart"></input></td>   
        
                </tr>
                                 
        
                </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        
        
        <div id="tabs-2">
            <div id="horizBorder"></div>
            <div id="register">
                <!-- <form action="UpdateController" method="post"> -->
                <sql:query var="listproducts" dataSource="${myDS}">
                    SELECT *FROM Product where Itemid=2;
                    </sql:query>

                <div align="float: left">
                    <table border="0" cellpadding="0">
                        <caption>
                            
                        </caption>
                        
       <c:forEach var="stud" items="${listproducts.rows}">
                   
                            <tr>
                   
                             
                        <td></br></br><img src="${stud.ImagePath}" alt="${stud.ImageName}" width="250px"></br></td>
                        <td><h3>${stud.ImageName}</h3><p>${stud.productDesc}</p><p>Price:${stud.price}</p> ID:${stud.productID} </td>                     
                         <td>Quantity:<select name="day" id="dd">
           <option>0</option><option>1</option><option>2</option><option>3</option><option>4</option><option>5</option><option>6</option>
            <option>7</option><option>8</option><option>9</option><option>10</option>
        </select></td>
        <td><input type="submit" value="Add to Cart"></input> </td>    
        
        
        
                            
                            </tr>
                        </c:forEach>
                                                    
                    </table>
                </div>
            </div>
        </div>
        
        
        
   </div>
    

<div style="margin-top:20px; "><br><br><br><br></div>
    



<footer>
    <div class="navbar navbar-inverse navbar-fixed-bottom" style="margin-top:20px"; >
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#footer-body">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                
            </div>
            <div class="navbar-collapse collapse" id="footer-body">
                <ul class="nav navbar-nav">
                
                                                
                    <li><a href="Contactus.jsp">Contact Us</a></li>
                    <li><a href="userreview.jsp">User Review</a></li>
                    <li><a href="http://www.albany.edu/">© copyright SUNY Albany</a></li>
                    
                </ul>
            </div>
        </div>
    </div>
</footer>
    
        
  <script>
 var quan;
 var map = [];
 
 var formData = new FormData();
var url = "";
  function getproduct(ele){
	  var smallMap = {};
	  var prod=ele.name;
      console.log(prod);
     var tempQuan = $("#"+prod).val()
     console.log(tempQuan);
     smallMap["id"]=prod;
     smallMap["quan"]=tempQuan;
      map.push(smallMap);
    }
 
function inserttable(){
	


console.log(map);

var jsonMap = JSON.stringify(map);

console.log(jsonMap);

$.ajax({
	  method: "POST",
	  url: "./CartCon",
	  data: { q: jsonMap }
	})
	  .done(function( msg ) {
	    console.log( "Data Saved: " + msg );
	  });

}   
// document.getElementById("demo").innerHTML = inserttable();

</script> 
    
    
</body>
</html>


