package controller;

import static controller.WelcomeEmail.sendMail;
import static dao.AuthDAO.DB_close;
import static dao.AuthDAO.enterNewUser;
import static dao.AuthDAO.enterUsername;
import static dao.UpdateUserDAO.updateUserInfo;
import static dao.ConnectDB.ConnectToDB;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UpdateUserDAO;
import model.User_Profile;

/**
 * Servlet implementation class UserUpdateServlet
 */
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		HttpSession mySes = request.getSession();
		User_Profile u = (User_Profile)mySes.getAttribute("user_profile");
		/*USER_PROFILE TABLE*/
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state_country = request.getParameter("state_country");
		String zipcode = request.getParameter("zipcode");
		String phone_number = request.getParameter("phone_number");
		u.setAddress(request.getParameter("address"));
		u.setCity(request.getParameter("city"));
		u.setState_country(request.getParameter("state_country"));
		u.setZipcode(new Integer(request.getParameter("zipcode")).intValue());
		u.setPhone_number(request.getParameter("phone_number"));
		
		
		System.out.println(phone_number);
		
				
		String url="";
		String msg="";
		
		String userId= request.getParameter("user_Id");
		int uid = Integer.parseInt(userId);
		System.out.println("UID "+userId);
		boolean status;
		
		int flag=0;
		int eflag=1;
		
		try{
			  /* USER_PROFILE TABLE*/
			if(address.length()==0){
				url = "/USER_update.jsp";
				msg = msg + "\n Address not entered";
				request.setAttribute("msg", msg);
				flag=0;
			}
			if(city.length()==0){
				url = "/USER_update.jsp";
				msg = msg + "\n City not entered";
				request.setAttribute("msg", msg);
				flag=0;
			}
			if(state_country.length()==0){
				url = "/USER_update.jsp";
				msg = msg + "\n State not entered";
				request.setAttribute("msg", msg);
				flag=0;
			}
			if(zipcode.length()==0){
				url = "/USER_update.jsp";
				msg = msg + "\n Zipcode not entered";
				request.setAttribute("msg", msg);
				flag=0;
			}
			if(phone_number.length()==0){
				url = "/USER_update.jsp";
				msg = msg + "\n Phone # not entered";
				request.setAttribute("msg", msg);
				flag=0;
			}
			else if(address.length()!=0 && city.length()!=0 && state_country.length()!=0
					&& zipcode.length()!=0 && phone_number.length()!=0)
				System.out.println("inside");
				flag = 1; //flag value not chaning.
			if(flag ==1){
						
				if(uid > 1){
					status = updateUserInfo(address,city,state_country,zipcode,phone_number,uid);
					if(status == true){
						msg = "account updated sucessfully!";
						request.setAttribute("msg", msg);
						url = "/accsetting.jsp";
						//sendMail(email);				
						
					}
					else{
						msg = "failed to update, ERROR";
						request.setAttribute("msg", msg);
						url = "/USER_update.jsp";
					}
				}
				else{
					msg = "ABORT";
					request.setAttribute("msg", msg);
					url = "/USER_update.jsp";
				}
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		DB_close();
		
		mySes.setAttribute("user_profile", u);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}




}
