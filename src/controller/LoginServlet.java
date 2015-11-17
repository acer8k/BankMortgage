package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import controller.CaptchasDotNet;
import model.*;
import dao.GetData;
import dao.AuthDAO;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
/*	private static final String success = "success.jsp";  
	private static final String retailer = "retailer.jsp";
	private static final String customer = "customer.jsp";
	private static final String wholesaler = "wholesaler.jsp";
	private static final String supplier = "supplier.jsp";*/
	private static final String redirect = "error.jsp";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
    	
    	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		//String role = request.getParameter("role");
		String msg="";
		String url="";
		
	
		CaptchasDotNet captchas = new controller.CaptchasDotNet(
				  request.getSession(true),     // Ensure session
				  "Trudgling",                       // client
				  "P1W9WnjyjYZ1di1F0NegiWalX2rOUJS1ieNTinvY"                      // secret
				  );
				// Read the form values
		
		/*
		 * 
		 * CAPTCHA
		 * look at authentication.CaptchasDotNet for 
		 * what values check can return.
		 */
		String capPhrase = request.getParameter("capPhrase");
		char isHuman = captchas.check(capPhrase);	
		/*
		 * 
		 * CAPTCHA
		 * 
		 */
		
		String contextPath = ""; //"/SE_Lab1/WebContent/"; //request.getContextPath();
		
		
		String loggedIn = "false";
		if(username.length() == 0 || password.length() == 0 || isHuman != 't'){
			if(username.length() == 0 && password.length() == 0){
				msg = "Please fill in all the details !!!";
				url = "/login.jsp";
				loggedIn = "false";
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);
			}			
			else if(username.length()==0){
				msg = "Username not entered !!!";
				url = "/login.jsp";
				loggedIn = "false";
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);
			}
			else if(password.length()==0){
				msg = "Password not entered !!!";
				url = "/login.jsp";
				loggedIn = "false";
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);
			}
			else if(isHuman != 't'){
				msg = "Could not verify human user!!!";
				url = "/login.jsp";
				loggedIn = "false";
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);
			}

		}
		else{
			int ID = dao.AuthDAO.checkUserPass(username, password);
			if(ID!=-1 ){ //User Is Legit!!
				loggedIn = "true";
				HttpSession se = request.getSession();
				se.setAttribute("user_profile", dao.GetData.getProfileFromId(ID));
				se.setAttribute("user", dao.AuthDAO.getUserById(ID));
				se.setAttribute("loggedIn", loggedIn);
				url = "/index.jsp";
				url = "/homescreen_unlocked.jsp";
				msg = "Login Successful!";
				
				se.setAttribute("returnMes", "");
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);
			}
			else{
				msg = "Invalid Login, please check username or password.";
				url = "/login.jsp";
				loggedIn = "false";
				request.setAttribute("loggedIn", loggedIn);
				request.setAttribute("msg", msg);			
			}					
		}
	//	DB_close(); //close db.
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);	
		
	}

}






















