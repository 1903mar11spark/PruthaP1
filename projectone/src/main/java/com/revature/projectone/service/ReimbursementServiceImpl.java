package com.revature.projectone.service;

import java.util.List;

import com.revature.projectone.beans.Employee;
import com.revature.projectone.dao.ReimbursementDAO;
import com.revature.projectone.dao.ReimbursementDAOImpl;
import com.revature.projectone.service.*;

public class ReimbursementServiceImpl implements ReimbursementService {

	private ReimbursementDAO hello = new ReimbursementDAOImpl();
	
	@Override
	public List<Employee> allEmployees() {
		return hello.getEmployees();
	}

	
	
}
