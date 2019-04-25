package com.revature.projectone.service;

import com.revature.projectone.beans.*;
import com.revature.projectone.dao.*;


public class AuthService {

		public boolean authEmployee (String username, String password) {
			System.out.println(username);
			ReimbursementDAOImpl daoimpl = new ReimbursementDAOImpl();
			boolean auth = daoimpl.authenticateEmployee(username, password);
			System.out.println(auth);
			return auth;
			}
		
		public Employee findEmployee(String username, String password) {
			ReimbursementDAOImpl daoimpl = new ReimbursementDAOImpl();
			Employee emp = daoimpl.retrieveEmployee(username, password);
			return emp;
		}
		
		public void updateEmployee(Employee emp) {
			ReimbursementDAOImpl daoimpl = new ReimbursementDAOImpl();
			daoimpl.updateEmployeeInfo(emp);
			System.out.println("hello is anyone out there?!");
		}
		
		
}
