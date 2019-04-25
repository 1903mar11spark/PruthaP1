package com.revature.projectone.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.projectone.beans.Reimbursement;
import com.revature.projectone.dao.ReimbursementDAOImpl;
import com.revature.projectone.service.AuthService;


public class PendingEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ReimbursementDAOImpl theDao = new ReimbursementDAOImpl();
    
    public PendingEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session !=null && session.getAttribute("username")!= null) {
			try {
				int employeeId = (int)session.getAttribute("employeeId");
				System.out.println(employeeId);
				List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
				reimbs =theDao.getPendingEmployee(employeeId);
				String getItYas = new ObjectMapper().writeValueAsString(reimbs);
				response.getWriter().write(getItYas);
				System.out.println("is anyone out there");
				System.out.println(getItYas);
			
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("{\"viewpending\":null}");
				System.out.println(session + "viewpending");
			} 
		}else {
				response.getWriter().write("{\"viewpending\":null}");
				System.out.println(session + "viewpending");
			}
		}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
