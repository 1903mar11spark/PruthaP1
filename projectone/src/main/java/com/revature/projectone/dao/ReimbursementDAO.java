package com.revature.projectone.dao;

import java.util.List;
import java.sql.*;
import com.revature.projectone.beans.*;

public interface ReimbursementDAO {
	
	
	public List<Employee> getEmployees();
	
	
	public List<Employee> getEmployeesAndManagers();
	
	
	public boolean authenticateEmployee(String username, String password);
	public Employee retrieveEmployee(String username, String password);
	public boolean updateEmployeeInfo(Employee employee);
	public List<Reimbursement> getPendingEmployee(int employeeId);
	public List<Reimbursement> getApprovedEmployee(int employeeId);
	public List<Reimbursement> getDeniedEmployee(int employeeId);
	public List<Reimbursement> getAllEmployee(int employeeId); 
	public List<Reimbursement> getAllPendingMan(int employeeId);
	public List<Reimbursement> getAllResolvedMan();
	public void reimbursementDenied(int reimbursementId);
	public void reimbursementApproved(int reimbursementId);
	public boolean newEmployee (String firstName, String lastName, String email, int managerId, int employeeLevel);
	public boolean newLogin (String username, String password, String employeeId);
	public boolean createReimbursement (String reimbursementType, String reimbursementDescription, double reimbursementAmount, int employeeId);
	public void getImage (int reimbursementId, String reimbursementImage);
	
}
