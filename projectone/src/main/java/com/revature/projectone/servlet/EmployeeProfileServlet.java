package com.revature.projectone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.projectone.service.*;


public class EmployeeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public EmployeeProfileServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("profile.html").forward(request, response);
		HttpSession session = request.getSession();
		int employeeId = (int)session.getAttribute("employeeId");
		int employeeLevel = (int)session.getAttribute("employeeLevel");
		String firstName = session.getAttribute("firstName").toString();
		String lastName = session.getAttribute("lastName").toString();
		
		//PrintWriter out = response.getWriter();
		//out.println("Is this working SOS "+ employeeId);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
