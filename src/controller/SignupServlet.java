package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import static dao.AuthDAO.*;

/**
 * Servlet implementation class SignupServlet
 */
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		
		String check = request.getParameter("check"); 
		/*USER TABLE*/
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		
		/*USER_PROFILE TABLE*/
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		String phonenumber = request.getParameter("phonenumber");
		
		//String role = request.getParameter("role");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		
		final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		String url="";
		String msg="";
		
		int userId=0;
		boolean available=false;
		boolean status;
		
		int flag=0;
		int eflag=1;
		try{
			
		if(username.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Username missing!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		else{
			if(request.getParameter("check")!= null){
				available = checkUserNameAvailable(username);
				if(available){
					url = "/signup.jsp";
					msg = msg + "\nUsername Available!";
					request.setAttribute("msg", msg);
					flag=1;
				}
				else{
					url = "/signup.jsp";
					msg = msg + "\n Username NOT available, please choose another!\n";
					request.setAttribute("msg", msg);
					flag=0;
				}
			}
		}
          /* USER TABLE*/
		if(firstname.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Firstname missing!!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(lastname.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Lastname missing!!!!";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(email.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n EMAIL not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		else{
			eflag = 1;
			if(!email.matches(emailPattern)){
				url = "/signup.jsp";
				msg = msg + "\nPlease fill-in Valid Email";
				request.setAttribute("msg", msg);
				eflag=0;
			}
		}
		
		  /* USER_PROFILE TABLE*/
		if(address.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Address not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(city.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n City not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(state.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n State not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(zipcode.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Zipcode not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(phonenumber.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Phone # not entered";
			request.setAttribute("msg", msg);
			flag=0;
		}
		
		
		 /* PASSWORD VALIDATIONS*/
		
		if(password.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Please fill-in Password";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(cpassword.length()==0){
			url = "/signup.jsp";
			msg = msg + "\n Please type Confirm password again";
			request.setAttribute("msg", msg);
			flag=0;
		}
		if(! password.equals(cpassword)){
			url = "/signup.jsp";
			msg = msg + "\nPassword does not match!";
			request.setAttribute("msg", msg);
			flag=0;
		}

		else if(username.length()!=0 && firstname.length()!=0 && lastname.length()!=0 && eflag==1
				&& address.length()!=0 && city.length()!=0 && state.length()!=0 && zipcode.length()!=0 && phonenumber.length()!=0
				&& password.length()!=0 && cpassword.length()!=0 && password.equals(cpassword))
			flag = 1;
		if(flag ==1){
			userId = enterNewUser(username, password, email);
			
			if(userId >0){
				status = enterUsername(userId, firstname, lastname,
						address, city, state, zipcode, phonenumber);
				if(status == true){
					msg = "Your bank account is created successfully!";
					request.setAttribute("msg", msg);
					url = "/login.jsp";
				}
				else{
					msg = "UserName Insert Failed, ERROR";
					request.setAttribute("msg", msg);
					url = "/signup.jsp";
				}
			}
			else{
				msg = "Cannot create account, try again!";
				request.setAttribute("msg", msg);
				url = "/signup.jsp";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		DB_close();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}

















