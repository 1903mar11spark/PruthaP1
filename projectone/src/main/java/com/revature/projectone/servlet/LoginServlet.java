package com.revature.projectone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.projectone.beans.*;
import com.revature.projectone.dao.*;
import com.revature.projectone.service.*;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AuthService auth = new AuthService();
   
    public LoginServlet() {
        super();        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		request.getRequestDispatcher("home.html").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee emp = auth.findEmployee(username, password);
		
		if (auth.authEmployee(username,password)) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("employeeId", emp.getEmployeeId());
			session.setAttribute("firstName", emp.getFirstName());
			session.setAttribute("lastName", emp.getLastName());
			session.setAttribute("employeeLevel", emp.getEmployeeLevel());
			session.setAttribute("problem", null);
			response.sendRedirect("employeeprofile");
		} else {
			session.setAttribute("problem", "invalid credentials");
			response.sendRedirect("login");
		}
		
		PrintWriter out = response.getWriter();
		out.println("Is this working SOS "+ username);
	}

}
