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
public class ViewAccnType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String checkingAccount = "checkAccn.jsp";
	private static final String savingAccount = "savAccn.jsp";
	private static final String MortgageAccount = "mortAccn.jsp";
	private static final String TranscationDetails = "transDetails.jsp";
	private static final String AccountShow = "/AccountShow.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAccnType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			String role = request.getParameter("accn");
			HttpSession mySession = request.getSession();
			
		
		for(int i = 0 ; i < (int)((User_Profile)mySession.getAttribute("user")).getAccounts().size();i++){
			if(role.equals(""+i)){
				ArrayList<Transaction> out = new ArrayList<Transaction>();
				out = GetData.getHistory(((User_Profile)mySession.getAttribute("user")).getAccounts().get(i).getAccountId());
				mySession.setAttribute("history", out);
				mySession.setAttribute("curAcc", i);
			}
			
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(AccountShow);
		dispatcher.forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
