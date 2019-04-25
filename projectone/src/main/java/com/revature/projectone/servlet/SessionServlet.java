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
import com.revature.projectone.beans.Employee;
import com.revature.projectone.beans.Reimbursement;
import com.revature.projectone.dao.ReimbursementDAOImpl;
import com.revature.projectone.service.AuthService;

public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AuthService auth = new AuthService();
	private ReimbursementDAOImpl theDao = new ReimbursementDAOImpl();
   
    public SessionServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		HttpSession session = request.getSession(false);
		if (session !=null && session.getAttribute("username")!= null) {
			try {
					String username = session.getAttribute("username").toString();
					String password = session.getAttribute("password").toString();
					int employeeId = (int)session.getAttribute("employeeId");
//					System.out.println(employeeId);
					switch (type) {
					case("displayEmployeeInfo"): {
						Employee emp = (auth.findEmployee(username, password));
						String getItYas = new ObjectMapper().writeValueAsString(emp);
						response.getWriter().write(getItYas);
						//System.out.println(getItYas);
						//System.out.println("PLEASE WORK GOODNESS");
						break;}
					case("displayPending"):{
//						List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
//						reimbs =theDao.getPendingEmployee(employeeId);
//						String getItYas = new ObjectMapper().writeValueAsString(reimbs);
//						response.getWriter().write(getItYas);
//						
//						System.out.println("is anyone out there");
//						System.out.println(getItYas);
						break;
						}
					 default: break;
					} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
