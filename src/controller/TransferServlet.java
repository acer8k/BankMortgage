package controller;

import java.io.IOException;
import java.util.*;
import java.lang.Double;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;
import dao.*;

@WebServlet("/TransferServlet")
public class TransferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String welcome = "welcome.jsp";
	private static final String homescreen = "homescreen.jsp";
	private static final String register = "register.jsp";
	
	 public TransferServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		 HttpSession mySession = request.getSession();
		 User_Profile myUser = (User_Profile)mySession.getAttribute("user_profile");
		 String transAcc = request.getParameter("transAcc");
		 String target = request.getParameter("target");
		 String amm = request.getParameter("transAmmount");
		 
		 if(new Double(amm).doubleValue() > 0) {
			 if(dao.GetData.getAccountFromId(new Integer(target).intValue()) != null){
			 Transaction check = checkFunds(new Double(amm).doubleValue()*-1,myUser.getAccounts().get(new Integer(transAcc).intValue())); 
		 	if(check != null){
			 Transaction result = checkFunds(new Double(amm).doubleValue()*1,dao.GetData.getAccountFromId(new Integer(target).intValue())); 
				
			 int newCheck = dao.InsertData.insertTransaction(check); 
		 	 int newMort = dao.InsertData.insertTransaction(result);
		 	 dao.InsertData.makeTransaction(check);
		 	 dao.InsertData.makeTransaction(result);
		 	 
		 	 if(newMort == -1 || newCheck == -1){
		 		 mySession.setAttribute("error", "There was a problem inserting into the DB");
		 	 }
		 	 mySession.setAttribute("returnMes", "The Money has been transfered.");
		 	}	
		 	else{
		 		mySession.setAttribute("returnMes", "Not enough funds in Account to complete the transaction.");
		 	}
		 		
		   }
			 else{
				 mySession.setAttribute("returnMes", "Not a valid dollar ammount to transfer.");
			 }
		 }
		 	
		 	myUser = dao.GetData.getProfileFromId(myUser.getUserId());
		 	mySession.setAttribute("user_profle", myUser);
		 	
		 	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/homescreen_unlocked.jsp");
			dispatcher.forward(request, response);
		}
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	 private Transaction checkFunds(double x, Account A){
		 return A.addTransaction(x);
	 }
	 
	 //private boolean checkFunds()
	 /*
	 private void makeTransaction(Transaction T){
		 //insert into DB
		 
		 //update account
	 }
	 
	 private void makeTransfer(Transaction from, Transaction to){
		 
	 }
	 
	 private Account getAccountData(int accNum){
		 return null;
	 }
	 
	 private ArrayList getAccountHistory(int start, int stop){
		  
	 }
	 */
}