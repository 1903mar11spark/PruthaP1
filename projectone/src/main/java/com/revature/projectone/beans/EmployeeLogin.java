package com.revature.projectone.beans;

public class EmployeeLogin {

	private int loginCreds;
	private String username;
	private String password;
	private Employee employee;
	
	public EmployeeLogin() {
		super();
	}

	public EmployeeLogin(int loginCreds, String username, String password) {
		super();
		this.loginCreds = loginCreds;
		this.username = username;
		this.password = password;
	}

	public EmployeeLogin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public EmployeeLogin(int loginCreds) {
		super();
		this.loginCreds = loginCreds;
	}

	public int getLoginCreds() {
		return loginCreds;
	}

	public void setLoginCreds(int loginCreds) {
		this.loginCreds = loginCreds;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeLogin [loginCreds=" + loginCreds + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
