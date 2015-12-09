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

@WebServlet("/indexServlet")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String welcome = "welcome.jsp";
	private static final String homescreen = "homescreen.jsp";
	private static final String register = "register.jsp";
	
	 public indexServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		 HttpSession mySession = request.getSession();
		 User_Profile myUser = (User_Profile)mySession.getAttribute("user_profile");
		 String mAcc = request.getParameter("mortAccn");
		 String cAcc = request.getParameter("checkAcc");
		 String amm = request.getParameter("payAmmount");
		 
		 if(new Double(amm).doubleValue() > -1) {
			 Transaction check = checkFunds(new Double(amm).doubleValue()*-1,myUser.getAccounts().get(new Integer(cAcc).intValue())); 
		 	if(check != null){
			 Transaction result = checkFunds(new Double(amm).doubleValue()*1,myUser.getAccounts().get(new Integer(mAcc).intValue()));
		 	 
			 int newCheck = dao.InsertData.insertTransaction(check); 
		 	 int newMort = dao.InsertData.insertTransaction(result);
		 	 dao.InsertData.makeTransaction(check);
		 	 dao.InsertData.makeTransaction(result);
		 	 
		 	 if(newMort == -1 || newCheck == -1){
		 		 mySession.setAttribute("error", "There was a problem inserting into the DB");
		 	 }
		 	 mySession.setAttribute("returnMes", "The Money has been transfered.");
		 	}	
		 }
		 	
		 	myUser = dao.GetData.getProfileFromId(myUser.getUserId());
		 	mySession.setAttribute("user_profile", myUser);
		 	
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