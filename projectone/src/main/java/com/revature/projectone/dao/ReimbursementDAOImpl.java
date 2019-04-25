package com.revature.projectone.dao;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.revature.projectone.beans.*;
import com.revature.projectone.util.*;


public class ReimbursementDAOImpl implements ReimbursementDAO {

	//public static String url = "/Users/pruthapatel/RevatureSpace/projectone/src/main/Connection.properties";
	
	
//GETS ALL EMPLOYEES & ALL EMPLOYEE INFO IN DATABASE
	
	@Override
	public List<Employee> getEmployees() {
		PreparedStatement pstmt = null;
		List<Employee> employees = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "SELECT * FROM EMPLOYEE";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				int managerId = rs.getInt("REPORTS_TO");
				Employee employee = new Employee (employeeId, firstName, lastName, managerId);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e)	{
			e.printStackTrace();
		}
		return employees;
	}
	
//GETS ALL EMPLOYEES & THEIR MANAGAERS 
	
	@Override
	public List<Employee> getEmployeesAndManagers() {
		PreparedStatement pstmt = null;
		List<Employee> employees = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "SELECT E.EMPLOYEE_ID, E.FIRST_NAME, E.LAST_NAME, E.REPORTS_TO, (M.FIRST_NAME || ' ' || M.LAST_NAME) AS SUPERVISOR FROM EMPLOYEE E, EMPLOYEE M WHERE E.REPORTS_TO = M.EMPLOYEE_ID";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())	{
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				int managerId = rs.getInt("REPORTS_TO");
				String managerName = rs.getString("SUPERVISOR");
				Employee employee = new Employee (employeeId, firstName, lastName, managerId, managerName);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return employees;
	}

//VERIFIES LOGIN CREDENTIALS FOR THE EMPLOYEE 
	
	@Override
	public boolean authenticateEmployee(String username, String password) {
		PreparedStatement pstmt = null;
		boolean loginCred = false;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "SELECT LOGIN_CREDS, EMPLOYEE_ID FROM EMPLOYEE_LOGIN WHERE LOGIN_USERNAME=? AND LOGIN_PASSWORD=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				loginCred = true;
				return loginCred;
			} else {
				System.out.println("The Username and Password entered do not match");
				return loginCred;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginCred;
	}

//ALLOWS EMPLOYEES TO VIEW BASIC INFO WHEN LOGGED IN
	
	@Override
	public Employee retrieveEmployee(String username, String password) {
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = (SELECT EMPLOYEE_ID FROM EMPLOYEE_LOGIN WHERE LOGIN_USERNAME=? AND LOGIN_PASSWORD=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int employeeId = rs.getInt("EMPLOYEE_ID");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String email = rs.getString("EMAIL");
				int reportsTo = rs.getInt("REPORTS_TO");
				int employeeLevel = rs.getInt("EMPLOYEE_LEVEL");
				int loginCreds = rs.getInt("LOGIN_CREDS");
				return new Employee (employeeId, firstName, lastName, email,reportsTo, employeeLevel, loginCreds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
//ALLOWS EMPLOYEES TO MODIFY THEIR INFORMATION IN THE SYSTEM -- STILL NEED TO TEST
	
	@Override
	public boolean updateEmployeeInfo(Employee employee) {
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			con.setAutoCommit(false);
			String sql="UPDATE EMPLOYEE SET FIRST_NAME=?, LAST_NAME=?, EMAIL=? WHERE EMPLOYEE_ID =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setInt(4, employee.getEmployeeId());
			int change = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			if (change>0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//RETRIEVES PENDING REQUESTS FOR EACH EMPLOYEE ID

	@Override
	public List<Reimbursement> getPendingEmployee (int employeeId) {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID=? AND REIMBURSEMENT_STATUS = 'PENDING'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				String reimbursementType = rs.getString("REIMBURSEMENT_TYPE");
				String reimbursementDescription = rs.getString("REIMBURSEMENT_DESCRIPTION");
				String reimbursementStatus = rs.getString("REIMBURSEMENT_STATUS");
				double reimbursementAmount = rs.getDouble("REIMBURSEMENT_AMOUNT");
				int reimbursementManagedBy = rs.getInt("REIMBURSEMENT_MANAGED_BY");
				reimbs.add(new Reimbursement(reimbursementId, reimbursementType, reimbursementDescription, reimbursementStatus, reimbursementAmount, reimbursementManagedBy));
			}
			return reimbs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//RETRIEVES RESOLVED REQUESTS FOR EACH EMPLOYEE ID
	
	@Override
	public List<Reimbursement> getApprovedEmployee(int employeeId) {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID=? AND REIMBURSEMENT_STATUS = 'APPROVED'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				String reimbursementType = rs.getString("REIMBURSEMENT_TYPE");
				String reimbursementDescription = rs.getString("REIMBURSEMENT_DESCRIPTION");
				String reimbursementStatus = rs.getString("REIMBURSEMENT_STATUS");
				double reimbursementAmount = rs.getDouble("REIMBURSEMENT_AMOUNT");
				int reimbursementManagedBy = rs.getInt("REIMBURSEMENT_MANAGED_BY");
				reimbs.add(new Reimbursement(reimbursementId, reimbursementType, reimbursementDescription, reimbursementStatus, reimbursementAmount, reimbursementManagedBy));
				System.out.println("dao");
			}
			return reimbs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
//RETRIEVES DENIED REQUESTS FOR EACH EMPLOYEE ID

	@Override
	public List<Reimbursement> getDeniedEmployee(int employeeId) {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID=? AND REIMBURSEMENT_STATUS = 'DENIED'";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				String reimbursementType = rs.getString("REIMBURSEMENT_TYPE");
				String reimbursementDescription = rs.getString("REIMBURSEMENT_DESCRIPTION");
				String reimbursementStatus = rs.getString("REIMBURSEMENT_STATUS");
				double reimbursementAmount = rs.getDouble("REIMBURSEMENT_AMOUNT");
				int reimbursementManagedBy = rs.getInt("REIMBURSEMENT_MANAGED_BY");
				reimbs.add(new Reimbursement(reimbursementId, reimbursementType, reimbursementDescription, reimbursementStatus, reimbursementAmount, reimbursementManagedBy));
				System.out.println("dao");
			}
			return reimbs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//RETRIEVES ALL REQUESTS FOR EACH EMPLOYEE ID

	@Override
	public List<Reimbursement> getAllEmployee(int employeeId) {
		List<Reimbursement> reimbs = new ArrayList<Reimbursement>();
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, employeeId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
				String reimbursementType = rs.getString("REIMBURSEMENT_TYPE");
				String reimbursementDescription = rs.getString("REIMBURSEMENT_DESCRIPTION");
				String reimbursementStatus = rs.getString("REIMBURSEMENT_STATUS");
				double reimbursementAmount = rs.getDouble("REIMBURSEMENT_AMOUNT");
				int reimbursementManagedBy = rs.getInt("REIMBURSEMENT_MANAGED_BY");
				reimbs.add(new Reimbursement(reimbursementId, reimbursementType, reimbursementDescription, reimbursementStatus, reimbursementAmount, reimbursementManagedBy));
				System.out.println("dao");
			}
			return reimbs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//CHANGES STATUS OF REIMBURSEMENT REQUEST TO DENIED BASED ON REIMBURSEMENT ID
	
	@Override
	public void reimbursementDenied(int reimbursementId) {
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="UPDATE REIMBURSEMENT SET REIMBURSEMENT_STATUS = 'DENIED' WHERE REIMBURSEMENT_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimbursementId);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

//CHANGES STATUS OF REIMBURSEMENT REQUEST TO APPROVED BASED ON REIMBURSEMENT ID
	
	@Override
	public void reimbursementApproved(int reimbursementId) {
		PreparedStatement pstmt = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql="UPDATE REIMBURSEMENT SET REIMBURSEMENT_STATUS = 'APPROVED' WHERE REIMBURSEMENT_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimbursementId);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
//CREATES A NEW REIMBURSEMENT REQUEST IN THE DATABASE AND RETURNS TRUE IF SUCCESSFUL 
	
	@Override
	public boolean createReimbursement (String reimbursementType, String reimbursementDescription, double reimbursementAmount, int employeeId) {
			PreparedStatement pstmt = null;
			try(Connection con = ConnectionUtil.getConnectionFromFile()){
				String sql="INSERT INTO REIMBURSEMENT (REIMBURSEMENT_TYPE, REIMBURSEMENT_DESCRIPTION, REIMBURSEMENT_AMOUNT, EMPLOYEE_ID) VALUES (?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, reimbursementType);
				pstmt.setString(2, reimbursementDescription);
				pstmt.setDouble(3, reimbursementAmount);
				pstmt.setInt(4, employeeId);
				int rowCount = pstmt.executeUpdate();
				if(rowCount > 0) return true;
			} catch (Exception e) {
				e.printStackTrace();	
			}
			return false;
	}
	@Override
	public boolean newEmployee(String firstName, String lastName, String email, int managerId, int employeeLevel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean newLogin(String username, String password, String employeeId) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public void getImage(int reimbursementId, String reimbursementImage) {
		// TODO Auto-generated method stub
		
	}
	
// RETRIEVES ALL PENDING REQUESTS FROM EMPLOYEES OF ANY PARTICULAR MANAGER
	
	public List<Reimbursement> getAllPendingMan(int reimbursementManagedBy) {
		List<Reimbursement> reimbs = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "SELECT * FROM REIMBURSEMENT WHERE REIMBURSEMENT_MANAGED_BY=? AND REIMBURSEMENT_STATUS='PENDING'";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reimbursementManagedBy);
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
					int reimbursementId = rs.getInt("REIMBURSEMENT_ID");
					int employeeId = rs.getInt("EMPLOYEE_ID");
					String reimbursementType = rs.getString("REIMBURSEMENT_TYPE");
					String reimbursementDescription = rs.getString("REIMBURSEMENT_DESCRIPTION");
					String reimbursementStatus = rs.getString("REIMBURSEMENT_STATUS");
					double reimbursementAmount = rs.getDouble("REIMBURSEMENT_AMOUNT");
					int managerId = rs.getInt("REIMBURSEMENT_MANAGED_BY");
					reimbs.add(new Reimbursement(reimbursementId, employeeId, reimbursementType, reimbursementDescription, reimbursementStatus, reimbursementAmount, reimbursementManagedBy));
					System.out.println("reimbs");
			} } catch (Exception e) {
				e.printStackTrace();
			}  
			return reimbs;
	}
	

	@Override
	public List<Reimbursement> getAllResolvedMan() {
		// TODO Auto-generated method stub
		return null;
	}
	
	




	

	
}
