package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.Login;
import models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String errorMessage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.print("LoginController: ");
		
		Login login = new Login();
		
	    try {
	    	
			ManageUsers manager = new ManageUsers();
	    	BeanUtils.populate(login, request.getParameterMap());
    		
	    	if (login.isComplete() ) {
	    		System.out.println("Login fields are complete");
	    		User user = manager.getUser(login.getUsername());
	    		
		    	if(user != null && user.getPwd1().compareTo(login.getPassword()) == 0) {
		    		System.out.println("login OK, forwarding to ViewLoginDone ");
			    	HttpSession session = request.getSession();
			    	session.setAttribute("user",login.getUsername());
			    	RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginDone.jsp");
				    dispatcher.forward(request, response);
				    return;
		    	} else {
		    		errorMessage = "Invalid credentials, try again";
				    request.setAttribute("errorMessage",errorMessage);
				}
	    	}

		    request.setAttribute("login",login);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
		    dispatcher.forward(request, response);
		    	
		    
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	    
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

