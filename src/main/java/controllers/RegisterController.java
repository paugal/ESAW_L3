package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class FormController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String sqlResponse;
    private String view;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	   System.out.print("RegisterController: \n");
		
	   try {
	
		   User user = new User();
		   ManageUsers manager = new ManageUsers();
		   BeanUtils.populate(user, request.getParameterMap());
		
		   if (manager.isComplete(user) && !manager.checkErrors(user)) {
				sqlResponse = manager.addUser(
						user.getUsername(),user.getFullName(),user.getPhoneNumber(),
						user.getLocation(),user.getMail(),user.getPwd1());
				manager.finalize();
				System.out.println(sqlResponse);
				// correct sql insertion -> next screen 
				if("".equals(sqlResponse)) {	
					correctRegister();
					
				// incorrect sql insertion -> same screen 
				} else {
					incorrectRegister();
					request.setAttribute("user", user);
					request.setAttribute("errorMessage", errorHandler(sqlResponse));}
				
			// Not complete form or errors	
		   	} else {
			   	incorrectRegister();
		   		request.setAttribute("user",user);}

		   RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		   dispatcher.forward(request, response);
	   
	   } catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
	   }
		
	}
	
	public String errorHandler(String errmg) {
		String[] aux = errmg.split(" ");
		String aux2 = (aux[aux.length-1]).substring(7);
		aux2 = aux2.substring(0, aux2.length()-1);
		switch(aux2) {
			case "PRIMARY":
				aux2 = "user name";
				break;
			case "phoneNumber":
				aux2 = "phone number";
				break;
		}
		errmg = "Sorry, this " + aux2 + " is already in use!";
		return errmg;
	}
	
	public void correctRegister() {
		view = "ViewLoginForm.jsp";
		System.out.println(" user ok, forwarding to ViewLoginForm");
	}
	
	public void incorrectRegister() {
		view = "ViewRegisterForm.jsp";
		System.out.println(" forwarding to ViewRegisterForm");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
