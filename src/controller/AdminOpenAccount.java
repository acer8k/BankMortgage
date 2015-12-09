package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GetData;

import java.lang.Integer;
import java.util.*;

import model.*;
/**
 * Servlet implementation class ViewAccnType
 */
public class AdminOpenAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String checkingAccount = "checkAccn.jsp";
	private static final String savingAccount = "savAccn.jsp";
	private static final String MortgageAccount = "mortAccn.jsp";
	private static final String TranscationDetails = "transDetails.jsp";
	private static final String AccountShow = "/AccountShow.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOpenAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			String type = request.getParameter("accType");
			int user_Id = new Integer(request.getParameter("uId")).intValue();
			double balance = new Double(request.getParameter("initBal")).doubleValue();
			double intRate = new Double(request.getParameter("iRate")).doubleValue();
			HttpSession mySession = request.getSession();
			
			//User_Profile curMem = dao.GetData.getProfileFromId(new Integer(role).intValue());
			int acc_Id = dao.InsertData.createAccount(type, balance, intRate);
			
			if(acc_Id != -1){
				
				if(dao.InsertData.createOwnership(acc_Id, user_Id)){
				mySession.setAttribute("returnMes", "Account created.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin_Index.jsp");
				dispatcher.forward(request, response);	
				}
				else{
					mySession.setAttribute("returnMes", "Ownership creation failed.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin_Index.jsp");
					dispatcher.forward(request, response);
				}
			}
			else{
				mySession.setAttribute("returnMes", "Account creation failed.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Admin_Index.jsp");
				dispatcher.forward(request, response);
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
