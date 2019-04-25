package com.revature.projectone.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.projectone.dao.*;
import com.revature.projectone.beans.*;


public class SubmitReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	ReimbursementDAOImpl theDao = new ReimbursementDAOImpl();
   
    public SubmitReimbursementServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("profile.html").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		int employeeId = (int)session.getAttribute("employeeId");
		try {
			if (session != null && session.getAttribute("employeeId") != null) {
				boolean success;
				String type = request.getParameter("type");
				double amount = Double.parseDouble(request.getParameter("amount"));
				String description = request.getParameter("description");
				success = theDao.createReimbursement(type,description,amount,employeeId);
				if (success) {
					System.out.println("The reimbursement request has been created");
					response.sendRedirect("employeeprofile");
				} else {
					System.out.println("The reimbursement request could not be created at this time, please try again later");
					response.sendRedirect("employeeprofile");
				}}
		}
		catch (Exception e){
				e.printStackTrace();
				response.sendRedirect("employeeprofile");
		}
	}

}
