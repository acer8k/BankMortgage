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
public class AdminViewAllMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String checkingAccount = "checkAccn.jsp";
	private static final String savingAccount = "savAccn.jsp";
	private static final String MortgageAccount = "mortAccn.jsp";
	private static final String TranscationDetails = "transDetails.jsp";
	private static final String AccountShow = "/AccountShow.jsp";
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminViewAllMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession mySes = request.getSession();
		
		ArrayList<User_Profile> output = dao.GetData.getAllProfiles();
		
		mySes.setAttribute("mems", output);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminAllMemberShow.jsp");
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
