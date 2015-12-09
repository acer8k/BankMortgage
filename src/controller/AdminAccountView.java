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
public class AdminAccountView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String checkingAccount = "checkAccn.jsp";
	private static final String savingAccount = "savAccn.jsp";
	private static final String MortgageAccount = "mortAccn.jsp";
	private static final String TranscationDetails = "transDetails.jsp";
	private static final String AccountShow = "/AccountShow.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAccountView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			String role = request.getParameter("selectedAcc");
			HttpSession mySession = request.getSession();
			ArrayList<User_Profile> owners = new ArrayList<User_Profile>();
			
			Account curAcc = dao.GetData.getAccountFromId(new Integer(role).intValue());
			
			if(curAcc != null){
		
				ArrayList<Transaction> out = new ArrayList<Transaction>();
				out = GetData.getHistory(curAcc.getAccountId());
				owners = dao.GetData.getOwners(curAcc.getAccountId());
				mySession.setAttribute("owners", owners);
				mySession.setAttribute("history", out);
				mySession.setAttribute("curAcc", curAcc);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminAccountShow.jsp");
		dispatcher.forward(request, response);	
			}
			else{
				mySession.setAttribute("msg", "No such account.");
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
