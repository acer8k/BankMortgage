/**
 * 
 */

var attempt = 3; // Variable to count number of attempts.
// Below function Executes on click of login button.
function validate(){
var password = document.getElementById("4580").value;
if ( password == "4580"){
alert ("Login successfully");
window.location = "mortgage_calc.jsp"; // Redirecting to other page.
return false;
}
else{
attempt --;// Decrementing by one.
alert("You have left "+attempt+" attempt;");
// Disabling fields after 3 attempts.
if( attempt == 0){
document.getElementById("password").disabled = true;
document.getElementById("submit").disabled = true;
return false;
}
}
}