package com.revature.projectone.beans;

public class Employee {

	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private int managerId;
	private int employeeLevel;
	private String managerName;
	private int loginCreds;
	
	public Employee() {
		super();
	}

	public Employee(int employeeId, String firstName, String lastName, int managerId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
	}

	public Employee(int employeeId) {
		super();
		this.employeeId = employeeId;
	}

	public Employee(int employeeId, String firstName, String lastName, int managerId, String managerName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.managerId = managerId;
		this.managerName = managerName;
	}

	public Employee(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Employee(int employeeId, String firstName, String lastName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Employee(int employeeId, String firstName, String lastName, String email) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	
	public Employee(int employeeId, String firstName, String lastName, String email, int managerId, int employeeLevel,
			int loginCreds) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.managerId = managerId;
		this.employeeLevel = employeeLevel;
		this.loginCreds = loginCreds;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeLevel() {
		return employeeLevel;
	}

	public void setEmployeeLevel(int employeeLevel) {
		this.employeeLevel = employeeLevel;
	}
	
	public int getManager() {
		return managerId;
	}

	public void setManager(int manager) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", managerId=" + managerId + ", employeeLevel=" + employeeLevel + ", managerName="
				+ managerName + "]";
	}

	

	
	
	
	
	
	
	
}
