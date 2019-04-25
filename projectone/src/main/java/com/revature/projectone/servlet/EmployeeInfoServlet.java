package com.revature.projectone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EmployeeInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public EmployeeInfoServlet() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		int employeeId = (int)session.getAttribute("employeeId");
		int employeeLevel = (int)session.getAttribute("employeeLevel");
		String firstName = session.getAttribute("firstName").toString();
		String lastName = session.getAttribute("lastName").toString();
		System.out.println(session.getAttribute("employeeLevel".toString()));
		
		PrintWriter out = response.getWriter();
		out.println("Is this working SOS "+ employeeId);
		
		request.getRequestDispatcher("employeeinfo.html").forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("employeeinfo");
	}

}
