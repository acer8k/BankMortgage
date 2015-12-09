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
public class AdminMemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String checkingAccount = "checkAccn.jsp";
	private static final String savingAccount = "savAccn.jsp";
	private static final String MortgageAccount = "mortAccn.jsp";
	private static final String TranscationDetails = "transDetails.jsp";
	private static final String AccountShow = "/AccountShow.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
			String role = request.getParameter("selectedMem");
			HttpSession mySession = request.getSession();
			
			User_Profile curMem = dao.GetData.getProfileFromId(new Integer(role).intValue());
			
			if(curMem != null){
				mySession.setAttribute("curMem", curMem);
				mySession.setAttribute("curUser", dao.AuthDAO.getUserById(curMem.getUserId()));
		
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminMemberShow.jsp");
				dispatcher.forward(request, response);	
			}
			else{
				mySession.setAttribute("msg", "No such Member.");
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
